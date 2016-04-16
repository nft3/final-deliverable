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

    public static void main(String[] args) throws InterruptedException {

        in = new Scanner(System.in);
        dealer = new Dealer();
        file = new RecordFileManager("BlackjackRecords.txt");
        String startingBalanceStr, newUser = "", newPassword = "";
        String username = "", password = "";

        boolean keepPlaying = true, alreadyLost = false;
        System.out.println("Welcome to a Blackjack game!");

        while (true) {
            // Get username and password, if they don't have one, add them to the system.
            System.out.print("Please enter your username if you have one. If not please enter -1: ");
            username = in.nextLine();
            double amountInAccount;

            // Check that they have a balance and their password is correct
            if (!username.trim().equals("-1")) {
                System.out.print("Please enter your password: ");
                password = in.nextLine();
                if (file.doesUserExist(username)) {
                    amountInAccount = file.getAmountInAccount(username, password);
                    if (amountInAccount == -1) {
                        System.out.println("Sorry, your usename/password was not find you in the system. " +
                                "Can you please try again?");
                        continue;
                    } else {
                        break;
                    }
                } else {
                    System.out.println("Sorry, we did not find you in the system. Can you please try again?");
                    continue;
                }
            }
            else {
                break;
            }
        }

        // Make a new account
        if (!file.doesUserExist(username)) {
            while (true) {
                // Enter a new username and check that it doesn't already exits
                System.out.print("Please enter a new username: ");
                newUser = in.nextLine();
                newUser = newUser.trim();
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
                newPassword = newPassword.trim();

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
            System.out.println("You are now added to the system and ready to play the game!");

            // Set the username and password to be what was just added to th system
            username = newUser;
            password = newPassword;
        }

        // Now on the actual implementation of blackjack
        while (keepPlaying) {

            // Print a welcome message for a user with the balance of their accoount from the disk
            System.out.println("Welcome to the game " + username + "!");
            double startingBalnce = file.getAmountInAccount(username, password);
            System.out.println("Your current balance is: " + formatDollarAmount(startingBalnce));
            user = new User(startingBalnce);

            // The first part of a blackjack game is to get the user bets
            double userBet = -1;
            while (true) {
                try {
                    System.out.print("Enter your bet: ");
                    userBet = Double.parseDouble(in.nextLine());
                    if(!user.makeBet(userBet)){
                        System.out.println("You have entered an invalid bet.");
                        System.out.println("Please enter a value that is greater than 0 and less than "
                                + formatDollarAmount(user.getTotalMoney()));
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number that is greater than 0 for a bet.");
                }
            }

            System.out.println("You have bet: " + formatDollarAmount(userBet));
            // Now deal the cards out and print the hands
            dealCards();
            dealer.printInitialHand();
            user.printHand();

            // Check for blackjacks
            if (dealer.haveBlackjack()) {
                user.loseMoney();
                System.out.println("The dealer has blackjack. You lost: " + formatDollarAmount(user.getTotalMoney()));
            } else if (user.haveBlackjack()) {
                user.winBlackjack();
                System.out.println("YOU WON ON A BLACKJACK!");
                System.out.println("Your new amount is: " + formatDollarAmount(user.getTotalMoney()));
            }

            // Update in the file
            file.updateAmountInAccount(username, user.getTotalMoney());

            // Ask the user if they wish to hit or stay
            while (true) {
                System.out.print("\nWould you like to hit or stay? ");
                String hitOrStay = in.nextLine();
                if (hitOrStay.trim().equalsIgnoreCase("hit")) {
                    user.hit(dealer.dealCard());
                    user.printHand();
                    if(user.didBust()){
                        alreadyLost = userLost();
                        break;
                    }

                } else if (hitOrStay.trim().equalsIgnoreCase("stay")) {
                    break;
                } else {
                    System.out.print("That is not a valid input please enter another: ");
                }
            }

            // Now do the logic for the dealer, return 0 means the user won, 1 dealer won, 2 there is a push
            dealer.printHand();
            if(!alreadyLost) {
                if (dealerPlay() == 0) {
                    System.out.println("Congrats! You have won!");
                    user.winMoney();
                    System.out.println("Your balance is now: " + user.getTotalMoney());
                }
                // The dealer won
                else if (dealerPlay() == 1) {
                    userLost();
                } else if (dealerPlay() == 2) {
                    System.out.println("You have pushed with the dealer.");
                }
            }

            // Update the amount in the user account
            file.updateAmountInAccount(username, user.getTotalMoney());

            // Clear both user and dealer hands
            dealer.clearHand();
            user.clearHand();

            // Ask if they would like to play again
            while (true) {
                System.out.println("\nWould you like to play again (Y/N)?");
                String YorN = in.nextLine();

                if (YorN.equalsIgnoreCase("Y")) {
                    break;
                } else if (YorN.equalsIgnoreCase("N")) {
                    System.out.println("Thank you for playing");
                    System.out.println("You have " + user.getTotalMoney() + " in your account and your " +
                            "total has been recorded in you system.");
                    keepPlaying = false;
                    break;
                } else {
                    System.out.println("Please enter either \"Y\" or \"N\" as an input");
                    continue;
                }
            }

            // Update the records before exiting.
            file.updateAmountInAccount(username, user.getTotalMoney());
        }
    }


    private static void dealCards() {
        // Get four cards. c1 and c3 go in the dealer's hand. c2 and c4 go in the user's hand
        Card c1 = dealer.dealCard();
        Card c2 = dealer.dealCard();
        Card c3 = dealer.dealCard();
        Card c4 = dealer.dealCard();

        dealer.setHand(c1, c3);
        user.setHand(c2, c4);
    }

    private static String formatDollarAmount(double num) {
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance();
        return dollarFormat.format(num);
    }

    /*
        return 0 (user wins) if:
            - The dealer busts
            - The dealer gets higher than the user and wins

        return 1 (dealer wins) if:
            - The user busts
            - The user get higher than the dealer

        return 2 if there is a push.
     */
    private static int dealerPlay() {
        // If the dealer has black, then the dealer wins.
        if(dealer.haveBlackjack()){
            return 1;
        }

        else {
            if (isPush()){
                return 2;
            }
            else {
                if(isDealerHigher()){
                    return 1;
                }
                else {
                    return 0;
                }
            }
        }
    }

    private static boolean isDealerHigher() {
        return dealer.getSum() > user.getSum();
    }

    private static boolean isPush() {
        return dealer.getSum() == user.getSum();
    }

    private static boolean userLost(){
        System.out.println("You have lost the hand.");
        user.loseMoney();
        System.out.println("Your balance is now: " + formatDollarAmount(user.getTotalMoney()));
        return true;
    }
}
