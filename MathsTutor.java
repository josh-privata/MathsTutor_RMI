import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.SecureRandom;
import java.util.*;

/**
 * @filename MathsTutor.java
 *
 * @application RMI-MathsTutor
 *
 * @author Mary Tom created 28th February 2016
 *
 * @description This program creates a MathsTutor class to generate double digit
 * arithmetic problems and the main method demonstrates the use by looping
 * through generating and testing five problems for a chosen arithmetic
 * operation.
 *
 * @modified Josh Cannons April 9th 2016. Ammendments allow for inclusion into
 * MathsTutor_Server.Server.class to provide simplified client/server
 * functionality.
 * 
 * @modified Josh Cannons May 1st 2016. Ammendments allow for serializable
 * object transmission over RMI.
 * 
 */
public class MathsTutor extends UnicastRemoteObject implements MathsTutorInterface {

    // User Variables
    private static final int LOWEST_INTEGER = 10;
    private static final int HIGHEST_INTEGER = 100;
    private static final int NUMBER_OF_QUESTIONS = 10;
    private static final String MENU = "Please choose from the selection below\n"
            + "Enter 'a' or 'A' for Addition\n"
            + "Enter 's' or 'S' for Subtraction\n"
            + "Enter 'm' or 'M' for Multiplication\n"
            + "Enter 'd' or 'D' for Division\n"
            + "Enter 'q' or 'Q' to Quit the program\n"
            + "Please make your selection : ";

    // System Variables
    private int valueOne, valueTwo, right;
    private char symbol;
    private final SecureRandom rand;
    private String result, operation, allResults;
    private String user;
    private int attempt;

    // Constructor allocates memory for securerandom and initiates right to 0;     
    public MathsTutor() throws RemoteException {
        rand = new SecureRandom();
        attempt = 0;
        allResults = "";
    }

    // Simple getters
    @Override
    public int getNumberOfQuestions() {
        return NUMBER_OF_QUESTIONS;
    }

    @Override
    public String getMenu() {
        return MENU;
    }

    @Override
    public int getRight() {
        return right;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public int getAttempt() {
        return attempt;
    }
    
    @Override
    public String getResult() {
        return result;
    }

    // Simple setters
    private void setValueOne(int one) {
        this.valueOne = one;
    }

    private void setValueTwo(int two) {
        this.valueTwo = two;
    }

    @Override
    public void setRight(int right) {
        this.right = right;
    }
    
     @Override
    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }
    
    @Override
    public String setUser(String user) {
        this.user = user;
        String s = "Welcome ";
        return s + user;
    }

    // Iterate attempt by one
    @Override
    public void iterateAttempt(){
        ++this.attempt;
    }        

    // Method to set the mathematical operation 
    @Override
    public void setOperation(String op) {
        
        char ch = op.trim().toLowerCase().charAt(0);
        symbol = '+';
        switch (ch) {
            case 'a':
                symbol = '+';
                operation = "Addition";
                break;
            case 's':
                symbol = '-';
                operation = "Subtration";
                break;
            case 'm':
                symbol = '*';
                operation = "Multiplication";
                break;
            case 'd':
                symbol = '/';
                operation = "Division";
                break;
        }
        
    }

    // Method sets a problem with two random numbers.
    @Override
    public String setProblem() {
        Date today = new Date();
        rand.setSeed(today.getTime());
        setValueOne(LOWEST_INTEGER + rand.nextInt(HIGHEST_INTEGER - LOWEST_INTEGER));
        setValueTwo(LOWEST_INTEGER + rand.nextInt(HIGHEST_INTEGER - LOWEST_INTEGER));
        return String.format("%d %s %d = ", valueOne, symbol, valueTwo);
    }

    // Method returns 'correct' or 'incorrect' based on the answer.
    @Override
    public String checkAnswer(int answer) {
        result = " incorrect";
        switch (symbol) {
            case '+':
                if (valueOne + valueTwo == answer) {
                    result = " correct";
                    ++right;
                }
                break;
            case '-':
                if (valueOne - valueTwo == answer) {
                    result = " correct";
                    ++right;
                }
                break;
            case '*':
                if (valueOne / valueTwo == answer) {
                    result = " correct";
                    ++right;
                }
                break;
            case '/':
                if (valueOne * valueTwo == answer) {
                    result = " correct";
                    ++right;
                }
                break;
        }
        return result;
    }

    // Compiles ongoing results summary for session
    @Override
    public void saveResult(){
        allResults += attempt + "\t\t"
                   + operation + "\t"
                   + right + " out of " + NUMBER_OF_QUESTIONS + "\n";
        }
    
    // Returns results for a single run of questions
    @Override
    public String printResult(){
        return  user + "'s results for this round\nAttempt\t\tOperation\tResult\n"
                + attempt + "\t\t" 
                + operation + "\t"
                + right + " out of " + NUMBER_OF_QUESTIONS + "\n";
    }
    
    // Returns results for the entire user session
    @Override
    public String printAllResults(){
        String s = "\n" + user + "'s results for this session\n\n"
                + "Attempt\t\tOperation\tScore\n"
                + allResults;
        System.out.println(s + "\n[+] " + user +" has successfully disconnected "
                             + "from the RMI registry");
        return s;
    }
}
