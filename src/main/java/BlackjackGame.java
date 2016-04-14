/**
 * @Author Nicholas Treu
 */

import java.text.NumberFormat;
import java.util.Scanner;

public class BlackjackGame {

    private static Scanner in;
    private static Bet bet;
    private static Dealer dealer;
    private static Deck deck;
    private static User user;
    private static DealerHand dealerHand;
    private static UserHand userHand;
    private static RecordFileManager file;

    public static void main(String args) throws InterruptedException {

        in = new Scanner(System.in);
        dealer = new Dealer();
        user = new User();
        file = new RecordFileManager("BlackjackRecords.txt");
        String startingBalanceStr, newUser, newPassword;


        // Give a little load out animation
        fancyLoadForShow();

        boolean keepPlaying = true;
        System.out.println("Welcome to a Blackjack game!");


        // Get username and password, if they don't have one, then make one for them.
        System.out.println("Please enter your username if you have one. If not please enter -1: ");
        String username = in.nextLine();
        String password;

        // Check that they have a balance and their password is correct
        if (!username.trim().equals("-1")) {
            while (true) {
                System.out.print("Please enter your password: ");
                password = in.nextLine();
                double amountInAccount;
                if (file.doesUserExist(username)) {
                    amountInAccount = file.getAmountInAccount(username, password);
                    if (amountInAccount == -1) {
                        System.out.println("Sorry, we did not find you in the system. Can you please try again?");
                        continue;
                    }
                    else {
                        break;
                    }
                }
            }
        }

        // Make a new user and add them to the system
        else {
            while (true) {
                // Enter a new username and check that it doesn't already exits
                System.out.print("Please enter a new username: ");
                newUser = in.nextLine();
                newUser.trim();
                if (file.doesUserExist(newUser)) {
                    System.out.println("Please enter another username that one already exits!");
                } else {
                    break;
                }
            }

            // Allow them to make a password
            while (true) {
                System.out.print("Please enter a new password: ");
                newPassword = in.nextLine();
                newPassword.trim();

                System.out.print("Please reenter that password: ");
                String passwordRecheck = in.nextLine();

                if (!newPassword.equalsIgnoreCase(passwordRecheck)) {
                    System.out.println("Please reenter your password to make sure they match!");
                } else {
                    break; // breaks out of inner loop
                }
            }


            double startingBalance;
            while (true) {
                try {
                    System.out.print("Please enter a balance you wish to start with: ");
                    startingBalanceStr = in.nextLine();
                    startingBalance = Double.parseDouble(startingBalanceStr);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number greater than 0 for an initial balance!");
                }
            }

            // Add the new user to the system.
            file.addUserToFile(newUser, newPassword, startingBalance);

            // Now on the actual implementation of blackjack
            while(keepPlaying){
                // The first part of a blackjack game is to get the user bets
                double userBet;
                while(true){
                    try{
                        System.out.print("Enter your bet: ");
                        userBet = Double.parseDouble(in.nextLine());
                        break;
                    } catch (NumberFormatException e){
                        System.out.println("Please enter a valid number that is greater than 0 for a bet.");
                    }
                }

                user.makeBet(userBet);
                System.out.println("You have bet $" + formatNumber(userBet));

                // Now deal the cards out and print the hands
                dealCards();
                dealer.printInitialHand();
                user.printHand();

                // Ask the user if they wish to hit or stay
                while(true) {
                    System.out.print("\nWould you like to hit or stay? ");
                    String hitOrStay = in.nextLine();
                    if(hitOrStay.trim().equalsIgnoreCase("hit")){
                        user.hit(dealer.dealCard());
                    }
                    else if(hitOrStay.trim().equalsIgnoreCase("stay")){
                        break;
                    }
                    else{
                        System.out.println("That is not a valid input please enter another");
                    }
                }


            }
        }

    }

    private static void dealCards(){
        // Get four cards. c1 and c3 go in the dealer's hand. c2 and c4 go in the user's hand
        Card c1 = dealer.dealCard();
        Card c2 = dealer.dealCard();
        Card c3 = dealer.dealCard();
        Card c4 = dealer.dealCard();

        dealer.setHand(c1, c3);
        user.setHand(c2, c4);
    }

    private static String formatNumber(double num){
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance();
        return dollarFormat.format(num);
    }

    /*
        RULES:
            - Dealer MUST stand on a 17
            - Dealer MUST hit on 16 or below

        return true (user wins) if:
            - The dealer busts
            - The dealer gets higher than the user and wins

        return false (dealer wins) if:
            - The user busts
            - The user get higher than the dealer
     */
    private static boolean doDealerHitting(){

    }

    private static void fancyLoadForShow() throws InterruptedException {
        String[] loadout = {"|", "/", "-", "\\"};

        System.out.printf("Loading the game.... |");

        for (int i = 0; i < 1000; i++) {
            for (String phase : loadout) {
                System.out.printf("\b" + phase);
                Thread.sleep(100);
            }
        }

    }
}
