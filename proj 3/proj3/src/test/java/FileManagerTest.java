import org.junit.jupiter.api.Test;
import com.project3.*;
import static org.junit.jupiter.api.Assertions.*;

public class FileManagerTest {
    String testFilePath = "src/test/java/resources/testFile.txt";

    @Test
    public void testSaveAndLoadTree() {
        // Create a simple decision tree
        BinaryTreeNode<String> root = new BinaryTreeNode<>("Is it an animal?");
        BinaryTreeNode<String> leftChild = new BinaryTreeNode<>("Is it a mammal?");
        BinaryTreeNode<String> rightChild = new BinaryTreeNode<>("Is it a plant?");
        root.setLeft(leftChild);
        root.setRight(rightChild);
        BinaryDecisionTree tree = new BinaryDecisionTree(root);

        // Save the tree to a file
        FileManager.saveTreeToFile(tree, testFilePath);

        // Load the tree from the file
        BinaryDecisionTree loadedTree = FileManager.loadTreeFromFile(testFilePath);

        // Verify that the loaded tree is the same as the original tree
        assertNotNull(loadedTree);
        assertEquals(tree.getRoot().getElement(), loadedTree.getRoot().getElement());
        assertEquals(tree.getRoot().getLeft().getElement(), loadedTree.getRoot().getLeft().getElement());
        assertEquals(tree.getRoot().getRight().getElement(), loadedTree.getRoot().getRight().getElement());
    }
}
