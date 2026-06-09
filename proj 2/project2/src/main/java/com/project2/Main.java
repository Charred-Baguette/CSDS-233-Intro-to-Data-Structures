package com.project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/**
 * Code for CSDS 233 Project 2
 * @author George Maurice
 */
public class Main {
    // Fields
    private Set<String> dictionary = new HashSet<>();  // use hashset since set is an interface

    // Getter
    /**
     * Returns the dictionary
     * @return the dictionary
     */
    public Set<String> getDictionary() {
        return dictionary;
    }
    

    /**
     * Loads the dictionary from a file
     * @param filename the name of the file to load the dictionary from
     */
    public void loadDictionary(String filename) {
        File file = new File(filename);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (Exception e) {
            System.out.println("Error reading file: " + filename + ": " + e.getMessage());
        }
        
        while(scanner != null && scanner.hasNextLine()) {
            String word = scanner.nextLine().trim();
            if (!word.isEmpty()) {
                this.getDictionary().add(word);
            }
        }
    }

    /**
     * Returns an array list of words that are one letter different from the input word
     * also checks if the word exists in the dictionary
     * @param base the word to generate the differnt words from
     * @return an array list of words that are one letter different and are in the dictionary
     */
    public ArrayList<String> getOneDifferentWords(String base) {
        ArrayList<String> oneDifferentWords = new ArrayList<String>();
        char[] baseChars = base.toCharArray();

        for (int i = 0; i < baseChars.length; i++) {
            char OChar = baseChars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != OChar) {
                    baseChars[i] = c;
                    String newWord = new String(baseChars);
                    if (this.getDictionary().contains(newWord)) {
                        oneDifferentWords.add(newWord);
                    }
                }
            }
            baseChars[i] = OChar; // Restore original character
        }
        return oneDifferentWords;
    }

    /**
     * Finds a path from the start word to the end word using the instructed queue of stacks approach
     * @param start the word to start from
     * @param end the target word
     * @return a stack of strings that represent the path from the start to the end word, or null if not possible
     */
    public Stack<String> findIndividualPath(String start, String end) {
        Queue<Stack<String>> queue = new LinkedList<>(); // Queue to hold paths

        if (!this.getDictionary().contains(start) || !this.getDictionary().contains(end)) {
            return null; // Return null if either the start or end word is not in the dictionary
        }

        if (start.equals(end)) {
            Stack<String> path = new Stack<>();
            path.push(start);
            return path; // Return a stack with the start word if it is the same as the end word
        }

        //process for initial stacks
        Set<String> neighbors = new HashSet<>(getOneDifferentWords(start));
        
        for (String neighbor : neighbors) {
            Stack<String> stack = new Stack<>();
            stack.push(start);
            stack.push(neighbor);
            queue.add(stack);
        }

        // Continue recursive process until queue is empty
        while (!queue.isEmpty()) {
            Stack<String> currentPath = queue.poll(); // Get the next path from the queue
            String lastWord = currentPath.peek(); // Get the last word in the current path
            if (lastWord.equals(end)) {
                return currentPath; // Return the path if the last word is the end word
            }
            for (String neighbor : getOneDifferentWords(lastWord)) {
                if (!currentPath.contains(neighbor)) { // Avoid cycles
                    Stack<String> newPath = (Stack<String>) currentPath.clone(); // Clone the current path
                    newPath.push(neighbor); // Add the neighbor to the new path
                    queue.add(newPath); // Add the new path to the queue
                }
            }
        }
        return null;
    }

    /**
     * Main method to run the program
     * @param args The first argument will be a path to the dictionary file, but if none present then the code will prompt user
     */
    public static void main( String[] args ){
        // Create an instance of Main
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);
        // Load the dictionary 
        boolean loaded = false;
        while (!loaded) {
            if (args.length == 0) {
                System.out.println("Please provide a dictionary file path");
                String filename = scanner.nextLine();
                try {
                    main.loadDictionary(filename);
                    loaded = true;
                } catch (Exception e) {
                    System.out.println("Error loading dictionary: " + e.getMessage());
                    loaded = false; 
                }
            }
            else {
                try {
                    main.loadDictionary(args[0]);
                    if (main.getDictionary() == null || main.getDictionary().isEmpty()) {
                        args = new String[0]; // Clear args to make user input for file path
                    }
                    loaded = true;
                } catch (Exception e) {
                    System.out.println("Error loading dictionary: " + e.getMessage());
                    loaded = false;
                    args = new String[0]; // Clear args to make user input for file path
                }
            }
            if (main.getDictionary() == null || main.getDictionary().isEmpty()) {
                loaded = false;
            }
        }
        boolean active = true;
        // Get user input for start and end words
        while (active) {
            System.out.println("Enter start word (or 'exit' to quit):");
            String start = scanner.nextLine().trim();
            if (start.equalsIgnoreCase("exit")) {
                active = false;
                continue;
            }

            if (start.length() != 5) {
                System.out.println("Please enter a 5 letter word");
                continue;
            }

            System.out.println("Enter end word:");
            String end = scanner.nextLine().trim();

            if (end.length() != 5) {
                System.out.println("Please enter a 5 letter word");
                continue;
            }

            // Find and print the path
            Stack<String> path = main.findIndividualPath(start, end);
            if (path != null) {
                System.out.println("Path found: " + path);
            } else {
                System.out.println("No path found from " + start + " to " + end);
            }
        }
        scanner.close();
    }
}
