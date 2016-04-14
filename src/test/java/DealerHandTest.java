import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.*;

/**
 * @Author Nicholas Treu
 */
public class DealerHandTest {

    @Test
    // Test that when we are displaying a DealerHand initially without any hits, that it only displays the first card
    // and the count of only the first card
    public void testDisplayInitialHand() {
        Card c1 = new Card(10, Suit.HEART);
        Card c2 = new Card(9, Suit.CLUB);
        DealerHand hand = new DealerHand(c1, c2);

        // Set up output arrays
        final ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stdout));

        hand.displayInitialHand();

        String handStr = stdout.toString();
        System.setOut(null);

        CharSequence c = "Dealer showing: 10 of Hearts";
        assertTrue(handStr.contains(c));
    }

    @Test
    // Test it prints out the correct message if sum is an ace
    public void testDisplayInitialHandAce(){
        Card c1 = new Card(14, Suit.SPADE);
        Card c2 = new Card(9, Suit.CLUB);
        DealerHand hand = new DealerHand(c1, c2);

        // Set up output arrays
        final ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stdout));

        hand.displayInitialHand();

        String handStr = stdout.toString();
        System.setOut(null);

        CharSequence c = "Current Dealer sum can be 1 or 11";
        assertTrue(handStr.contains(c));
    }

    @Test
    // Test that display hand displays the correct output
    public void testDisplayHand() {
        Card c1 = new Card(14, Suit.SPADE);
        Card c2 = new Card(9, Suit.CLUB);
        DealerHand hand = new DealerHand(c1, c2);

        // Set up output arrays
        final ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stdout));

        hand.displayHand();

        String handStr = stdout.toString();
        System.setOut(null);

        CharSequence c = "The Dealer's sum can either be:";
        assertTrue(handStr.contains(c));
    }

    @Test
    // Test that display hand displays the correct output
    public void testDisplayHandNoAce() {
        Card c1 = new Card(9, Suit.SPADE);
        Card c2 = new Card(9, Suit.CLUB);
        DealerHand hand = new DealerHand(c1, c2);

        // Set up output arrays
        final ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stdout));

        hand.displayHand();

        String handStr = stdout.toString();
        System.setOut(null);

        CharSequence c = "Dealer's sum:";
        assertTrue(handStr.contains(c));
    }

    @Test
    // Test that display hand displays correct output after
    public void testDisplayHand2() {
        Card c1 = new Card(14, Suit.SPADE);
        Card c2 = new Card(9, Suit.CLUB);
        DealerHand hand = new DealerHand(c1, c2);

        hand.addCard(new Card(5, Suit.HEART));

        // Set up output arrays
        final ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stdout));

        hand.displayHand();

        String handStr = stdout.toString();
        System.setOut(null);

        CharSequence c = "The Dealer's sum can either be: 10 20";
        assertTrue(handStr.contains(c));
    }
}