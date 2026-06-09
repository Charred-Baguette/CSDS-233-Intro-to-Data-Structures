package com.project4;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The CharacterReader class is used to read in character data from a file then create Character objects. It will return null if there is an issue with the read
 * @author George Maurice
 */
public class CharacterReader {
    // Shared logger instance
    Logger logger;

    /**
     * Standard constructor that takes in a logger instance
     * @param logger The logger instance to use for logging events and errors
     */
    public CharacterReader(Logger logger) {
        this.logger = logger;
    }
    
    /**
     * This reads in a single line of a chacater's data to return a new character object or null
     * @param line The line to parse
     * @param characterID The ID assigned to the new character
     * @return A new character object or null if there is an issue with the data
     */
    public Character readSingleCharacter(String line, int characterID) {
        if (line == null) {
            return null;
        }
        String[] parts = line.split(" ");
        int arrivalTime;
        int requestTime;
        
        try {
            arrivalTime = Integer.parseInt(parts[0]);
            requestTime = Integer.parseInt(parts[1]);
        } catch (Exception e) {
            logger.log("Error parsing character data: " + e.getMessage());
            return null;
        }

        if (arrivalTime < 0 || requestTime < 0) {
            logger.log("Invalid character data: Arrival time and request time must be non-negative.");
            return null;
        }
    
        return new Character(arrivalTime, requestTime, characterID);
    }
    
    /**
     * This reads in a file of character data and can return either an array of characters or null
     * @param filename The name of the file to read from
     * @return An array of characters or null if there is an issue with the file or the data
     */
    public Character[] readCharactersFromFile(String filename) {
        ArrayList<Character> characterList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int characterID = 0;
            while ((line = br.readLine()) != null) {
                Character character = readSingleCharacter(line, characterID++);
                if (character != null) {
                    characterList.add(character);
                }
                else {
                    logger.log("Invalid character data: " + line);
                    return null;
                }
            }
        } catch (IOException e) {
            logger.log("Error reading characters from file: " + e.getMessage());
            return null;
        }
        return characterList.toArray(new Character[0]);
    }
}
