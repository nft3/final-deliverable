/**
 * @Author Nicholasa Treu
 *
 */
public class DealerHand extends Hand {

    private int initialSum;

    public DealerHand(Card one, Card two) {
        super(one, two);
        initialSum = getInitialSum();
    }

    public void displayInitialHand() {
        System.out.println("Dealer showing: " + firstCard().cardToString());
        if(initialSum == 1){
            System.out.println("Current Dealer sum can be 1 or 11");
        }
        else{
            System.out.println("Current Dealer sum: " + initialSum);
        }

    }

    public void displayHand() {
        for (Card c : getHand()) {
            System.out.println(c.cardToString());
        }
        if(getLowAceSum() < 21 && getHighAceSum() < 21 && isThereAce()) {
            System.out.println("The Dealer's sum can either be: " + getLowAceSum() + " " + getHighAceSum());
        }
        else {
            System.out.println("Dealer's sum: " + getSum());
        }
    }


    private int getInitialSum() {
        Card c = getHand().get(0);
        if (c.getRank() >= 10) {
            if(c.getRank() == 14) {
                return 1;
            }
            return 10;
        }
        else {
            return c.getRank();
        }
    }
}
