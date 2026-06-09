import com.project2.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import java.io.File;
import java.util.Set;
import java.util.Stack;
import java.util.HashSet;
import java.util.Scanner;



public class MainTest {

    @Test
    /**
     * Tests the loadDictionary method and the getDictionary method by comparing the dictionary loaded
     */
    public void testLoadDictionary() {
        Main main = new Main();
        main.loadDictionary("C:\\Users\\georg\\Documents\\class\\freshman\\csds 233\\proj 2\\project2\\words.txt"); // replace with your own path to the words.txt file
        File file = new File("C:\\Users\\georg\\Documents\\class\\freshman\\csds 233\\proj 2\\project2\\words.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (Exception e) {
            System.out.println("Error reading file: " + file.getName() + ": " + e.getMessage());
            throw new RuntimeException(e);
        }
        Set<String> tempSet = new HashSet<>();
        while (scanner != null && scanner.hasNextLine()) {
            String word = scanner.nextLine().trim();
            if (!word.isEmpty()) {
                tempSet.add(word);
            }
        }
        scanner.close();
        assertEquals(tempSet, main.getDictionary());

        // Check if no file is input (Main would continuously prompt the user but it is not expected to crash if there is a failure, but silntly fail)
        assertDoesNotThrow(() -> main.loadDictionary(""));
    }

    @Test
    /**
     * Tests the getOneDifferentWords method by comparing the output to a known set of words that are one letter different from the input word
     */
    public void testGetOneDifferentWords() {
        Main main = new Main();
        Set<String> expected = new HashSet<>();
        expected.add("cat");
        expected.add("cot");
        expected.add("lot");
        expected.add("cog");
        new HashSet<>(main.getOneDifferentWords("cot"));
        for (String word : main.getOneDifferentWords("cot")) {
            assertEquals(true, expected.contains(word));
        }

        // Check if the input word is not in the dictionary (should return an empty list)
        assertEquals(0, main.getOneDifferentWords("ajbefguahviejioa").size());

        // Check if it is just empty
        assertEquals(0, main.getOneDifferentWords("").size());
    }

    @Test
    /**
     * Tests the find Individual Path method by using a known path
     */
    public void testFindIndividualPath() {
        Main main = new Main();
        main.loadDictionary("C:\\Users\\georg\\Documents\\class\\freshman\\csds 233\\proj 2\\project2\\words.txt"); // replace with your own path to the words.txt file
        Stack<String> expected = new Stack<>();
        expected.add("corps");
        expected.add("corns");
        Stack<String> actual = main.findIndividualPath("corps", "corns");
        System.out.println(actual);
        assertEquals(expected, actual);

        // Check if the start word is not in the dictionary (should return a null)
        assertEquals(null, main.findIndividualPath("ajbef", "corns"));

        // check if the end word is not in the dictionary (should return a null)
        assertEquals(null, main.findIndividualPath("corps", "ajbef"));

        // Check if both the start and end word are not in the dictionary (should return a null)
        assertEquals(null, main.findIndividualPath("ajbcf", "ajbef"));

        // Check if the start and end word are the same (should return a stack with just the word)
        Stack<String> expectedSame = new Stack<>();
        expectedSame.add("corps");
        assertEquals(expectedSame, main.findIndividualPath("corps", "corps"));

        // Check if just empty
        assertEquals(null, main.findIndividualPath("", ""));

        // Test a longer path: tears to smile : tears sears spars spare spire spile smile
        Stack<String> expectedLonger = new Stack<>();
        expectedLonger.add("tears");
        expectedLonger.add("sears");
        expectedLonger.add("spars");
        expectedLonger.add("spare");
        expectedLonger.add("spire");
        expectedLonger.add("spile");
        expectedLonger.add("smile");
        assertEquals(expectedLonger, main.findIndividualPath("tears", "smile"));

        // Check if there is no path
        // use secondary dataset for easier testing of no path, but it should also work with the main dataset
        main.loadDictionary("C:\\Users\\georg\\Documents\\class\\freshman\\csds 233\\proj 2\\project2\\words3.txt"); // replace with your own path to the words3.txt file
        assertEquals(null, main.findIndividualPath("could", "where"));

        
    }
}
