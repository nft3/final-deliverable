/**
 * @author Nicholas Treu
 */

import java.util.List;
import java.util.ArrayList;

public class Hand {

    // A hand will be represent by an ArrayList of two cards
    private final List<Card> hand;
    private boolean aceFlag;
    private int sum;
    private int lowAceSum;
    private int highAceSum;

    public Hand(Card one, Card two) {
        hand = new ArrayList<Card>();
        addCard(one);
        addCard(two);
        if (one.getRank() == 14 || two.getRank() == 14)
            aceFlag = true;

        sum = 0;
        lowAceSum = 0;
        highAceSum = 0;
    }

    protected List<Card> getHand() {
        return hand;
    }

    public Card firstCard() {
        return hand.get(0);
    }

    /**
     * We have to do some calculating for ranks. Jacks, Queens, Kings are all worth 10.
     * Aces can have a value of 0 or 11.
     */

    public void calculateSum() {
        int localSum = 0, localHighAceSum = 0, localLowAceSum = 0;
        for (Card c : getHand()) {
            if (c.isUsed()) {
                if (c.getRank() >= 10) {
                    if (c.getRank() == 14) {
                        localLowAceSum = localSum + 1;
                        localHighAceSum = localSum + 11;
                        continue;
                    }
                    localSum += 10;
                } else {
                    localSum += c.getRank();
                    localLowAceSum += localSum;
                    localHighAceSum += localSum;
                }
            }
        }

        sum = localSum;
        lowAceSum = localLowAceSum;
        highAceSum = localHighAceSum;
    }

    public int getSum() {
        calculateSum();
        return sum;
    }

    public int getHighAceSum() {
        calculateSum();
        return highAceSum;
    }

    public int getLowAceSum() {
        calculateSum();
        return lowAceSum;
    }

    public boolean isThereAce() {
        return aceFlag;
    }

    public boolean didBust() {
        // If we have an ace
        if (isThereAce()) {

            // If the high ace sum is greater than 21
            if (highAceSum > 21) {

                // Now let's check if the low ace sum is greater than 21. If it's not, then we can add
                // 1 to sum and we have resolved our problem because the ace has to have a value of 1.
                if (lowAceSum < 21) {
                    sum = lowAceSum;
                    aceFlag = false;
                    return false;
                }

                // If we get here, then highAceSum is greater than 21 and lowAceSum is also greater than 21, bust.
                return true;
            }
            // If the highAceSum is not greater than 21, no bust
            else {
                return false;
            }
        }

        // No aces, check if the sum is greater than 21.
        else {
            return sum > 21;
        }
    }

    public boolean haveBlackjack(){
        boolean haveAce = false, haveTenValue = false;
        for(Card c: hand){
            if(c.getRank() == 14){
                haveAce = true;
            }
            else if(c.getRank() == 10 || c.getRank() == 11 || c.getRank() == 12
                || c.getRank() == 13){
                haveTenValue = true;
            }
        }

        return haveAce && haveTenValue;
    }

    public void addCard(Card c) {
        c.setUsed();
        hand.add(c);
    }

    public void clearHand() {
        for(Card c : hand){
            c.setUnused();
        }
        hand.clear();
    }

    /**
     * BELOW ARE METHODS TO HELP WITH TESTING!
     */

    public int getSizeOfHand() {
        return hand.size();
    }
}
