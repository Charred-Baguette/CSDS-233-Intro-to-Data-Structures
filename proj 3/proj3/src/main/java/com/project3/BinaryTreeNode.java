package com.project3;

/**
 * Binary tree node class to represent a node in the decision tree
 */
public class BinaryTreeNode<T> {
    // fields
    private T element;
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;

    /**
     * Constructor for the BinaryTreeNode class
     * @param initElement the element to store in the node
     * @param initLeft the left child of the node
     * @param initRight the right child of the node
     */
    public BinaryTreeNode(T initElement, BinaryTreeNode<T> initLeft, BinaryTreeNode<T> initRight){
        element = initElement;
        left = initLeft;
        right = initRight;
    }

    /**
     * Constructor for the BinaryTreeNode class with only the element parameter, left and right children are set to null
     * @param initElement the element to store in the node
     */
    public BinaryTreeNode(T initElement){
        element = initElement;
        left = null;
        right = null;
    }

    /**
     * Default constructor for the BinaryTreeNode class, element is set to null and left and right children are set to null
     */
    public BinaryTreeNode(){
        left = right = null;
    }

    /**
     * Setter for the element stored in the node
     * @param element the element to set
     */
    public void setElement(T element ) {this.element =element ;}

    /**
     * Getter for the element stored in the node
     * @return the element stored in the node
     */
    public T getElement() {return this.element;}

    /**
     * Setter for the left child of the node
     * @param left the left child to set
     */
    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    /**
     * Getter for the left child of the node
     * @return the left child of the node
     */
    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    /**
     * Setter for the right child of the node
     * @param right the right child to set
     */
    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    /**
     * Getter for the right child of the node
     * @return the right child of the node
     */

    public BinaryTreeNode<T> getRight() {
        return right;
    }

    //Returns the height of the binary tree rooted at "root"
    /**
     * Returns the height of the binary tree rooted at root
     * @param <T> the type of the elements stored in the binary tree
     * @param root the root of the binary tree to calculate the height of
     * @return the height of the binary tree rooted at root
     */
    public static <T> int getHeight(BinaryTreeNode<T> root){

        if(root == null)
            return 0;
        int lh = getHeight(root.getLeft());
        int rh = getHeight(root.getRight());
        if(lh>rh)
            return lh +1;
        else
            return rh +1;
    }

    /**
     * toString method to return a string representation of the node, used for printing the node's element when it is a leaf node
     * @return a string representation of the node's element
     */
    public String toString(){
        return "I recommend the following destination: " + getElement();
    } 



    //Returns the number of nodes in the binary tree rooted at "root"
    /**
     * Returns the number of nodes in the binary tree rooted at root
     * @param <T> the type of the elements stored in the binary tree
     * @param root the root of the binary tree to calculate the number of nodes of
     * @return the number of nodes in the binary tree rooted at root
     */
    public static <T> int getNumNodes(BinaryTreeNode<T> root){
        if (root == null)
            return 0;
        int sum = 1;
        int branchSum = getNumNodes(root.getLeft()) + getNumNodes(root.getRight());
        return sum + branchSum;
    }

    //Returns true if this BinaryTreeNode is a leaf node
    /**
     * Returns true if this BinaryTreeNode is a leaf node, false otherwise
     * @return true if this BinaryTreeNode is a leaf node, false otherwise
     */
    public boolean isLeaf(){
        if (left == null && right == null)
            return true;
        else
            return false;
    }
   
}
