package com.project3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * File manager class to save/load the decision tree using a preorder traversal. The file format is as follows:
 * Each line represents a node in the tree. The first line is the root, followed by the left subtree and then the right subtree. A null node is represented by a line containing only "#". For example:
 * Would you like to go on a trip?
 * Vail, Colorado
 * #          <- null left child
 * #
 * Have a great day!
 * #
 * #
 */
public class FileManager {

    /**
     * Saves the given decision tree to a file using a preorder traversal.
     * @param tree the decision tree to save
     * @param fileName the name of the file to save the tree to
     */
    public static void saveTreeToFile(BinaryDecisionTree tree, String fileName) {
        if (tree == null || tree.getRoot() == null) {
            System.out.println("Cannot save an empty tree.");
            return;
        }
        if (fileName == null || fileName.isEmpty()) {
            System.out.println("Invalid file name.");
            return;
        }
        if (!fileName.endsWith(".txt")) {
            fileName += ".txt"; // it must be a .txt
         }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            saveNodeToFile(tree.getRoot(), writer);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the tree to file: " + e.getMessage());
        }
    }

    /**
     * Helper method to save a node and its children to a file using a preorder traversal (recursive).
     * @param node the node to save
     * @param writer the BufferedWriter to write to
     * @throws IOException if an I/O error occurs
     */
    private static void saveNodeToFile(BinaryTreeNode<String> node, BufferedWriter writer) throws IOException {
        if (node == null) {
            writer.write("#\n"); // null node
            return;
        }
        writer.write(node.getElement() + "\n");
        saveNodeToFile(node.getLeft(), writer);
        saveNodeToFile(node.getRight(), writer);
    }

    /**
     * Loads a decision tree from a file that was saved using the saveTreeToFile method.
     * @param fileName the name of the file to load the tree from
     * @return the loaded decision tree, or null if an error occurs
     */
    public static BinaryDecisionTree loadTreeFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            BinaryTreeNode<String> root = loadNodeFromFile(reader);
            return new BinaryDecisionTree(root);
        } catch (IOException e) {
            System.out.println("An error occurred while loading the tree from file: " + e.getMessage());
            return null;
        }
    }

    /**
     * Helper method to load a node and its children from a file using a preorder traversal (recursive).
     * @param reader the BufferedReader to read from
     * @return the loaded node, or null if the node is a marker for null
     * @throws IOException if an I/O error occurs
     */
    private static BinaryTreeNode<String> loadNodeFromFile(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line == null || line.equals("#")) {
            return null; 
        }
        BinaryTreeNode<String> node = new BinaryTreeNode<>(line);
        node.setLeft(loadNodeFromFile(reader));
        node.setRight(loadNodeFromFile(reader));
        return node;
    }

}