import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * @author Nicholas Treu
 */
public class CardTest {

    // Rule created to test for illegal arguments being passed to the constructor
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    // Test that the card's rank should be five
    @Test
    public void testGetRank() {
        Card card = new Card(5, Suit.SPADE);
        assertEquals(5, card.getRank());
    }

    // If a number less than 2 is enter when creating a Card object, an IllegalArgumentException should be thrown.
    // 1 is an edge case
    @Test
    public void testConstructLessThanTwoCard(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("A card's rank must be between 2 and 14.");

        new Card(1, Suit.HEART);
    }


    // If a number less than 2 is enter when creating a Card object, an IllegalArgumentException should be thrown.
    // 15 is an edge case.
    @Test
    public void testConstructMoreThanTwoCard(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("A card's rank must be between 2 and 14.");

        new Card(15, Suit.HEART);
    }

    // Test that getSuit method will return HEART
    @Test
    public void testGetSuitHeart() {
        Card card = new Card(3, Suit.HEART);
        assertEquals(Suit.HEART, card.getSuit());
    }


    // Test that getSuit method will return DIAMOND
    @Test
    public void testGetSuitDiamond() {
        Card card = new Card(3, Suit.DIAMOND);
        assertEquals(Suit.DIAMOND, card.getSuit());
    }


    // Test that getSuit method will return DIAMOND
    @Test
    public void testGetSuitClub() {
        Card card = new Card(3, Suit.CLUB);
        assertEquals(Suit.CLUB, card.getSuit());
    }


    // Test that getSuit method will return DIAMOND
    @Test
    public void testGetSuitSpade() {
        Card card = new Card(3, Suit.SPADE);
        assertEquals(Suit.SPADE, card.getSuit());
    }

    // If a Card object is made with rank 14 and Suit.SPADE, then we should see "Ace of Spades" returned
    @Test
    public void testCardToStringAceOfSpades() {
        Card card = new Card(14, Suit.SPADE);
        assertEquals("Ace of Spades", card.cardToString());
    }


    // If a Card object is made with rank 10 and Suit.DIAMOND, then we should see "10 of Diamonds" returned
    @Test
    public void testCardToStringTenOfDiamonds() {
        Card card = new Card(10, Suit.DIAMOND);
        assertEquals("10 of Diamonds", card.cardToString());
    }


    // If a Card object is made with rank 8 and Suit.HEARTS, then we should see "8 of Hearts" returned
    @Test
    public void testCardToStringEightOfHearts() {
        Card card = new Card(8, Suit.HEART);
        assertEquals("8 of Hearts", card.cardToString());
    }


    // If a Card object is made with rank 4 and Suit.CLUB, then we should see "4 of Clubs" returned
    @Test
    public void testCardToStringFourOfClubs() {
        Card card = new Card(4, Suit.CLUB);
        assertEquals("4 of Clubs", card.cardToString());
    }
}