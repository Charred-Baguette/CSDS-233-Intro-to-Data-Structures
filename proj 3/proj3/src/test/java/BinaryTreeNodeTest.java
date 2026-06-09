import com.project3.BinaryTreeNode;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * JUNIT tests for the binary tree node class
 */
public class BinaryTreeNodeTest {
    
    /**
     * Test the getter and setter methods of the BinaryTreeNode class
     */
    @Test
    public void testGetterSetters() {
        BinaryTreeNode<String> node = new BinaryTreeNode<>("root");
        assertEquals("root", node.getElement());
        assertNull(node.getLeft());
        assertNull(node.getRight());

        BinaryTreeNode<String> leftChild = new BinaryTreeNode<>("left");
        BinaryTreeNode<String> rightChild = new BinaryTreeNode<>("right");

        node.setLeft(leftChild);
        node.setRight(rightChild);

        assertEquals(leftChild, node.getLeft());
        assertEquals(rightChild, node.getRight());

        // get height
        assertEquals(2, BinaryTreeNode.getHeight(node));
        assertEquals(1, BinaryTreeNode.getHeight(leftChild));

        // test num nodes
        assertEquals(3, BinaryTreeNode.getNumNodes(node));
        assertEquals(1, BinaryTreeNode.getNumNodes(leftChild));
    }

    /**
     * Tests the default constructor
     */
    @Test
    public void testDefaultConstructor() {
        BinaryTreeNode<String> node = new BinaryTreeNode<>();
        assertNull(node.getElement());
        assertNull(node.getLeft());
        assertNull(node.getRight());
    }

    /**
     * Tests the full constructor with element and left and right children
     */
    @Test
    public void testFullConstructor() {
        BinaryTreeNode<String> leftChild = new BinaryTreeNode<>("left");
        BinaryTreeNode<String> rightChild = new BinaryTreeNode<>("right");
        BinaryTreeNode<String> node = new BinaryTreeNode<>("root", leftChild, rightChild);

        assertEquals("root", node.getElement());
        assertEquals(leftChild, node.getLeft());
        assertEquals(rightChild, node.getRight());
    }

    @Test
    public void testToString() {
        BinaryTreeNode<String> node = new BinaryTreeNode<>("test");
        assertEquals("I recommend the following destination: test", node.toString());
    }

    @Test
    public void testIsLeaf() {
        BinaryTreeNode<String> node = new BinaryTreeNode<>("test");
        assertTrue(node.isLeaf());

        BinaryTreeNode<String> leftChild = new BinaryTreeNode<>("left");
        node.setLeft(leftChild);
        assertFalse(node.isLeaf());

        BinaryTreeNode<String> rightChild = new BinaryTreeNode<>("right");
        node.setRight(rightChild);
        assertFalse(node.isLeaf());
    }
}
