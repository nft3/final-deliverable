/**
 * @Author Nicholas Treu
 * A User can have a Hand and a Bet
 */
public class User implements Player {

    private Bet userBet;
    private UserHand userHand;
    private double totalMoney;

    public User(double totalMoney){
        if(totalMoney <= 0){
            throw new IllegalArgumentException("Your total money must be greater than 0.");
        }
        this.userBet = new Bet();
        this.totalMoney = totalMoney;
    }

    public User(){
        this.userBet = new Bet();
    }

    public void makeBet(double bet){
        userBet.setBet(bet);
    }

    public void loseMoney(){
        totalMoney = totalMoney - userBet.getBet();
    }

    // Winning money you get your bet back, plus what you bet from the dealer
    public void winMoney(){
        totalMoney = totalMoney + userBet.getBet();
    }

    // Getting blackjack pays out 1.5 : 1
    public void winBlackjack(){
        // Add to total money 1.5 times your bet, plus getting your money back that you bet.
        totalMoney = totalMoney + (1.5 * userBet.getBet());
    }

    public double getTotalMoney(){
        return totalMoney;
    }

    public int getSum(){
        userHand.calculateSum();
        return userHand.getSum();
    }

    public void printHand(){
        userHand.displayHand();
    }

    public void setHand(Card c1, Card c2){
        userHand = new UserHand(c1, c2);
    }

    public void clearHand(){
        userHand.clearHand();
    }

    public void hit(Card c){
        userHand.addCard(c);
    }

    /**
     * HELPERS TO USE FOR TESTING!
     */

    protected int getNumOfCardsInHand(){
        return userHand.getSizeOfHand();
    }
}
