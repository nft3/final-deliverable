/**
 * @Author Nicholas Treu
 */
public class Bet {

    private double bet;

    public Bet(){
    }

    // Getter method
    public double getBet(){
        return this.bet;
    }

    // Setter method
    public void setBet(double bet){
        if(bet <= 0){
            throw new IllegalArgumentException("You cannot enter a negative or 0 dollar bet!");
        }

        else {
            this.bet = bet;
        }
    }

    public void clearBet(){
        this.bet = 0;
    }
}
