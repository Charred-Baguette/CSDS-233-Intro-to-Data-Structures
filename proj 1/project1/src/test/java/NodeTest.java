import com.project1.Node;
import org.jfugue.player.Player;
import org.junit.jupiter.api.Test;

public class NodeTest {
    
    @Test
    /**
     * Tests the creation of a node with various constructors and special cases
     * Also tests the getter methods for the node
     */
    public void testNodeCreation() {
        //standard case for each constructor
        Player player = new Player();
        Node node = new Node("C D E", player);
        assert node.getPhrase().equals("C D E");
        assert node.getNext() == null;
        assert node.getPrev() == null;

        Node node2 = new Node("F G A", player, node);
        assert node2.getPhrase().equals("F G A");
        assert node2.getNext() == null;
        assert node2.getPrev() == node;

        Node node3 = new Node("B C D", player, node2, node);
        assert node3.getPhrase().equals("B C D");
        assert node3.getNext() == node2;
        assert node3.getPrev() == node;

        //special case: empty phrase
        Node node4 = new Node("", player);
        assert node4.getPhrase().equals("");
        assert node4.getNext() == null;
        assert node4.getPrev() == null;

        //special case: null phrase
        Node node5 = new Node(null, player);
        assert node5.getPhrase() == null;
        assert node5.getNext() == null;
        assert node5.getPrev() == null;

        //special case: null player
        Node node6 = new Node("C D E", null);
        assert node6.getPhrase().equals("C D E");
        assert node6.getNext() == null;
        assert node6.getPrev() == null;
    }

    @Test
    /**
     * Tests the setter methods for the node and special cases
     * Node setters only update their own fields; linkage logic is handled by LinkedListComposer
     */
    public void testNodeSetters() {
        Player player = new Player();
        Node node1 = new Node("C D E", player);
        Node node2 = new Node("F G A", player);
        Node node3 = new Node("B C D", player);

        //test setting next and prev pointers
        node1.setNext(node2);
        assert node1.getNext() == node2;

        node2.setPrev(node1);
        assert node2.getPrev() == node1;

        node2.setNext(node3);
        assert node2.getNext() == node3;

        node3.setPrev(node2);
        assert node3.getPrev() == node2;

        //special case: setting next and prev to null
        node1.setNext(null);
        assert node1.getNext() == null;

        node2.setPrev(null);
        assert node2.getPrev() == null;

        //test setting contents
        node1.setPhrase("G A B");
        assert node1.getPhrase().equals("G A B");
        node2.setPhrase("");
        assert node2.getPhrase().equals("");
        node3.setPhrase(null);
        assert node3.getPhrase() == null;
    }

    @Test
    /**
     * Tests the playPhrase method for the node and special cases
     * Also tests the interaction between the player and the phrase when playing
     * Requires manual verification of the output to ensure that the correct phrases are being played and that no errors are thrown
     */
    public void testPlayPhrase() {
        Player player = new Player();
        Node node1 = new Node("C D E", player);
        Node node2 = new Node("", player);
        Node node3 = new Node(null, player);
        Node node4 = new Node("F G A", null);

        //test playing a normal phrase
        node1.playPhrase(); // should play "C D E"

        //test playing an empty phrase
        node2.playPhrase(); // should play nothing

        //test playing a null phrase
        node3.playPhrase(); // should play nothing

        //test playing with a null player
        node4.playPhrase(); // should not throw an error but also not play anything
    }
}

