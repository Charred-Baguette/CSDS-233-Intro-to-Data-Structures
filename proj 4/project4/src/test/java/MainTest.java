import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.project4.Character;
import com.project4.Main;


public class MainTest {
    @Test
    /**
     * Tests the getters and setters of the Main class
     */
    public void testGettersAndSetters() {
        Main main = new Main();
        main.setMode("FCFS");
        assertEquals("FCFS", main.getMode());

        Character character1 = new Character(5, 10, 1);
        Character character2 = new Character(6, 12, 2);
        Character[] characters = {character1, character2};
        main.setCharacters(characters);
        assertArrayEquals(characters, main.getCharacters());
        assertEquals(2, main.getCharacters().length);
        assertEquals(2, main.getTotalCharacters());


        main.setCurrentCharacterID(1);
        assertEquals(1, main.getCurrentCharacterID());

        main.setCurrentTime(10);
        assertEquals(10, main.getCurrentTime());

        main.setCurrentCharacter(character1);
        assertEquals(character1, main.getCurrentCharacter());

        main.setMinHeap(new java.util.PriorityQueue<>());
        assertNotNull(main.getMinHeap());   
    }

    @Test
    /**
     * Tests the get next character and add to queue methods
     */
    public void testGetNextCharacterAndAddToQueue() {
        Main main = new Main();
        main.setMode("SRF");
        Character character1 = new Character(5, 10, 1);
        Character character2 = new Character(6, 12, 2);
        Character[] characters = {character1, character2};
        main.setCharacters(characters);
        main.setCurrentCharacterID(0);
        main.setMinHeap(new java.util.PriorityQueue<>());

        main.addCharacterToQueue(character1);
        assertFalse(main.getMinHeap().isEmpty());
        assertEquals(character1, main.getMinHeap().peek());

        Character nextCharacter = main.getNextCharacter();
        assertEquals(character1, nextCharacter);
        assertTrue(main.getMinHeap().isEmpty());
    }

    @Test
    /**
     * Tests the enter cave and finish cave methods
     */
    public void testEnterCaveAndFinishCave() {
        Main main = new Main();
        main.setMode("SRF");
        Character character1 = new Character(0, 10, 1);
        Character[] characters = {character1};
        main.setCharacters(characters);
        main.setCurrentCharacterID(0);
        main.setMinHeap(new java.util.PriorityQueue<>());

        main.enterCave(character1);
        assertEquals(character1, main.getCurrentCharacter());

        main.finishCurrentCharacter(character1);
        assertNull(main.getCurrentCharacter());
        assertEquals(10, character1.getDepartureTime());
    }

    @Test
    /**
     * Tests the find next event method
     */
    public void testFindNextEvent() {
        Main main = new Main();
        main.setMode("SRF");
        Character character1 = new Character(0, 10, 1);
        Character character2 = new Character(5, 12, 2);
        Character[] characters = {character1, character2};
        main.setCharacters(characters);
        main.setCurrentCharacterID(0);
        main.setMinHeap(new java.util.PriorityQueue<>());

        // case 1: No current character and queue is empty, wait for next character to arrive and accept them into the cave rather than do a time tick
        main.findNextEvent();
        assertEquals(character1, main.getCurrentCharacter());
        assertEquals(0, main.getCurrentTime());

        // case 2: Current character is still in the cave but a new character arrives, add them to the queue but do not interrupt the current character
        main.findNextEvent();
        assertEquals(character1, main.getCurrentCharacter());
        assertFalse(main.getMinHeap().isEmpty());
        assertEquals(character2, main.getMinHeap().peek());
        assertEquals(5, main.getCurrentTime());

        // case 3: Current character finishes and there is a character in the queue, remove the next character from the queue and enter them into the cave
        main.finishCurrentCharacter(character1);
        main.findNextEvent();
        assertEquals(character2, main.getCurrentCharacter());
        
    }

    @Test
    /**
     * Tests the compute average waiting time method
     */
    public void testComputeAverageWaitingTime() {
        Main main = new Main();
        Character character1 = new Character(0, 10, 1);
        Character character2 = new Character(5, 12, 2);
        Character[] characters = {character1, character2};
        main.setCharacters(characters);
        main.setMode("SRF");

        while (main.getCurrentCharacterID() < characters.length || main.getCurrentCharacter() != null || !main.getMinHeap().isEmpty()) {
            main.findNextEvent();
        }

        double averageWaitingTime = main.computeAverageWaitingTime();
        assertEquals(2.5, averageWaitingTime);
    
    }
}
