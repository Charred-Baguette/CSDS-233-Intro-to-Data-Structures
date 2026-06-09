package com.project1;
import org.jfugue.player.Player;

/**
    This class is for managing the linked list of musical phrase nodes.
    All necessary methods for adding, removing, and moving nodes are here, as well as various methods for playing the composition
 */
public class LinkedListComposer {
    // standard fields for a linked list with a current pointer and a player
    private Node head;
    private Node tail;
    private Node current;
    private Player player;

    // constructor for an empty linked list composer
    public LinkedListComposer() {
        this.head = null;
        this.tail = null;
        this.current = null;
        this.player = new Player();
    }

    /**
    * Gets the head node
    * @return the head node
     */
    public Node getHead() {
        return this.head;
    }

    /**
    * Gets the tail node
    * @return the tail node
     */
    public Node getTail() {
        return this.tail;
    }

    /**
     * Gets the current node
     * @return the current node
     */
    public Node getCurrent() {
        return this.current;
    }

    /**
     * Gets the player
     * @return the player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Sets the head node
     * @param head the new head node
     */
    public void setHead(Node head) {
        this.head = head;
    }

    /**
     * Sets the tail node
     * @param tail the new tail node
     */
    public void setTail(Node tail) {
        this.tail = tail;
    }

    /**
     * Sets the current node
     * @param current the new current node
     */
    public void setCurrent(Node current) {
        this.current = current;
    }

    /**
     * Sets the player
     * @param player the new player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Initializes the composer with the first phrase
     * This is a helper method to avoid code duplication when adding the first phrase to an empty composer
     * @param phrase the phrase to initialize the composer with
     */
    public void initializeComposer(String phrase) {
        Node newNode = new Node(phrase, this.player);
        this.setHead(newNode);
        this.setTail(newNode);
        this.setCurrent(newNode);
    }

    /**
     * Adds a phrase to the end of the linked list
     * @param phrase the phrase to add
     */
    public void addPhraseTail(String phrase) {
        Node newNode = new Node(phrase, this.player);
        if (this.getHead() == null) {
            this.initializeComposer(phrase);
        } else {
            newNode.setPrev(this.getTail());
            this.getTail().setNext(newNode);
            this.setTail(newNode);
        }

    }

    /**
     * Adds a phrase to the beginning of the linked list
     * @param phrase the phrase to add
     */
    public void addPhraseHead(String phrase) {
        Node newNode = new Node(phrase, this.player);
        if (this.getHead() == null) {
            this.initializeComposer(phrase);
        } else {
            newNode.setNext(this.getHead());
            this.getHead().setPrev(newNode);
            this.setHead(newNode);
        }
    }

    /**
     * Adds a phrase at the current position in the linked list
     * If the current position is at the end of the list, it adds to the tail instead.
     * @param phrase the phrase to add
     */
    public void addAtCurrent(String phrase) {
        if (this.getHead() == null || this.getCurrent() == null) { // if the list is empty or current is null, initialize the composer with the new phrase
            this.initializeComposer(phrase);
            return;
        }

        if (this.getCurrent() == this.getTail()) { // if current is at the end of the list, add to tail
            this.addPhraseTail(phrase);
            this.setCurrent(this.getTail());
            return;
        }

        Node prev = this.getCurrent().getPrev(); // get the previous node to the current node
        Node newNode = new Node(phrase, this.getPlayer(), this.getCurrent(), prev);

        if (prev != null) { // if there is a previous node, set its next to the new node
            prev.setNext(newNode);
        } else { // if there is no previous node, the new node is the new head
            this.setHead(newNode);
        }

        this.getCurrent().setPrev(newNode); // set the current node's previous to the new node
        this.setCurrent(newNode); // set the current node to the new node
    }

    /**
     * Copies the current block to the tail of the linked list
     */
    public void copyBlock() {
        addPhraseTail(this.getCurrent().getPhrase());
    }

    /**
     * Removes the current node from the linked list and updates the current pointer accordingly
     * If the current node is the only node in the list, it sets head, tail, and current to null.
     */
    public void removeCurrent() {
        if (this.getCurrent() == this.getHead() && this.getCurrent() == this.getTail()) {
            this.setHead(null);
            this.setTail(null);
            this.setCurrent(null);
        } else if (this.getCurrent() == this.getHead()) {
            this.setHead(this.getCurrent().getNext());
            this.getHead().setPrev(null);
            this.setCurrent(this.getHead());
        } else if (this.getCurrent() == this.getTail()) {
            this.setTail(this.getCurrent().getPrev());
            this.getTail().setNext(null);
            this.setCurrent(this.getTail());
        } else {
            this.getCurrent().getPrev().setNext(this.getCurrent().getNext());
            this.getCurrent().getNext().setPrev(this.getCurrent().getPrev());
            this.setCurrent(this.getCurrent().getNext());
        }
    }

    /**
     * Removes the first node with the specified phrase from the linked list and updates the current pointer accordingly
     * @param phrase the phrase to remove
     */
    public void removePhraseSpecific(String phrase) {
        Node temp = this.getHead();
        while (temp != null) {
            if (temp.getPhrase().equals(phrase)) {
                if (temp == this.getHead() && temp == this.getTail()) { // only one node in the list
                    this.setHead(null);
                    this.setTail(null);
                    this.setCurrent(null);
                } else if (temp == this.getHead()) { // removing head node
                    this.setHead(temp.getNext());
                    this.getHead().setPrev(null);
                    if (this.getCurrent() == temp) {
                        this.setCurrent(this.getHead());
                    }
                } else if (temp == this.getTail()) { // removing tail node
                    this.setTail(temp.getPrev());
                    this.getTail().setNext(null);
                    if (this.getCurrent() == temp) {
                        this.setCurrent(this.getTail());
                    }
                } else { // removing a middle node
                    temp.getPrev().setNext(temp.getNext());
                    temp.getNext().setPrev(temp.getPrev());
                    if (this.getCurrent() == temp) {
                        this.setCurrent(temp.getNext());
                    }
                }
            }
            temp = temp.getNext();
        }
    }

    /**
     * Moves a phrase from its current position to a new position in the linked list and updates the current pointer accordingly
     * @param initialNode the node to move
     * @param newPosition the node to move to. If null, moves to the end of the list
     */
    public void movePhrase(Node initialNode, Node newPosition) {
        if (initialNode == newPosition) { // if the initial node and new position are the same, do nothing
            return;
        }
        String tempPhrase = initialNode.getPhrase(); 
        this.setCurrent(initialNode);
        this.removeCurrent();
        if (newPosition == null) { // if new position is null, add to tail
            this.addPhraseTail(tempPhrase);
        } else { // if new position is not null, insert before the new position
            Node newNode = new Node(tempPhrase, this.player, newPosition, newPosition.getPrev());
            if (newPosition.getPrev() != null) {
                newPosition.getPrev().setNext(newNode);
            } else {
                this.setHead(newNode);
            }
            newPosition.setPrev(newNode);
            this.setCurrent(newNode);
        }
    }

    /**
     * Plays the entire composition from head to tail
     */
    public void playFull() { 
        Node temp = this.getHead();
        while (temp != null) {
            temp.playPhrase();
            temp = temp.getNext();
        }
    }

    /**
     * Plays the composition from the current node to the tail
     */
    public void playFromCurrent() {
        Node temp = this.getCurrent();
        while (temp != null) {
            temp.playPhrase();
            temp = temp.getNext();
        }
    }

    /**
     * Plays the phrase of the current node
     */
    public void playCurrent() {
        if (this.getCurrent() != null) {
            this.getCurrent().playPhrase();
        }
    }

    /**
     * Moves the current pointer to the left (previous node) if possible
     */
    public void currentLeft() {
        if (this.getCurrent() != null && this.getCurrent().getPrev() != null) {
            this.setCurrent(this.getCurrent().getPrev());
        }
    }

    /**
     * Moves the current pointer to the right (next node) if possible
     */
    public void currentRight() {
        if (this.getCurrent() != null && this.getCurrent().getNext() != null) {
            this.setCurrent(this.getCurrent().getNext());
        }
    }

    /**
    * Gets the full composition as a string
    * @return the full composition as a string
    */
    public String getFullComposition() {
        StringBuilder composition = new StringBuilder();
        Node temp = this.getHead();
        while (temp != null) {
            if (temp == this.getCurrent()) {
                composition.append("[").append(temp.getPhrase()).append("] ").toString(); // add brackets around the current phrase
            } else {
            composition.append(temp.getPhrase()).append(" ");
            }
            temp = temp.getNext();
        }
        return composition.toString();
    }
}
