package com.project1;
import org.jfugue.player.Player;

/**
 * Node class is for a single node in the linked list for musical phrases
 * Each has a phrase, a player, and pointers to the next and previous nodes
 */

public class Node {
    // standard fields
    private String phrase;
    private Node next;
    private Node prev;
    private Player player;

    // constructor for just a phrase and player
    public Node(String phrase, Player player) {
        this.phrase = phrase;
        this.next = null;
        this.prev = null;
        this.player = player;
    }

    //constructor for all fields
    public Node(String phrase, Player player, Node next, Node prev) {
        this.phrase = phrase;
        this.next = next;
        this.prev = prev;
        this.player = player;
    }

    // constructor for phrase, player, and prev. Allows for adding to tail where there is no next node yet
    public Node(String phrase, Player player, Node prev) {
        this.phrase = phrase;
        this.prev = prev;
        this.next = null;
        this.player = player;
    }

    /**
     * Gets the phrase of this node
     * @return the phrase of this node
     */
    public String getPhrase() {
        return this.phrase;
    }

    /**
     * Gets the next node
     * @return the next node
     */
    public Node getNext() {
        return this.next;
    }

    /**
    * Gets the previous node
    * @return the previous node
    */
    public Node getPrev() {
        return this.prev;
    }

    /**
     * Sets the phrase of this node
     * @param phrase the phrase to set
     */
    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    /**
     * Sets the next node
     * @param next the next node to set
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * Sets the previous node
     * @param prev the previous node to set
     */
    public void setPrev(Node prev) {
        this.prev = prev;
    }

    /**
    * Plays the phrase of this node using the player
    */
    public void playPhrase() {
        try {
            this.player.play(this.phrase);
        } catch (Exception e) {
            System.out.println("Error playing phrase: " + e.getMessage());
        }
    }
}
