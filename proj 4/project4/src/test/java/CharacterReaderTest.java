import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.project4.Character;
import com.project4.CharacterReader;
import com.project4.Logger;

public class CharacterReaderTest {
    @Test
    /**
     * Tests the readSingleCharacter method 
     */
    public void testReadSingleCharacter() {
        Logger logger = new Logger();
        CharacterReader reader = new CharacterReader(logger);
        
        // valid input
        Character character = reader.readSingleCharacter("5 10", 1);
        assertNotNull(character);
        assertEquals(5, character.getArrivalTime());
        assertEquals(10, character.getRequestTime());
        assertEquals(1, character.getCharacterID());

        // invalid input null line
        assertNull(reader.readSingleCharacter(null, 2));

        // invalid input non-integer values
        assertNull(reader.readSingleCharacter("abc def", 3));

        // invalid input negative values
        assertNull(reader.readSingleCharacter("-1 -5", 4));
    }

    @Test
    /**
     * Tests the readCharactersFromFile method
     */
    public void testReadCharactersFromFile() {
        Logger logger = new Logger();
        CharacterReader reader = new CharacterReader(logger);
        
        // valid file
        Character[] characters = reader.readCharactersFromFile("test.txt");
        assertNotNull(characters);
        assertEquals(3, characters.length);
        assertEquals(0, characters[0].getArrivalTime());
        assertEquals(30, characters[0].getRequestTime());
        assertEquals(0, characters[0].getCharacterID());

        // invalid file non-existent file
        assertNull(reader.readCharactersFromFile("non_existent_file.txt"));

        // invalid file invalid data
        assertNull(reader.readCharactersFromFile("error.txt"));

    }
}
