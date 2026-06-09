package com.project4;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * This class is used to run the full simulation
 * Rather than go through each time, this checks for the next event (extra credit)
 * @author George Maurice
 */
public class Main {
    // all required fields for the simulation
    private int currentTime = 0;
    private int lastArrivalTime = 0;
    private int totalCharacters = 0;
    private Character currentCharacter = null;
    private int currentCharacterID = 0;
    private Scanner scanner = new Scanner(System.in);
    private String mode;
    private PriorityQueue<Character> minHeap = new PriorityQueue<>(); //min heap priority queue based on character priority
    private Character[] characters = new Character[100]; //list of all characters read from file
    private Logger logger = new Logger();

    /**
     * Gets the current time of the simulation
     * @return The current time of the simulation
     */
    public int getCurrentTime() {
        return currentTime;
    }

    /**
     * Sets the current time of the simulation
     * @param currentTime The current time to set for the simulation
     */
    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    /**
     * Gets the total number of characters in the simulation
     * @return The total number of characters
     */
    public int getTotalCharacters() {
        return totalCharacters;
    }

    /**
     * Sets the total number of characters in the simulation
     * @param totalCharacters The total number of characters to set
     */
    public void setTotalCharacters(int totalCharacters) {
        this.totalCharacters = totalCharacters;
    }

    /**
     * Gets the mode of the simulation
     * @return The mode of the simulation
     */
    public String getMode() {
        return mode;
    }

    /**
     * Sets the mode of the simulation
     * @param mode The mode to set for the simulation
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * Gets the last arrival time of the simulation
     * @return The last arrival time of the simulation
     */
    public int getLastArrivalTime() {
        return lastArrivalTime;
    }

    /**
     * Sets the last arrival time of the simulation
     * @param lastArrivalTime The last arrival time to set for the simulation
     */
    public void setLastArrivalTime(int lastArrivalTime) {
        this.lastArrivalTime = lastArrivalTime;
    }

    /**
     * Gets the current character in the simulation
     * @return The current character in the simulation
     */
    public Character getCurrentCharacter() {
        return currentCharacter;
    }

    /**
     * Sets the current character in the simulation
     * @param currentCharacter The current character to set for the simulation
     */
    public void setCurrentCharacter(Character currentCharacter) {
        this.currentCharacter = currentCharacter;
    }

    /**
     * Sets the current character ID in the simulation
     * @param currentCharacterID The current character ID to set for the simulation
     */
    public void setCurrentCharacterID(int currentCharacterID) {
        this.currentCharacterID = currentCharacterID;
    }

    /**
     * Gets the current character ID in the simulation
     * @return The current character ID in the simulation
     */
    public int getCurrentCharacterID() {
        return currentCharacterID;
    }

    /**
     * Gets all the characters in the simulation
     * @return The characters in the simulation
     */
    public Character[] getCharacters() {
        return characters;
    }

    /**
     * Sets all the characters in the simulation
     * @param characters The characters to set for the simulation
     */
    public void setCharacters(Character[] characters) {
        this.characters = characters;
        this.setTotalCharacters(characters.length);
    }

    /**
     * Gets the min heap priority queue of characters in the simulation
     * @return The min heap priority queue of characters in the simulation
     */
    public PriorityQueue<Character> getMinHeap() {
        return minHeap;
    }

    /**
     * Sets the min heap priority queue of characters in the simulation
     * @param minHeap The min heap priority queue to set for the simulation
     */
    public void setMinHeap(PriorityQueue<Character> minHeap) {
        this.minHeap = minHeap;
    }


    /**
     * This method is used to add a character to the queue and set their priority based on the mode of the simulation
     * @param character The character to add to the queue
     */
    public void addCharacterToQueue(Character character) {
        if (character == null) {
            System.err.println("Cannot add null character to queue.");
            return;
        }
        if (mode.equals("SRF")) {
            character.setPriority(character.getRequestTime());
        } else if (mode.equals("FCFS")) {
            character.setPriority(character.getArrivalTime());
        }
        minHeap.offer(character);
        totalCharacters++;
        logger.logEvent(currentTime, "Character ID " + character.getCharacterID() + " added to the queue");
    }

    /**
     * This method is used to get the next character from the queue and return it, or return null if the queue is empty
     * @return The next character from the queue or null if the queue is empty
     */
    public Character getNextCharacter() {
        if (minHeap.isEmpty()) {
            return null;
        }
        Character nextCharacter = minHeap.poll();
        return nextCharacter;
    }

    /**
     * This method is used to enter a character into the cave and log the event, or return if the character is null
     * @param character The character to enter the cave
     */
    public void enterCave(Character character) {
        if (character == null) {
            System.err.println("Cannot enter cave with null character.");
            return;
        }
        this.setCurrentCharacter(character);
        character.setEntranceTime(this.getCurrentTime());
        logger.logEvent(this.getCurrentTime(), "Character ID " + character.getCharacterID() + " entered the cave");
    }

    /**
     * This method is used to finish the current character in the cave and log the event, or return if the character is null
     * @param character The character to finish in the cave
     */
    public void finishCurrentCharacter(Character character) {
        if (character == null) {
            System.err.println("Cannot finish null character.");
            return;
        }
        this.setCurrentTime(character.getEntranceTime() + character.getRequestTime());
        character.setDepartureTime(this.getCurrentTime());
        logger.logEvent(this.getCurrentTime(), "Character ID " + character.getCharacterID() + " left the cave");
        this.setCurrentCharacter(null);
    }

    /**
     * For extra credit: this method is used to find the next event in the simulation and conduct a time jump to that event rather than going through each time step
     */
    public void findNextEvent() { //method for the extra credit, dont use time step instead find next event and conduct a time jump
        if ((characters == null || characters.length == 0) && this.getCurrentCharacter() == null) {
            return; // no more events to process
        }

        boolean moreArrivals = currentCharacterID < characters.length;

        // case 1: No current character and queue is empty, wait for next character to arrive and accept them into the cave rather than do a time tick
        if (this.getCurrentCharacter() == null && minHeap.isEmpty()) {
            if (!moreArrivals) 
                return;
            Character nextCharacter = this.getCharacters()[currentCharacterID++];
            this.setCurrentTime(nextCharacter.getArrivalTime());
            this.addCharacterToQueue(nextCharacter); // to ensure the event is logged and the character is added to the queue before entering the cave
            nextCharacter = this.getNextCharacter();
            this.enterCave(nextCharacter);
            return;
        }

        // case 2: There is a current character in the cave and the next character arrives before or when the current character finishes, add next character to queue
        else if (this.getCurrentCharacter() != null && moreArrivals && (this.getCurrentCharacter().getEntranceTime() + this.getCurrentCharacter().getRequestTime()) >= this.getCharacters()[currentCharacterID].getArrivalTime()) {
            Character nextCharacter = this.getCharacters()[currentCharacterID++];
            this.setCurrentTime(nextCharacter.getArrivalTime());
            this.addCharacterToQueue(nextCharacter);
            }

        // case 3: The current character finishes before the next character arrives (or no more arrivals), finish current character and accept next character (if applicable)
        else if (this.getCurrentCharacter() != null && (!moreArrivals || (this.getCurrentCharacter().getEntranceTime() + this.getCurrentCharacter().getRequestTime()) < this.getCharacters()[currentCharacterID].getArrivalTime())) {
            this.finishCurrentCharacter(this.getCurrentCharacter());
            this.setCurrentCharacter(null);
            if (!this.getMinHeap().isEmpty()) {
                Character nextCharacter = this.getNextCharacter();
                this.enterCave(nextCharacter);
            }
        }

        // case 4: There is no current character but there are characters in the queue, accept next character into the cave
        else if (this.getCurrentCharacter() == null && !minHeap.isEmpty()) {
            Character nextCharacter = this.getNextCharacter();
            this.enterCave(nextCharacter);
        }

    }
    
    /**
     * This method is used to compute the average waiting time of all characters in the simulation, or return 0 if there are no characters
     * @return The average waiting time
     */
    public double computeAverageWaitingTime() {
        if (characters == null || characters.length == 0) {
            return 0.0;
        }
        int totalWaitingTime = 0;
        for (Character character : characters) {
            int waitingTime = character.getDepartureTime() - character.getArrivalTime() - character.getRequestTime();
            totalWaitingTime += waitingTime;
        }
        return (double) totalWaitingTime / characters.length;
    }
    
    /**
     * The main method to run the full simulation
     * It asks for the mode and the filename and runs the simulation as needed
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Project 4 Character Scheduling Simulation");
        System.out.println("What mode would you like to run? FCFS or SRF?");
        Main main = new Main();
        while (true) {
            String input = main.scanner.nextLine().trim().toUpperCase();
            if (input.equals("FCFS") || input.equals("SRF")) {
                main.setMode(input);
                main.logger.log("Simulation mode set to: " + input);
                break;
            } else {
                System.out.println("Invalid input. Please enter 'FCFS' or 'SRF'.");
            }
        }
        CharacterReader reader = new CharacterReader(main.logger);
        Character[] characters = null;
        String filename = "";
        while (characters == null || characters.length == 0) {
            System.out.println("What is the filename of the character data?");
            filename = main.scanner.nextLine().trim();
            characters = reader.readCharactersFromFile(filename);
            if (characters == null || characters.length == 0) {
                System.out.println("No characters read from file. Please check the filename and file contents, and try again.");
            }   
        }
        main.logger.log("Valid character data read from file: " + filename);
        main.logger.log("Read " + characters.length  + " characters from file: " + filename);
        main.setCharacters(characters);

        main.logger.log("Beginning full simulation in mode: " + main.getMode());
        main.logger.log("Time         Event");
        main.logger.log("----         -----");
        main.setCurrentCharacterID(0);
        main.setCurrentCharacter(null);
        main.setCurrentTime(0);
        while (main.getCurrentCharacterID() < characters.length || main.getCurrentCharacter() != null || !main.getMinHeap().isEmpty()) {
            main.findNextEvent();
        }
        main.logger.log("Simulation complete. Average waiting time: " + main.computeAverageWaitingTime());

    }
}