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
        this.totalMoney = numberFormat(totalMoney);
    }

    public User(){
        this.userBet = new Bet();
    }

    // Return true if the bet is less than the total amount in the account and greater than 0.
    //return false other wise
    public boolean makeBet(double bet){
        if(bet > 0 && bet <= totalMoney){
            userBet.setBet(numberFormat(bet));
            return true;
        }
        else {
            return false;
        }
    }

    public boolean didBust(){
        return userHand.didBust();
    }

    public void loseMoney(){
        totalMoney = (totalMoney - numberFormat(userBet.getBet()));
    }

    // Winning money you get your bet back, plus what you bet from the dealer
    public void winMoney(){
        totalMoney = (totalMoney + numberFormat(userBet.getBet()));
    }

    // Getting blackjack pays out 1.5 to 1
    public void winBlackjack(){
        // Add to total money 1.5 times your bet, plus getting your money back that you bet.
        totalMoney = (totalMoney + (1.5 * numberFormat(userBet.getBet())));
    }

    public boolean haveBlackjack(){
        return userHand.haveBlackjack();
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

    private double numberFormat(double number){
        return Math.round(number * 100.0) / 100.0;
    }

    /**
     * HELPERS TO USE FOR TESTING!
     */

    protected int getNumOfCardsInHand(){
        return userHand.getSizeOfHand();
    }
}
