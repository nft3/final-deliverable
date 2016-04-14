import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @Author Nicholas Treu
 */
public class DealerTest {

    @Test
    // Assert what is being returned from the dealCard method is actally and instance of
    // a card
    public void testDealCard() {
        Dealer dealer = new Dealer();
        Card c = dealer.dealCard();

        assertEquals(Card.class, c.getClass());
    }

    @Test
    public void testTakeCard() {
        Dealer dealer = new Dealer();
        dealer.setHand(dealer.dealCard(), dealer.dealCard());

        assertEquals(2, dealer.getNumCardsInHand());
    }

    @Test
    // Test also with an additional hit
    public void testTakeCardWithHit() {
        Dealer dealer = new Dealer();
        dealer.setHand(dealer.dealCard(), dealer.dealCard());
        dealer.takeCard();

        assertEquals(3, dealer.getNumCardsInHand());
    }

    @Test
    public void testSetHand() {
        Dealer dealer = new Dealer();
        dealer.setHand(dealer.dealCard(), dealer.dealCard());

        assertEquals(2, dealer.getNumCardsInHand());
    }

    @Test
    public void testGetSum() {
        Dealer dealer = new Dealer();
        dealer.setHand(dealer.dealCard(), dealer.dealCard());

        if(dealer.getSum() < 2 || dealer.getSum() > 21){
            fail();
        }
    }

    @Test
    public void testShuffleCards() {
        Dealer dealer = new Dealer();
        Dealer dealer1 = new Dealer();

        dealer.shuffleCards();
        dealer1.shuffleCards();
        // There is a chance that the top card on the Deck of both Dealers are the same. I am going to print the
        // String out so that we can see that they are different. If the test fails and the same String is printed out
        // we can rerun the test with confidence knowing that it so happened to shuffle that way.
        // Get the top card from each

        String cardD = dealer.dealCard().cardToString();
        String cardD1 = dealer1.dealCard().cardToString();
        // Assert that these two Strings aren't the same, then they aren't the same card and shuffled differently
        assertNotEquals(cardD, cardD1);
    }

    @Test
    public void testShuffleCardsOneDealer() {
        Dealer dealer = new Dealer();

        Card card1 = dealer.dealCard();
        String cardStr1 = card1.cardToString();

        // Shuffle the cards again
        dealer.shuffleCards();

        Card card2 = dealer.dealCard();
        String cardStr2 = card2.cardToString();

        // There should be no doubt here that shuffle works because we already removed a card from the Deck.
        // it should NEVER be the same.

        // Assert that these two Strings aren't the same, then they aren't the same card and shuffled differently
        assertNotEquals(cardStr1, cardStr2);
    }

    @Test
    public void testPrintHand() {
        Dealer dealer = new Dealer();
        dealer.setHand(dealer.dealCard(), dealer.dealCard());

        dealer.printInitialHand();

        final ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stdout));

        dealer.printHand();

        String hand = stdout.toString();
        System.setOut(null);

        CharSequence c = "Dealer's sum";
        assertTrue(hand.contains(c));
    }
}