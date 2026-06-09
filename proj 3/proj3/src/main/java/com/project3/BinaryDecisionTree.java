package com.project3;

/**
 * Binary decision tree class to represent the decision tree 
 */
public class BinaryDecisionTree {
    // fields
    private BinaryTreeNode<String> root;
    private BinaryTreeNode<String> currentNode;

    /**
     * Constructor for the BinaryDecisionTree class
     * @param initRoot the root of the decision tree
     */
    public BinaryDecisionTree(BinaryTreeNode<String> initRoot){
        root = initRoot;
        currentNode = root;
    }

    /**
     * Getter for the root of the decision tree
     * @return the root of the decision tree
     */
    public BinaryTreeNode<String> getRoot() {
        return root;
    }

    /**
     * Getter for the current node of the decision tree
     * @return the current node of the decision tree
     */
    public BinaryTreeNode<String> getCurrentNode() {
        return currentNode;
    }

    /**
     * Setter for the current node of the decision tree
     * @param currentNode the node to set as the current node
     */
    public void setCurrentNode(BinaryTreeNode<String> currentNode) {
        this.currentNode = currentNode;
    }

    /**
     * Resets the current node to the root of the decision tree
     */
    public void resetCurrentNode(){
        setCurrentNode(root);
    }

    // Returns true if the current node is a leaf node, false otherwise
    public boolean isCurrentNodeLeaf() {
        return getCurrentNode().isLeaf();
    }

    /**
     * Moves the current node to its left child, if it exists
     */
    public void moveToLeftChild() {
        if(getCurrentNode().getLeft() != null)
            setCurrentNode(getCurrentNode().getLeft());
    }

    /**
     * Moves the current node to its right child, if it exists
     */
    public void moveToRightChild() {
        if(getCurrentNode().getRight() != null)
            setCurrentNode(getCurrentNode().getRight());
    }

        /**
        * Adds a question to the current node and creates left and right children for the current node with the given left and right child nodes
        * For this case the answer 'yes' should lead to the left child and the answer 'no' should lead to the right child
        * @param question the question to add to the current node
        * @param leftChild the left child node to set as the left child of the current node
        * @param rightChild the right child node to set as the right child of the current node
        */
    public void addQuestion(String question, BinaryTreeNode<String> leftChild, BinaryTreeNode<String> rightChild) {
        getCurrentNode().setElement(question);
        getCurrentNode().setLeft(leftChild);
        getCurrentNode().setRight(rightChild);
    }

    
}
