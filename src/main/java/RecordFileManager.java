/**
 * @Author Nicholas Treu
 * We can log into this blackjack game and get a previous bet that was made before.
 * Should be able to write/read username, password, amount in account to a simple test file.
 */

import java.io.*;
import java.util.*;

public class RecordFileManager {

    private static File file;

    public RecordFileManager(String fileName){
        try{
            // Create an instance of a File object using the name of the file.
            file = new File(fileName);

            if(!file.exists()){
                file.createNewFile();
            }
        }

        catch(IOException e){
            e.printStackTrace();
        }
    }

    public double getAmountInAccount(String userName, String password) {
        String currentLine;
        Scanner in;
        try {
            // Create a Scanner object to read in the ine
            in = new Scanner(file);
            while(in.hasNextLine()){
                currentLine = in.nextLine();

                // Now that we got a current line, let's separate it on a tab
                String[] fileLine = currentLine.split(" ");
                String localUserName = fileLine[0];
                String localUserPassword = fileLine[1];
                double localAccountMoney = Double.parseDouble(fileLine[2]);

                // Found the right account, return the amount in it
                if(userName.equals(localUserName) && password.equals(localUserPassword)){
                    // First close the file close the file
                    in.close();

                    // return the amount in the account
                    return localAccountMoney;
                }
            }

            in.close();
            return -1;
        }

        catch(IOException e){
            e.printStackTrace();
        }

        return -1;
    }

    public boolean doesUserExist(String userName){
        String currentLine;
        Scanner in;
        try {
            // Create a Scanner object to read in the ine
            in = new Scanner(file);
            while(in.hasNextLine()){
                currentLine = in.nextLine();

                // Now that we got a current line, let's separate it on a tab
                String[] fileLine = currentLine.split(" ");
                String localUserName = fileLine[0];
                String localUserPassword = fileLine[1];
                double localAccountMoney = Double.parseDouble(fileLine[2]);

                // Found the right account, return the amount in it
                if(userName.equals(localUserName)){
                    // First close the file close the file
                    in.close();

                    // return the amount in the account
                    return true;
                }
            }

            in.close();
            return false;
        }

        catch(IOException e){
            e.printStackTrace();
        }

        return false;
    }

    // Add user to the file given their userName, password and amount they are gambling with
    public boolean addUserToFile(String userName, String password, double deposit) {
        boolean returnValue = false;
        if (!doesUserExist(userName) && validUserName(userName) && validPassword(password) && validDeposit(deposit)) {

            // Format the String correctly
            String outString = userName + " " + password + " " + Double.toString(deposit);
            BufferedWriter bw;
            try {
                bw = new BufferedWriter(new FileWriter(file, true));

                // Write it to the end of the file
                bw.write(outString);
                bw.newLine();
                bw.flush();
                bw.close();
                returnValue = true;
            }

            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return returnValue;
    }


    // Use a PrintWriter to write over everything that is in the text file.
    // Not very efficient, but it will work.
    public boolean updateAmountInAccount(String userName, double deposit){

        String currentLine;
        Scanner in;
        List<String> lines = new ArrayList();
        boolean returnValue = false;

        try {
            // Create a Scanner object to read in the ine
            in = new Scanner(file);
            while(in.hasNextLine()){
                currentLine = in.nextLine();

                // Now that we got a current line, let's separate it on a tab
                String[] fileLine = currentLine.split(" ");
                String localUserName = fileLine[0];
                String localUserPassword = fileLine[1];
                double localAccountMoney = Double.parseDouble(fileLine[2]);

                if(localUserName.equals(userName) && validUserName(userName)){
                    currentLine = localUserName + " " + localUserPassword + " " + deposit;
                    returnValue = true;
                }

                lines.add(currentLine);
            }

            in.close();

            // Now we can write over the entire file with the updated deposit value.
            FileWriter writer = new FileWriter(file);

            for(String line : lines){
                writer.write(line + "\n");
            }

            writer.close();
            return returnValue;
        }

        catch(IOException e){
            e.printStackTrace();
        }

        return returnValue;
    }

    private boolean validUserName(String username){
        return !username.contains(" ");
    }

    private boolean validPassword(String password){
        return !password.contains(" ");
    }

    private boolean validDeposit(double deposit){
        return deposit > 0;
    }
    /**
     * METHODS THAT HELP WITH TESTING
     */

    // Delete the file when we want to test it and make a new one
    protected void deleteFile(){
        if(file.exists()){
            file.delete();
        }
    }
}
