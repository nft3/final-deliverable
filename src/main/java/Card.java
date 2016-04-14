/**
 * @Author Nicholas Treu
 * A card has to have a rank (Ace, 2, 3, 4, 5....), a suit and a value that it is given
 * in blackjack. In blackjack, all face cards are worth 10, with an Ace either being 1 or 11.
 */
public class Card {

    private int rank;
    private Suit suit;
    private boolean used;

    // Constructor for a card
    public Card(int rank, Suit s){
        if(rank < 2 || rank > 14){
            throw new IllegalArgumentException("A card's rank must be between 2 and 14.");
        }
        this.rank = rank;
        this.suit = s;
    }

    public int getRank(){
        return rank;
    }

    public Suit getSuit(){
        return suit;
    }

    private String suitToString() {
        if(suit == Suit.DIAMOND){
            return "Diamonds";
        }
        else if(suit == Suit.HEART){
            return "Hearts";
        }
        else if(suit == Suit.CLUB){
            return "Clubs";
        }
        else if(suit == Suit.SPADE){
            return "Spades";
        }
        else {
            return "Something went horribly, horribly wrong";
        }
    }

    public void setUsed(){
        this.used = true;
    }

    public void setUnused(){
        this.used = false;
    }

    public boolean isUsed(){
        return this.used;
    }

    private String rankToString() {
        if(rank == 11){
            return "Jack";
        }
        else if(rank == 12){
            return "Queen";
        }
        else if (rank == 13){
            return "King";
        }
        else if(rank == 14){
            return "Ace";
        }
        else {
            return Integer.toString(rank);
        }
    }

    public String cardToString(){
        String card = "";
        card += rankToString();
        card += " of ";
        card += suitToString();

        return card;
    }
}
