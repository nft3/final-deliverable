import org.junit.*;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

/**
 * @author Nicholas Treu
 */
public class DeckTest {

    private Deck deck;

    // Create a fresh deck every time as test is run
    @Before
    public void setUp() {
        deck = new Deck();
    }

    // Test that the deck size is 52 after it is created
    @Test
    public void testDeckSize() {
        assertEquals(52, deck.getDeckSize());
    }

    // Test that after shuffling, check that card at a certain position in the deck is not the same card is was pre
    // shuffle.
    // NOTE: There is a chance that it ends up in the same position.
    @Test
    public void testShuffle() {
        // Get a random number between 0 and 51
        int randomNum = (int) (Math.random() * 51);

        // Get the card at a certain position in the deck
        Card temp = deck.getCard(randomNum);

        // Shuffle the deck
        deck.shuffleDeck();

        // Assert that the cards are not the same object.
        assertNotEquals(temp, deck.getCard(randomNum));
    }

    // In an attempt to offset the chance of a card being in the same position in the test above,
    // get three cards and see if they are in the same position.
    @Test
    public void testShuffleThreeCards() {
        // Get three cards after the deck was made
        Card[] beforeShuffleCards = new Card[3];
        beforeShuffleCards[0] = deck.getCard(10);
        beforeShuffleCards[1] = deck.getCard(24);
        beforeShuffleCards[2] = deck.getCard(48);

        // Shuffle the deck
        deck.shuffleDeck();

        // Get the cards at the same positions as above
        Card[] afterShuffleCards = new Card[3];
        afterShuffleCards[0] = deck.getCard(10);
        afterShuffleCards[1] = deck.getCard(24);
        afterShuffleCards[2] = deck.getCard(48);

        // Assert that these two arrays are NOT the equal.
        assertFalse(Arrays.equals(beforeShuffleCards, afterShuffleCards));
    }

    // To test that all cards are printed out, we can test that there are 13 occurrences of each suit in the Srting
    // that is returned.

    // First test will test if we see 13 occurrences of "Hearts"
    @Test
    public void testPrintWholeDeckHearts(){
        // Set up some byte streams so that we can test what is printed to standard output
        final ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stdout));

        // Print the whole deck to standard output
        deck.printWholeDeck();

        // Get the String of the deck that was printed out
        String wholeDeck = stdout.toString();

        // Clean up the steams now
        System.setOut(null);

        assertEquals(13, findCountOfSubString(wholeDeck, "Hearts"));

    }

    // First test will test if we see 13 occurrences of "Diamonds"
    @Test
    public void testPrintWholeDeckDiamonds(){
        // Set up some byte streams so that we can test what is printed to standard output
        final ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stdout));

        // Print the whole deck to standard output
        deck.printWholeDeck();

        // Get the String of the deck that was printed out
        String wholeDeck = stdout.toString();

        // Clean up the steams now
        System.setOut(null);

        assertEquals(13, findCountOfSubString(wholeDeck, "Diamonds"));

    }

    // First test will test if we see 13 occurrences of "Spades"
    @Test
    public void testPrintWholeDeckSpades(){
        // Set up some byte streams so that we can test what is printed to standard output
        final ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stdout));

        // Print the whole deck to standard output
        deck.printWholeDeck();

        // Get the String of the deck that was printed out
        String wholeDeck = stdout.toString();

        // Clean up the steams now
        System.setOut(null);

        assertEquals(13, findCountOfSubString(wholeDeck, "Spades"));

    }

    // First test will test if we see 13 occurrences of "Clubs"
    @Test
    public void testPrintWholeDeckClubs(){
        // Set up some byte streams so that we can test what is printed to standard output
        final ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stdout));

        // Print the whole deck to standard output
        deck.printWholeDeck();

        // Get the String of the deck that was printed out
        String wholeDeck = stdout.toString();

        // Clean up the steams now
        System.setOut(null);

        assertEquals(13, findCountOfSubString(wholeDeck, "Clubs"));

    }

    // In order to test a hit, let's shuffle the deck, hit, then check to see if the size of the ArrayList
    // is not the same.
    @Test
    public void testHit(){
        // Shuffle the deck
        deck.shuffleDeck();

        // Get the size of the deck after it was shuffled
        int initSize = deck.getDeckSize();

        // Get a hit from the deck
        deck.hit();

        // Get the size of the deck after a hit
        int afterHitSize = deck.getDeckSize();

        assertNotEquals(initSize, afterHitSize);
    }

    // Test the the size of the ArrayList that is implementing the deck is of size 51 (51 cards in the deck).
    @Test
    public void testHitSize(){
        // Shuffle the deck
        deck.shuffleDeck();

        // Get a hit from the deck
        deck.hit();

        // Get the size of the deck after a hit
        int afterHitSize = deck.getDeckSize();

        assertEquals(51, afterHitSize);
    }


    // Find a count of the occurrences of a substring
    private int findCountOfSubString(String wholeStr, String searchFor){
        int lastIndex = 0, count = 0;

        while(lastIndex != -1){
            lastIndex = wholeStr.indexOf(searchFor, lastIndex);

            if(lastIndex != -1){
                count += 1;
                lastIndex += searchFor.length();
            }
        }

        return count;
    }
}