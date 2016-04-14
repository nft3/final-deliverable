/**
 * @author Nicholas Treu
 */
public class UserHand extends Hand {

    public UserHand(Card one, Card two) {
        super(one, two);
    }

    public void displayHand() {
        // Print out the cards that are part of a user's hand
        for (Card c : getHand()) {
            System.out.println(c.cardToString());
        }

        // Calculate sum of the count
        calculateSum();

        // Print out the sum of their current hand
        if (isThereAce()) {
            System.out.println("User has: " + getLowAceSum());
            System.out.println("OR:");
            System.out.println("User has: " + getHighAceSum());
        } else {
            System.out.println("User has: " + getSum());
        }
    }
}
