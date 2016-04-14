import org.junit.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * @Author Nicholas Treu
 */
public class UserHandTest {

    UserHand test;

    // Test with an ace. OR will only appear one time in the String print out with an ace in it
    @Test
    public void testDisplayHandAce() {
        Card c1 = new Card(14, Suit.DIAMOND);
        Card c2 = new Card(9, Suit.SPADE);
        test = new UserHand(c1 ,c2);

        final ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stdout));
        test.displayHand();
        String hand = stdout.toString();
        System.setOut(null);
        CharSequence c = "OR";
        assertTrue(hand.contains(c));

    }

    // Test the String printed out without an ace will NOT contain the String OR
    @Test
    public void testDisplayHandNoAce(){
        Card c1 = new Card(10, Suit.HEART);
        Card c2 = new Card(9, Suit.CLUB);
        test = new UserHand(c1, c2);

        final ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stdout));
        test.displayHand();
        String hand = stdout.toString();
        System.setOut(null);
        CharSequence c = "OR";
        assertFalse(hand.contains(c));
    }

}