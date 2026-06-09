package com.project1;
import java.util.Scanner;

public class Main {

    public static String listenInput(Scanner scanner) {
        System.out.print("Enter command: ");
        return scanner.nextLine();
    }

    public static void printMenu() {
        System.out.println("Menu:");
        System.out.println("1. Add phrase to tail");
        System.out.println("2. Add phrase to head");
        System.out.println("3. Add at current position");
        System.out.println("4. Copy phrase to end");
        System.out.println("5. Remove current phrase");
        System.out.println("6. Remove all of a specific phrases");
        System.out.println("7. Move current to new location");
        System.out.println("8. Play entire composition");
        System.out.println("9. Play current phrase to end");
        System.out.println("10. Play current phrase only");
        System.out.println("11. Move to next phrase");
        System.out.println("12. Move to previous phrase");
        System.out.println("13. Print list");
        System.out.println("14. Exit");
    }

    public static void main(String[] args) {
        LinkedListComposer composer = new LinkedListComposer();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            String input = listenInput(scanner);
            if (input.equals("1")) {
                System.out.println("Enter phrase to add to tail:");
                String phrase = listenInput(scanner);
                composer.addPhraseTail(phrase);
            } else if (input.equals("2")) {
                System.out.println("Enter phrase to add to head:");
                String phrase = listenInput(scanner);
                composer.addPhraseHead(phrase);
            } else if (input.equals("3")) {
                System.out.println("Enter phrase to add at current position:");
                String phrase = listenInput(scanner);
                composer.addAtCurrent(phrase);
            } else if (input.equals("4")) {
                System.out.println("Coppying current phrase to end...");
                composer.copyBlock();
            } else if (input.equals("5")) {
                System.out.println("Removing current phrase...");
                composer.removeCurrent();
            } else if (input.equals("6")) {
                System.out.println("Enter phrase to remove:");
                String phrase = listenInput(scanner);
                composer.removePhraseSpecific(phrase);
            } else if (input.equals("7")) {
                Node temp = composer.getCurrent();
                System.out.println("Move the current phrase to the new location.");
                while (true) {
                    System.out.println("Current composition: " + composer.getFullComposition());
                    System.out.println("1. Move right");
                    System.out.println("2. Move left");
                    System.out.println("3. Select new location");
                    String move_input = listenInput(scanner);
                    if (move_input.equals("1")) {
                        composer.currentRight();
                    } else if (move_input.equals("2")) {
                        composer.currentLeft();
                    } else if (move_input.equals("3")) {
                        Node new_location = composer.getCurrent();
                        composer.movePhrase(temp, new_location);
                        break;
                    } else {
                        System.out.println("Invalid command. Please try again.");
                    }
                }
            } else if (input.equals("8")) {
                System.out.println("Playing entire composition...");
                composer.playFull();
            } else if (input.equals("9")) {
                System.out.println("Playing from current phrase to end...");
                composer.playFromCurrent();
            } else if (input.equals("10")) {
                System.out.println("Playing current phrase...");
                composer.playCurrent();
            } else if (input.equals("11")) {
                System.out.println("Moving to next phrase...");
                composer.currentRight();
            } else if (input.equals("12")) {
                System.out.println("Moving to previous phrase...");
                composer.currentLeft();
            } else if (input.equals("13")) {
                System.out.println("Printing list...");
                System.out.println(composer.getFullComposition());
            } else if (input.equals("14")) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid command. Please try again.");
            }


        }
    }
}