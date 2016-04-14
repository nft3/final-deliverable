/**
 * @Author Nicholas Treu
 * A Dealer in a game of blackjack has his own dealerHand and gives a User a card if there is a hit.
 * A Dealer needs to be "in charge" of a Deck of cards.
 *
 */

public class Dealer implements Player {

    private DealerHand dealerHand;
    private Deck deck;

    public Dealer(){
        deck = new Deck();
        deck.shuffleDeck();
    }

    // Use when dealing a card to a User
    public Card dealCard(){
        return deck.hit();
    }

    // Use after the initial dealing of card
    public void takeCard(){
        dealerHand.addCard(deck.hit());
    }

    // Use to setup the hand
    public void setHand(Card c1, Card c2){
        dealerHand = new DealerHand(c1, c2);
    }

    public int getSum(){
        dealerHand.calculateSum();
        return dealerHand.getSum();
    }

    public void shuffleCards(){
        deck.shuffleDeck();
    }

    public void printHand(){
        dealerHand.displayHand();
    }

    public void printInitialHand(){
        dealerHand.displayInitialHand();
    }

    /**
     * METHODS TO HELP WITH TESTING!
     */

    protected int getNumCardsInHand(){
        return dealerHand.getSizeOfHand();
    }

    protected Deck getDeck(){
        return deck;
    }
}

