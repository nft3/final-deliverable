import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * @Author Nicholas Treu
 */
public class UserTest {

    /**
     * To test making bets, losing money, winning money, winning by a blackjack can all be done by using the method
     * getTotalMoney(). By the change in the money, we can see if the methods work or not.
     */

    @Test
    public void testLoseMoney() {
        // Start out with a user with 120 dollars
        User user = new User(120);

        // Have this particular bet be 23 dollars and make it.
        double bet = 23;
        user.makeBet(bet);

        // Test a loss, 120 - 23 = 97. So we should have 97 dollars left
        user.loseMoney();
        assertEquals(97, user.getTotalMoney(), 0);
    }

    // Test this with a decimal
    @Test
    public void testLoseMoneyDecimal() {
        // Start out with a user with 120 dollars
        User user = new User(120.98);

        // Have this particular bet be 23 dollars and make it.
        double bet = 23.65;
        user.makeBet(bet);

        // Test a loss, 120.98 - 23.65 = 97. So we should have 97 dollars left
        user.loseMoney();

        // We cannot have 0.0001 of a monetary value, so that will be the granularity that it can be off by.
        assertEquals(97.33, user.getTotalMoney(), 0.0001);
    }

    @Test
    public void testWinMoney() {
        // Start out with a user with 120 dollars
        User user = new User(500);

        // Have this particular bet be 23 dollars and make it.
        double bet = 200;
        user.makeBet(bet);

        // Test a loss, 500 + 200 = 700. So we should have 97 dollars left
        user.winMoney();
        assertEquals(700, user.getTotalMoney(), 0);
    }

    @Test
    public void testWinMoneyDecimal() {
        // Start out with a user with 120 dollars
        User user = new User(350.87);

        // Have this particular bet be 23 dollars and make it.
        double bet = 56.76;
        user.makeBet(bet);

        // Test a loss, 350.87 + 56.76 = 407.63. So we should have 97 dollars left
        user.winMoney();

        // We cannot have 0.0001 of a monetary value, so that will be the granularity that it can be off by.
        assertEquals(407.63, user.getTotalMoney(), 0.0001);
    }

    @Test
    public void testWinBlackjack() {
        User user = new User(275);

        // Bet
        double bet = 50;
        user.makeBet(bet);

        // Test when the user gets a blackjack.
        // Blackjack pays out 1.5 times your bet. 50 * 1.5 = 75
        // 75 + 275 = 350
        user.winBlackjack();

        assertEquals(350, user.getTotalMoney(), 0);
    }


    @Test
    public void testWinBlackjackDecimal() {
        User user = new User(275.45);

        // Bet
        double bet = 67.12;
        user.makeBet(bet);

        // Test when the user gets a blackjack.
        // Blackjack pays out 1.5 times your bet. 67.12 * 1.5 = 100.68
        // 100.68 + 275.45 = 350
        user.winBlackjack();

        // Cannot have a monetary value of 0.0001 and that is okay to have an error off.
        assertEquals(376.13, user.getTotalMoney(), 0.0001);
    }

    @Test
    public void testUserGetSum() {
        // Simulate when you hit and a dealer gives you a card. Calculate the sum
        Dealer dealer = new Dealer();
        User user = new User();

        simulateDeal(user, dealer);

        if (user.getSum() < 2 || user.getSum() > 21) {
            fail();
        }
    }

    @Test
    public void testDealerGetSum() {
        // Simulate when you hit and a dealer gives you a card. Calculate the sum
        Dealer dealer = new Dealer();
        User user = new User();

        simulateDeal(user, dealer);

        if (dealer.getSum() < 2 || dealer.getSum() > 21) {
            fail();
        }
    }

    @Test
    public void testPrintHand() {
        // See UserHandTest for how to test this
        Dealer dealer = new Dealer();
        User user = new User();

        simulateDeal(user, dealer);

        final ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stdout));

        user.getSum();
        user.printHand();

        String hand = stdout.toString();
        System.setOut(null);
        CharSequence c = "User has";
        assertTrue(hand.contains(c));
    }

    @Test
    public void testSetUserHand() {
        // Get Cards from the deal and set it to be your hand
        User user = new User();
        Dealer dealer = new Dealer();
        simulateDeal(user, dealer);

        // There should be two cards in this hand
        assertEquals(2, user.getNumOfCardsInHand());
    }

    @Test
    // Test if there is a hit
    public void testSetUserHandOneHit() {
        // Get Cards from the deal and set it to be your hand
        User user = new User();
        Dealer dealer = new Dealer();
        simulateDeal(user, dealer);

        Card temp = dealer.dealCard();
        user.hit(temp);

        // There should be two cards in this hand
        assertEquals(3, user.getNumOfCardsInHand());
    }

    @Test
    // Test if there are two hits
    public void testSetUserHandTwoHits() {
        // Get Cards from the deal and set it to be your hand
        User user = new User();
        Dealer dealer = new Dealer();
        simulateDeal(user, dealer);

        Card temp = dealer.dealCard();
        user.hit(temp);
        user.hit(dealer.dealCard());

        // There should be two cards in this hand
        assertEquals(4, user.getNumOfCardsInHand());
    }

    @Test
    // Test that clearing cards works for 2 cards
    public void testClearUserHand(){
        // Get Cards from the deal and set it to be your hand
        User user = new User();
        Dealer dealer = new Dealer();
        simulateDeal(user, dealer);

        user.clearHand();
        assertEquals(0, user.getNumOfCardsInHand());
    }

    @Test
    // Test if there is a hit
    public void testClearUserHandOneHit() {
        // Get Cards from the deal and set it to be your hand
        User user = new User();
        Dealer dealer = new Dealer();
        simulateDeal(user, dealer);

        Card temp = dealer.dealCard();
        user.hit(temp);
        user.clearHand();

        // There should be two cards in this hand
        assertEquals(0, user.getNumOfCardsInHand());
    }

    @Test
    // Test if there is a hit
    public void testClearUserHandTwoHits() {
        // Get Cards from the deal and set it to be your hand
        User user = new User();
        Dealer dealer = new Dealer();
        simulateDeal(user, dealer);

        Card temp = dealer.dealCard();
        user.hit(temp);
        user.hit(dealer.dealCard());
        user.clearHand();

        // There should be two cards in this hand
        assertEquals(0, user.getNumOfCardsInHand());
    }


    private void simulateDeal(User u, Dealer d) {
        // Deal the cards alternating between the two players.
        Card user1 = d.dealCard();
        Card dealer1 = d.dealCard();
        Card user2 = d.dealCard();
        Card dealer2 = d.dealCard();

        // Set the hands
        u.setHand(user1, user2);
        d.setHand(dealer1, dealer2);
    }
}