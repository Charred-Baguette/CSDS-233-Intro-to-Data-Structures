package com.project4;
/**
 * This class contains all the data needed for a character. It also uses comparable so characters can be compared
 * @author George Maurice
 */
public class Character implements Comparable<Character> {
    // all required fields for a character
    private int priority;
    private int arrivalTime;
    private int requestTime;
    private int departureTime;
    private int entranceTime;
    private int characterID;


    /**
     * Standard constructor for a character object
     * @param arrivalTime The time the character arrives
     * @param requestTime The time the character requests service
     * @param characterID The unique ID of the character
     */
    public Character(int arrivalTime, int requestTime, int characterID) {
        this.arrivalTime = arrivalTime;
        this.requestTime = requestTime;
        this.characterID = characterID;
    }

    /**
     * Gets the priority of the character
     * @return The priority of the character
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the priority of the character
     * @param priority The priority to set for the character
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Gets the arrival time of the character
     * @return The arrival time of the character
     */
    public int getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Sets the arrival time of the character
     * @param arrivalTime The arrival time to set for the character
     */
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    /**
     * Gets the request time of the character
     * @return The request time of the character
     */
    public int getRequestTime() {
        return requestTime;
    }
    /**
     * Sets the request time of the character
     * @param requestTime The request time to set for the character
     */
    public void setRequestTime(int requestTime) {
        this.requestTime = requestTime;
    }
    /**
     * Gets the departure time of the character
     * @return The departure time of the character
     */
    public int getDepartureTime() {
        return departureTime;
    }
    /**
     * Sets the departure time of the character
     * @param departureTime The departure time to set for the character
     */
    public void setDepartureTime(int departureTime) {
        this.departureTime = departureTime;
    }
    /**
     * Gets the unique ID of the character
     * @return The unique ID of the character
     */
    public int getCharacterID() {
        return characterID;
    }
    /**
     * Sets the unique ID of the character
     * @param characterID The unique ID to set for the character
     */
    public void setCharacterID(int characterID) {
        this.characterID = characterID;
    }
    /**
     * Gets the entrance time of the character
     * @return The entrance time of the character
     */
    public int getEntranceTime() {
        return entranceTime;
    }
    /**
     * Sets the entrance time of the character
     * @param entranceTime The entrance time to set for the character
     */
    public void setEntranceTime(int entranceTime) {
        this.entranceTime = entranceTime;
    }

    /**
     * Compare to method is used to compare the priorities of two characters
     * Uses the Integer compare method
     */
    @Override
    public int compareTo(Character otherCharacter) {
        return Integer.compare(this.getPriority(), otherCharacter.getPriority());
    }
}
