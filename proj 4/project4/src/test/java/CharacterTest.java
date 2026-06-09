import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.project4.Character;



/**
 * Test class for the Character class using JUNIT
 */
public class CharacterTest {

    @Test
    /**
     * Tests the constructor and getters of the Character class
     */
    public void testConstructorAndGetters() {
        Character character = new Character(5, 10, 1);
        assertEquals(5, character.getArrivalTime());
        assertEquals(10, character.getRequestTime());
        assertEquals(1, character.getCharacterID());
        assertEquals(0, character.getDepartureTime());
        assertEquals(0, character.getPriority());
        assertEquals(0, character.getEntranceTime());
    }

    @Test
    /**
     * Tests the setters of the Character class
     */
    public void testSetters() {
        Character character = new Character(5, 10, 1);
        character.setArrivalTime(6);
        character.setRequestTime(12);
        character.setDepartureTime(20);
        character.setPriority(2);
        character.setEntranceTime(7);

        assertEquals(6, character.getArrivalTime());
        assertEquals(12, character.getRequestTime());
        assertEquals(20, character.getDepartureTime());
        assertEquals(2, character.getPriority());
        assertEquals(7, character.getEntranceTime());
    }

    
    @Test
    /**
     * Tests the comparator of the Character class
     */
    public void testComparator() {
        Character character1 = new Character(5, 10, 1);
        Character character2 = new Character(5, 8, 2);
        Character character3 = new Character(6, 10, 3);

        character1.setPriority(character1.getRequestTime());
        character2.setPriority(character2.getRequestTime());
        character3.setPriority(character3.getRequestTime());

        assertTrue(character1.compareTo(character2) > 0);
        assertTrue(character1.compareTo(character3) == 0); 
    }


}
