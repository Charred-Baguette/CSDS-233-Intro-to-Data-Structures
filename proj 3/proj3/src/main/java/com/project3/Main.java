package com.project3;

import java.util.Scanner;

/**
 * Main class to run the travel expert system. Contains the main method and the learning and finding destination functions.
 */
public class Main {
    // The fields
    private BinaryDecisionTree decisionTree;

    /**
     * Getter for the decision tree
     * @return the decision tree
     */
    public BinaryDecisionTree getDecisionTree() {
        return decisionTree;
    }

    /**
     * Setter for the decision tree
     * @param decisionTree the decision tree to set
     */
    public void setDecisionTree(BinaryDecisionTree decisionTree) {
        this.decisionTree = decisionTree;
    }

    /**
     * Function to initialize the decision tree with a root question and two destination guesses. The root question is "Would you like to go on a trip?", the left child is "Vail, Colorado" and the right child is "Have a great day!"
     * This function is called at the start of the program to initialize the decision tree with a basic structure. The user can then build upon this structure by adding new questions and destinations through the learning process.
     */
    public void startUp(){
        setDecisionTree(new BinaryDecisionTree(new BinaryTreeNode<>("Would you like to go on a trip?"))); // root question
        getDecisionTree().getCurrentNode().setLeft(new BinaryTreeNode<>("Vail, Colorado")); // first destination guess
        getDecisionTree().getCurrentNode().setRight(new BinaryTreeNode<>("Have a great day!")); //exit node
    }
    
    /**
     * Overloaded startUp method to load a decision tree from a file
     * @param fileName the name of the file to load the decision tree from
     */
    public void startUp(String fileName){
        BinaryDecisionTree loadedTree = FileManager.loadTreeFromFile(fileName);
        if (loadedTree != null) {
            setDecisionTree(loadedTree);
        } else {
            startUp();
        }
    }

    /**
     * Function to learn new destinations and questions from the user
     * traverses the decision tree based on the yes/no answers until a leaf node is reached
     * if the leaf node is not "Have a great day!", the user is prompted to provide a new destination and a question to distinguish the new destination from the old one
     * the new question is added to the tree with the new destination as the left child and the old destination as the right child
     * the learning process continues until the user decides to terminate or the maximum number of destinations (20) is reached
     * the user is then given the option to save the decision tree to a file
     */
    public void learn(Scanner scanner) {
        int destinationCount = 1;

        System.out.println("Begining the learning process...");
        while(destinationCount < 30){ // limit the number of destinations to 30 to prevent an excessively large tree
            while (!getDecisionTree().isCurrentNodeLeaf()) { // traverse the tree until a leaf node is reached
                if (getDecisionTree().getCurrentNode() == getDecisionTree().getRoot()) {
                    System.out.println("Current amount of destinations in the decision tree: " + destinationCount);

                }
                System.out.println("Please answer the following question with 'yes' or 'no':");
                System.out.println("Learning mode: " + getDecisionTree().getCurrentNode().getElement());
                String answer = scanner.nextLine().toLowerCase();

                while (!answer.equals("y") && !answer.equals("n") && !answer.equals("yes") && !answer.equals("no")) {
                    System.out.println("Invalid input. Please enter 'y' or 'n'.");
                    answer = scanner.nextLine().toLowerCase();
                }

                if (answer.equals("yes") || answer.equals("y")) {
                    getDecisionTree().moveToLeftChild();
                    
                } else {
                    getDecisionTree().moveToRightChild();
                    
                }
                    
                
            }

            // if the leaf node is "Have a great day!", we have reached the exit node and the learning process is complete
            // this checks if not at the exit node, if not then we can learn a new destination and question
            if (!getDecisionTree().getCurrentNode().getElement().equals("Have a great day!")){

                System.out.println(this.getDecisionTree().getCurrentNode().toString());
                System.out.println("Learning mode: Is this where you would like to go? (yes/no)");
                String destinationAnswer = scanner.nextLine().toLowerCase();
                while (!destinationAnswer.equals("y") && !destinationAnswer.equals("n") && !destinationAnswer.equals("yes") && !destinationAnswer.equals("no")) {
                    System.out.println("Invalid input. Please enter 'y' or 'n'.");
                    destinationAnswer = scanner.nextLine().toLowerCase();
                }
                if (destinationAnswer.equals("yes") || destinationAnswer.equals("y")) {
                    System.out.println("Very good. Would you like to terminate the learning process? (yes/no)");
                    String terminateAnswer = scanner.nextLine().toLowerCase();
                    while (!terminateAnswer.equals("y") && !terminateAnswer.equals("n") && !terminateAnswer.equals("yes") && !terminateAnswer.equals("no")) {
                        System.out.println("Invalid input. Please enter 'y' or 'n'.");
                        terminateAnswer = scanner.nextLine().toLowerCase();
                    }
                    if (terminateAnswer.equals("yes") || terminateAnswer.equals("y")) {
                        System.out.println("Terminating learning process...");
                        System.out.println("Would you like to save the decision tree to a file? (yes/no)");
                        String saveAnswer = scanner.nextLine().toLowerCase();
                        while (!saveAnswer.equals("y") && !saveAnswer.equals("n") && !saveAnswer.equals("yes") && !saveAnswer.equals("no")) {
                            System.out.println("Invalid input. Please enter 'y' or 'n'.");
                            saveAnswer = scanner.nextLine().toLowerCase();
                        }
                        if (saveAnswer.equals("yes") || saveAnswer.equals("y")) {
                            System.out.println("Please provide a file name to save the decision tree to:");
                            String fileName = scanner.nextLine();
                            FileManager.saveTreeToFile(getDecisionTree(), fileName);
                            System.out.println("Decision tree saved to " + fileName);
                        } else {
                            System.out.println("Decision tree not saved.");
                        }
                        break;
                    } else {
                        System.out.println("Continuing learning process...");
                        getDecisionTree().resetCurrentNode();
                    }
                } else { // new question and destination to learn
                    System.out.println("Learning mode: What destination were you thinking of?");
                    String newDestination = scanner.nextLine();
                    System.out.println("Learning mode: Please provide a yes/no question that would distinguish " + newDestination + " from " + getDecisionTree().getCurrentNode().getElement());
                    System.out.println("Learning mode: Please ensure that your 'yes' answer corresponds to " + newDestination);
                    String newQuestion = scanner.nextLine();
                    BinaryTreeNode<String> oldDestinationNode = new BinaryTreeNode<>(getDecisionTree().getCurrentNode().getElement());
                    BinaryTreeNode<String> newDestinationNode = new BinaryTreeNode<>(newDestination);
                    getDecisionTree().addQuestion(newQuestion, newDestinationNode, oldDestinationNode);
                    destinationCount++;
                    getDecisionTree().resetCurrentNode();
                }
            } else { // at the exit node, end the learning process
                System.out.println(getDecisionTree().getCurrentNode().getElement());
                System.out.println("End of learning process. Would you like to save the decision tree to a file? (yes/no)");
                String saveAnswer = scanner.nextLine().toLowerCase();
                while (!saveAnswer.equals("y") && !saveAnswer.equals("n") && !
                        saveAnswer.equals("yes") && !saveAnswer.equals("no")) {
                    System.out.println("Invalid input. Please enter 'y' or 'n'.");
                    saveAnswer = scanner.nextLine().toLowerCase();
                }
                if (saveAnswer.equals("yes") || saveAnswer.equals("y")) {
                    System.out.println("Please provide a file name to save the decision tree to:");
                    String fileName = scanner.nextLine();
                    FileManager.saveTreeToFile(getDecisionTree(), fileName);
                    System.out.println("Decision tree saved to " + fileName);
                } else {                    
                    System.out.println("Decision tree not saved.");
                }
                break;
            }

        }
        if (destinationCount >= 20) {
            System.out.println("Maximum number of destinations reached. Ending learning process.");
            FileManager.saveTreeToFile(getDecisionTree(), "decision_tree.txt");
            System.out.println("Decision tree saved to decision_tree.txt");
            // else presume early termination
        }
        System.out.println("Would you like to find a destination? (yes/no)");
        String findDestinationAnswer = scanner.nextLine().toLowerCase();
        while (!findDestinationAnswer.equals("y") && !findDestinationAnswer.equals("n") &&
               !findDestinationAnswer.equals("yes") && !findDestinationAnswer.equals("no")) {
            System.out.println("Invalid input. Please enter 'y' or 'n'.");
            findDestinationAnswer = scanner.nextLine().toLowerCase();
        }
        if (findDestinationAnswer.equals("yes") || findDestinationAnswer.equals("y")) {
            System.out.println("Find a destination mode selected...");
            findDestination(scanner);
        } else {
            System.out.println("Exiting...");
        } 
    }

    
    /**
     * Function to find a destination for the user
     * tranverses the decision tree based on the yes/no answers
     * yes moves to the left child, no moves to the right child
     * stops whena leaf node is reached and outputs the destination recommendation
     * @return the destination node that was reached
     */
    public BinaryTreeNode<String> findDestination(Scanner scanner) {
        decisionTree.resetCurrentNode();
        System.out.println("Please answer the following questions with 'yes' or 'no':");
        while (!decisionTree.isCurrentNodeLeaf()) {
            System.out.println(decisionTree.getCurrentNode().getElement());
            String answer = scanner.nextLine().toLowerCase();

            while (!answer.equals("y") && !answer.equals("n") && !answer.equals("yes") && !answer.equals("no")) {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
                answer = scanner.nextLine().toLowerCase();
            }

            if (answer.equals("yes") || answer.equals("y")) {
                decisionTree.moveToLeftChild();
            } else if (answer.equals("no") || answer.equals("n")) {
                decisionTree.moveToRightChild();
            }
        }
        if (decisionTree.getCurrentNode().getElement().equals("Have a great day!")) {
            System.out.println("Sorry, I couldn't find a destination for you.");
        } else {
            System.out.println(decisionTree.getCurrentNode().toString());
        }
        return decisionTree.getCurrentNode();
    }

    /**
     * Main method to run the program
     * Gives a menu for user interactions
     * @param args no args needed for this program
     */
    public static void main(String[] args) {
        boolean finished = false;
        Main main = new Main();
        while (!finished) {
            System.out.println("Welcome to the Travel Expert System!");
            System.out.println("Would you like to:");
            System.out.println("1. Build a new decision tree");
            System.out.println("2. Find a destination");
            System.out.println("3. Exit");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();
            boolean validInput = false;

            while (!validInput) {
                try {
                    int choiceInt = Integer.parseInt(choice);
                    if (choiceInt < 1 || choiceInt > 3) {
                        System.out.println("Invalid input. Please enter a number between 1 and 3.");
                        choice = scanner.nextLine();
                    } else {
                        validInput = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number between 1 and 3.");
                    choice = scanner.nextLine();
                }
            }
            int choiceInt = Integer.parseInt(choice);
            if (choiceInt == 1){
                System.out.println("Building a new decision tree...");
                main.startUp();
                main.learn(scanner);
            }
            else if (choiceInt == 2){
                System.out.println("Find a destination mode selected...");
                if (main.getDecisionTree() == null) {
                    System.out.println("No decision tree found. Would you like to load a decision tree from a file?");
                    System.out.println("Please provide a file name to load the decision tree from:");
                    String fileName = scanner.nextLine();
                    try {
                        main.startUp(fileName);
                    } catch (Exception e) {
                        System.out.println("Error loading decision tree from file: " + e.getMessage());
                        System.out.println("Starting with a new decision tree instead...");
                        main.startUp();

                    }
                    
                    if (main.getDecisionTree() == null) {
                        System.out.println("Failed to load decision tree. Exiting...");
                        finished = true;
                    }

                    main.findDestination(scanner);
                }
                else {
                    System.out.println("Using existing decision tree...");
                    main.findDestination(scanner);
                }
                
            }
            else if (choiceInt == 3){
                System.out.println("Exiting...");
                scanner.close();
                finished = true;
            }
        }
        

        
    }
}