import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.project3.*;

public class BinaryDecisionTreeTest {
    
    @Test
    public void testConstructor() {
        BinaryTreeNode<String> root = new BinaryTreeNode<>("root");
        BinaryDecisionTree tree = new BinaryDecisionTree(root);
        assertEquals(root, tree.getRoot());
        assertEquals(root, tree.getCurrentNode());
    }

    @Test
    public void testGettersSettersReset() {
        BinaryTreeNode<String> root = new BinaryTreeNode<>("root");
        BinaryTreeNode<String> leftChild = new BinaryTreeNode<>("left");
        BinaryTreeNode<String> rightChild = new BinaryTreeNode<>("right");
        root.setLeft(leftChild);
        root.setRight(rightChild);
        BinaryDecisionTree tree = new BinaryDecisionTree(root);

        // test getters
        assertEquals(root, tree.getRoot());
        assertEquals(root, tree.getCurrentNode());

        // test setters
        tree.setCurrentNode(leftChild);
        assertEquals(leftChild, tree.getCurrentNode());

        tree.setCurrentNode(rightChild);
        assertEquals(rightChild, tree.getCurrentNode());

        // test reset
        tree.resetCurrentNode();
        assertEquals(root, tree.getCurrentNode());
    }

    @Test
    public void testIsCurrentNodeLeaf() {
        BinaryTreeNode<String> leftChild = new BinaryTreeNode<>("left");
        BinaryTreeNode<String> rightChild = new BinaryTreeNode<>("right");
        BinaryTreeNode<String> root = new BinaryTreeNode<>("root", leftChild, rightChild);
        
        BinaryDecisionTree tree = new BinaryDecisionTree(root);

        assertFalse(tree.isCurrentNodeLeaf());

        tree.setCurrentNode(leftChild);
        assertTrue(tree.isCurrentNodeLeaf());

        tree.setCurrentNode(rightChild);
        assertTrue(tree.isCurrentNodeLeaf());
    }

    @Test
    public void testMoveToChildren() {
        BinaryTreeNode<String> leftChild = new BinaryTreeNode<>("left");
        BinaryTreeNode<String> rightChild = new BinaryTreeNode<>("right");
        BinaryTreeNode<String> root = new BinaryTreeNode<>("root", leftChild, rightChild);
        
        BinaryDecisionTree tree = new BinaryDecisionTree(root);

        tree.moveToLeftChild();
        assertEquals(leftChild, tree.getCurrentNode());

        tree.resetCurrentNode();
        tree.moveToRightChild();
        assertEquals(rightChild, tree.getCurrentNode());
    }

    @Test
    public void testAddQuestion() {
        BinaryTreeNode<String> root = new BinaryTreeNode<>("root");
        BinaryDecisionTree tree = new BinaryDecisionTree(root);
        BinaryTreeNode<String> leftChild = new BinaryTreeNode<>("left");
        BinaryTreeNode<String> rightChild = new BinaryTreeNode<>("right");

        tree.addQuestion("Is it a Node?", leftChild, rightChild);
        assertEquals("Is it a Node?", tree.getCurrentNode().getElement());
        assertEquals(leftChild, tree.getCurrentNode().getLeft());
        assertEquals(rightChild, tree.getCurrentNode().getRight());

        // there should be 3 nodes in the tree now
        assertEquals(3, BinaryTreeNode.getNumNodes(tree.getRoot()));
    }
        
}
