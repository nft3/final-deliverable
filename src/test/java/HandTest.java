import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
/**
 * @Author Nicholas Treu
 */
public class HandTest {

    private Card one;
    private Card two;
    private Hand hand;

    // In every hand, there are two cards. Set up so there are two cards made every time we run a test
    private void makeHand(int rank1, Suit s1, int rank2, Suit s2){
        one = new Card(rank1, s1);
        two = new Card(rank2, s2);
        hand = new Hand(one, two);
    }

    @After
    public void tearDown(){
        one = null;
        two = null;
        hand = null;
        System.gc();
    }

    // Test that getHand() gets an instance of an ArrayList
    @Test
    public void testGetHand() {
        makeHand(7, Suit.HEART, 9, Suit.SPADE);
        assertEquals(ArrayList.class, hand.getHand().getClass());
    }

    @Test
    public void testFirstCard() {
        Card c1 = new Card(3, Suit.DIAMOND);
        Card c2 = new Card(7, Suit.HEART);
        Hand h1 = new Hand(c1, c2);

        assertEquals(c1, h1.firstCard()); // They should be the same object
    }

    // Testing calculating the right sum. This tests both getSum() and calcualteSum()
    @Test
    public void testCalculateSum() {
        makeHand(12, Suit.CLUB, 6, Suit.HEART);
        hand.calculateSum();
        assertEquals(16, hand.getSum());
    }

    // If there is an ace, test that the higher value it could possibly be (+ 11) is correct
    @Test
    public void testGetHighAceSumFirstCard() {
        makeHand(14, Suit.SPADE, 5, Suit.CLUB);
        hand.calculateSum();
        assertEquals(16, hand.getHighAceSum()); // 11 + 5 = 16
    }

    // If there is an ace, test that the lower value it could possibly be (+ 1) is correct
    @Test
    public void testGetLowAceSumFirstCard() {
        makeHand(14, Suit.HEART, 8, Suit.CLUB);
        hand.calculateSum();
        assertEquals(9, hand.getLowAceSum()); // 8 + 1 = 9
    }

    // If there is an ace, test that the higher value it could possibly be (+ 11) is correct
    @Test
    public void testGetHighAceSumSecondCard() {
        makeHand(5, Suit.CLUB, 14, Suit.SPADE);
        hand.calculateSum();
        assertEquals(16, hand.getHighAceSum()); // 11 + 5 = 16
    }

    // If there is an ace, test that the lower value it could possibly be (+ 1) is correct
    @Test
    public void testGetLowAceSumSecondCard() {
        makeHand(8, Suit.CLUB, 14, Suit.HEART);
        hand.calculateSum();
        assertEquals(9, hand.getLowAceSum()); // 8 + 1 = 9
    }

    // Test if there is an Ace in the first card passed into the constructor.
    @Test
    public void testIsThereAceFirstCard() {
        makeHand(14, Suit.CLUB, 4, Suit.HEART);
        assertTrue(hand.isThereAce());
    }

    // Test if there is an Ace in the second card passed into the constructor.
    @Test
    public void testIsThereAceSecondCard(){
        makeHand(14, Suit.SPADE, 9, Suit.DIAMOND);
        assertTrue(hand.isThereAce());
    }


    // Test if there is not an Ace passed into a Hand at all.
    @Test
    public void testIsThereIsNotAceFirstCard(){
        makeHand(6, Suit.SPADE, 9, Suit.DIAMOND);
        assertFalse(hand.isThereAce());
    }



    // Test that once a card is added, if there is a bust or not. Simulating a "hit" and bust
    @Test
    public void testDidBustYes() {
        makeHand(10, Suit.HEART, 12, Suit.DIAMOND);
        hand.addCard(new Card(10, Suit.CLUB));
        hand.calculateSum();
        assertTrue(hand.didBust());
    }

    // Test if two cards are added, if there is a bust or not. Simulating a "hit" and bust
    @Test
    public void testDidBustYesTwoCards() {
        makeHand(4, Suit.HEART, 8, Suit.DIAMOND);
        hand.addCard(new Card(5, Suit.CLUB));
        hand.addCard(new Card(5, Suit.HEART));
        System.out.println(hand.getSum());
        System.out.println(hand.getSizeOfHand());
        hand.getSum();
        assertTrue(hand.didBust());
    }

    // Test that once a card is added, that is does not bust. Simulating a "hit" and not bust
    @Test
    public void testDidBustNo(){
        makeHand(10, Suit.SPADE, 4, Suit.CLUB);
        hand.addCard(new Card(2, Suit.HEART));
        hand.calculateSum();
        assertFalse(hand.didBust());
    }


    // Test if two cards are added, that is does not bust. Simulating a "hit" and not bust
    @Test
    public void testDidBustNoTwoCards(){
        makeHand(10, Suit.SPADE, 4, Suit.CLUB);
        hand.addCard(new Card(2, Suit.HEART));
        hand.addCard(new Card(3, Suit.DIAMOND));
        hand.calculateSum();
        assertFalse(hand.didBust());
    }

    // Test that adding a card to the hand changes the size of the array lis
    @Test
    public void testAddCard(){
        makeHand(12, Suit.HEART, 3, Suit.CLUB);
        hand.addCard(new Card(2, Suit.CLUB));
        assertEquals(3, hand.getSizeOfHand());
    }

    // Test that adding two cards to the hand changes the size of the array list
    @Test
    public void testAddTwoCards(){
        makeHand(12, Suit.HEART, 3, Suit.CLUB);
        hand.addCard(new Card(2, Suit.CLUB));
        hand.addCard(new Card(5, Suit.DIAMOND));
        assertEquals(4, hand.getSizeOfHand());
    }

    // Test that we clear the hand after adding one card
    @Test
    public void testClearHand(){
        makeHand(6, Suit.HEART, 8, Suit.CLUB);
        hand.addCard(new Card(2, Suit.CLUB));
        hand.clearHand();
        assertEquals(0, hand.getSizeOfHand());
    }

    // Test that we clear the hand after adding one card
    @Test
    public void testClearHandInitially(){
        makeHand(6, Suit.HEART, 8, Suit.CLUB);
        hand.clearHand();
        assertEquals(0, hand.getSizeOfHand());
    }

    @Test
    public void testClearHandTwoCards(){
        makeHand(12, Suit.HEART, 3, Suit.CLUB);
        hand.addCard(new Card(2, Suit.CLUB));
        hand.addCard(new Card(5, Suit.DIAMOND));
        hand.clearHand();
        assertEquals(0, hand.getSizeOfHand());
    }

    @Test
    // Test that we have a blackjack
    public void testHaveBlackjack1(){
        makeHand(14, Suit.SPADE, 10, Suit.HEART);
        assertTrue(hand.haveBlackjack());
    }

    @Test
    // Test that there is a blackjack
    public void testHaveBlackjack2(){
        makeHand(12, Suit.CLUB, 14, Suit.DIAMOND);
        assertTrue(hand.haveBlackjack());
    }

    @Test
    // Test that there is not a blackjack
    public void testNoBlackjack1(){
        makeHand(7, Suit.HEART, 8, Suit.CLUB);
        assertFalse(hand.haveBlackjack());
    }
}