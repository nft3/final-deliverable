/**
 * @Author Nicholas Treu
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Deck {

    private List<Card> deck;

    // When a Deck object in instantiated, let's make a new deck
    public Deck(){
        deck = new ArrayList();
        createCardsForSuit(Suit.HEART);
        createCardsForSuit(Suit.CLUB);
        createCardsForSuit(Suit.DIAMOND);
        createCardsForSuit(Suit.SPADE);
    }

    private void createCardsForSuit(Suit s){
        for(int i = 2; i < 15; i++){
            deck.add(new Card(i, s));
        }
    }

    public void printWholeDeck(){
        for(Card c : deck){
            System.out.println(c.cardToString());
        }
    }

    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    public Card hit(){
        Card tempCard;

        // Get the card from the top of the deck, then remove it.
        tempCard = deck.get(0);
        deck.remove(0);

        return tempCard;
    }

    /**
        BELOW ARE METHODS BEING USED JUST TO TEST PROPERTIES OF THE DECK!
     */
    protected int getDeckSize(){
        return deck.size();
    }

    // This differs from hit because it doesn't remove it from the deck.
    protected Card getCard(int position){
        Card temp = deck.get(position);
        return temp;
    }

    protected List<Card> getDeck(){
        return deck;
    }
}
