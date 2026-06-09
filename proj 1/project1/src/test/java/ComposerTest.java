import com.project1.LinkedListComposer;
import org.junit.jupiter.api.Test;

public class ComposerTest {
    
    @Test
    /**
     * Tests the creation of a composer with the default constructor
     */
    public void testComposerCreation() {
        LinkedListComposer composer = new LinkedListComposer();
        assert composer.getHead() == null;
        assert composer.getTail() == null;
        assert composer.getCurrent() == null;
    }

    @Test
    /**
     * Initialization method using the phrase it is provided
     * also tests the getter methods for the head, tail, and current node after initialization
     */
    public void testInitialization() {
        LinkedListComposer composer = new LinkedListComposer();
        composer.initializeComposer("C D E F G");
        assert composer.getHead() != null;
        assert composer.getTail() != null;
        assert composer.getCurrent() != null;
        assert composer.getHead().getPhrase().equals("C D E F G");
        assert composer.getTail().getPhrase().equals("C D E F G");
        assert composer.getCurrent().getPhrase().equals("C D E F G");

        //special cases: empty string
        composer.initializeComposer("");
        assert composer.getHead() != null;
        assert composer.getTail() != null;
        assert composer.getCurrent() != null;
        assert composer.getHead().getPhrase().equals("");
        assert composer.getTail().getPhrase().equals("");
        assert composer.getCurrent().getPhrase().equals("");

        //special case: null string
        composer.initializeComposer(null);
        assert composer.getHead() != null;
        assert composer.getTail() != null;
        assert composer.getCurrent() != null;
        assert composer.getHead().getPhrase() == null;
        assert composer.getTail().getPhrase() == null;
        assert composer.getCurrent().getPhrase() == null;
    }

    @Test
    /**
     * Tests the various add phrase methods
     * also tests the getter methods for the head, tail, and current node after adding phrases to ensure they are correctly updated
     * also tests the get method for the full composition to ensure the phrases are correctly added to the composition in the correct order with the correct formatting
     * since these methods rely on the node setters to update the next and prev pointers, this also indirectly tests the node setters and their interaction with each other when setting the next and prev pointers
     */
    public void testAddPhrase() {
        LinkedListComposer composer = new LinkedListComposer();
        composer.initializeComposer("D E F");

        //test adding to head
        composer.addPhraseHead("A B C");
        assert composer.getHead().getPhrase().equals("A B C");
        assert composer.getHead().getNext().getPhrase().equals("D E F");
        assert composer.getHead().getPrev() == null;
        assert composer.getFullComposition().equals("A B C [D E F] ");

        //test adding to tail
        composer.addPhraseTail("G H I");
        assert composer.getTail().getPhrase().equals("G H I");
        assert composer.getTail().getPrev().getPhrase().equals("D E F");
        assert composer.getTail().getNext() == null;
        assert composer.getFullComposition().equals("A B C [D E F] G H I ");

        //test adding at current
        composer.addAtCurrent("X Y Z");
        assert composer.getCurrent().getPhrase().equals("X Y Z");
        assert composer.getCurrent().getPrev().getPhrase().equals("A B C");
        assert composer.getCurrent().getNext().getPhrase().equals("D E F");
        assert composer.getFullComposition().equals("A B C [X Y Z] D E F G H I ");

        //special case: adding to head when there is only one node
        LinkedListComposer composer2 = new LinkedListComposer();
        composer2.initializeComposer("M N O");
        composer2.addPhraseHead("J K L");
        assert composer2.getHead().getPhrase().equals("J K L");
        assert composer2.getHead().getNext().getPhrase().equals("M N O");
        assert composer2.getHead().getPrev() == null;
        assert composer2.getTail().getPhrase().equals("M N O");
        assert composer2.getTail().getPrev().getPhrase().equals("J K L");
        assert composer2.getTail().getNext() == null;

        //special case: adding to tail when there is only one node
        LinkedListComposer composer3 = new LinkedListComposer();
        composer3.initializeComposer("P Q R");
        composer3.addPhraseTail("S T U");
        assert composer3.getTail().getPhrase().equals("S T U");
        assert composer3.getTail().getPrev().getPhrase().equals("P Q R");
        assert composer3.getTail().getNext() == null;
        assert composer3.getHead().getPhrase().equals("P Q R");
        assert composer3.getHead().getNext().getPhrase().equals("S T U");
        assert composer3.getHead().getPrev() == null;
    }

    @Test
    /**
     * Tests the specialized adding methods: copy block and move Phrase
     * also tests the formatting of the composition to ensure it is correct with brackets around the current phrase and spaces between phrases
     */
    public void testSpecialAddMethods() {
        LinkedListComposer composer = new LinkedListComposer();
        composer.initializeComposer("D E F");
        composer.addPhraseHead("A B C");
        composer.addPhraseTail("G H I");
        
        //test copy block, should move current to the end of the copied block
        composer.copyBlock();
        assert composer.getTail().getPhrase().equals("D E F");
        assert composer.getTail().getPrev().getPhrase().equals("G H I");
        assert composer.getFullComposition().equals("A B C [D E F] G H I D E F ");
        assert composer.getCurrent().getPhrase().equals("D E F");

        //test move phrase, should move the current phrase to the new location and update the current pointer to point to the moved phrase in its new location
        composer.movePhrase(composer.getCurrent(), composer.getHead());
        assert composer.getHead().getPhrase().equals("D E F");
        assert composer.getHead().getNext().getPhrase().equals("A B C");
        assert composer.getFullComposition().equals("[D E F] A B C G H I D E F ");
        assert composer.getCurrent().getPhrase().equals("D E F");
    }

    @Test
    /**
     * Tests the various remove phrase methods
     * also tests the getter methods for the head, tail, and current node after removing phrases to ensure they are correctly updated
     * also tests the get method for the full composition to ensure the phrases are correctly removed from the composition and the formatting is correct with brackets around the current phrase and spaces between phrases
     */
    public void testRemoveMethods() {
        LinkedListComposer composer = new LinkedListComposer();
        composer.initializeComposer("D E F");
        composer.addPhraseHead("A B C");
        composer.addPhraseTail("G H I");
        composer.addAtCurrent("X Y Z");

        //test removing current
        composer.removeCurrent();
        assert composer.getCurrent().getPhrase().equals("D E F");
        assert composer.getFullComposition().equals("A B C [D E F] G H I ");
        assert composer.getHead().getPhrase().equals("A B C");
        assert composer.getTail().getPhrase().equals("G H I");
        assert composer.getHead().getNext().getPhrase().equals("D E F");
        assert composer.getTail().getPrev().getPhrase().equals("D E F");

        composer.addPhraseHead("X Y Z");
        composer.addPhraseTail("X Y Z");

        //test removing specific phrase, should remove all instances of the phrase and update the head, tail, and current pointers accordingly
        composer.removePhraseSpecific("X Y Z");
        assert composer.getFullComposition().equals("A B C [D E F] G H I ");
        assert composer.getHead().getPhrase().equals("A B C");
        assert composer.getTail().getPhrase().equals("G H I");
        assert composer.getCurrent().getPhrase().equals("D E F");
        assert composer.getHead().getNext().getPhrase().equals("D E F");
        assert composer.getTail().getPrev().getPhrase().equals("D E F");

        //special case: removing the only phrase in the composition, should result in an empty composition with head, tail, and current pointers all set to null
        LinkedListComposer composer2 = new LinkedListComposer();
        composer2.initializeComposer("M N O");
        composer2.removeCurrent();
        assert composer2.getHead() == null;
        assert composer2.getTail() == null;
        assert composer2.getCurrent() == null;

        //special case: removing a phrase that does not exist in the composition, should not change the composition
        LinkedListComposer composer3 = new LinkedListComposer();
        composer3.initializeComposer("P Q R");
        composer3.removePhraseSpecific("X Y Z");
        assert composer3.getFullComposition().equals("[P Q R] ");
        assert composer3.getHead().getPhrase().equals("P Q R");
        assert composer3.getTail().getPhrase().equals("P Q R");
        assert composer3.getCurrent().getPhrase().equals("P Q R");
    }

    @Test
    /**
     * Tests the various playback methods
     * If an error is encountered the code should print the issue and continue playing the rest of the composition without crashing
     */
    public void testPlaybackMethods() {
        LinkedListComposer composer = new LinkedListComposer();
        composer.initializeComposer("C D E F G");
        composer.addPhraseHead("A B C");
        composer.addPhraseTail("G H I");
        composer.addAtCurrent("X Y Z");

        //test playing entire composition, should play all phrases in the composition in order from head to tail
        composer.playFull();

        //test playing current to end, should play all phrases from the current phrase to the end of the composition in order
        composer.playFromCurrent();

        //test playing current phrase only, should only play the current phrase
        composer.playCurrent();
    }

    @Test
    /**
     * Tests the current mover methods: currentLeft and currentRight
     * also tests the formatting of the composition to ensure it is correct with brackets around the current
     * includes handling the special cases of trying to move past the head or tail
     */
    public void testCurrentMover() {
        LinkedListComposer composer = new LinkedListComposer();
        composer.initializeComposer("D E F");
        composer.addPhraseHead("A B C");
        composer.addPhraseTail("G H I");
        assert(composer.getFullComposition().equals("A B C [D E F] G H I "));

        //test moving current left twice. Once to get to head then one more to break past
        composer.currentLeft();
        assert composer.getCurrent().getPhrase().equals("A B C");
        assert composer.getFullComposition().equals("[A B C] D E F G H I ");
        composer.currentLeft();
        assert composer.getCurrent().getPhrase().equals("A B C");
        assert composer.getFullComposition().equals("[A B C] D E F G H I ");

        //test moving current right three times.
        composer.currentRight();
        composer.currentRight();
        assert composer.getCurrent().getPhrase().equals("G H I");
        assert composer.getFullComposition().equals("A B C D E F [G H I] ");
        composer.currentRight();
        assert composer.getCurrent().getPhrase().equals("G H I");
        assert composer.getFullComposition().equals("A B C D E F [G H I] ");

    }
}
