/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.refactorintoobjects;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author pspethmann
 */
public class RockPaperScissors {
    
    public void play() {

        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        int numRounds;

        // while they want to keep playing
        while (true) {

            int userInt, compInt, winner;
            int userWins = 0;
            int compWins = 0;
            int ties = 0;

            // Get number of rounds
            numRounds = getNumRounds();

            // for number of rounds
            for (int i = 0; i < numRounds; i++) {

                // Get choices
                userInt = getUserChoice();
                compInt = rand.nextInt(3) + 1;

                // Print both choices
                printChoices(userInt, compInt);

                // Compare choices to determine winner
                winner = determineWinner(userInt, compInt);
                
                // Print and save result
                if (winner == 1) {
                    System.out.println("You win the round!");
                    userWins++;
                } else if (winner == 2) {
                    System.out.println("The computer wins the round!");
                    compWins++;
                } else {
                    System.out.println("The round was a draw!");
                    ties++;
                }

                System.out.println("");
            }

            // Print final stats
            printStats(userWins, compWins, ties);

            // Declare overall winner
            if (userWins > compWins) {
                System.out.println("You are the winner!");
            } else if (compWins > userWins) {
                System.out.println("The computer is the winner!");
            } else {
                System.out.println("This match was a draw!");
            }

            // Ask to play again
            playAgain();
        }

    }
    
    /*
    Gets desired number of rounds from users. Exits the program if the number is
    outside of range 1-10.
    Inputs: None
    Outputs: Desired number of rounds
    */
    private int getNumRounds() {

        Scanner input = new Scanner(System.in);

        // Ask for number of rounds
        System.out.print("How many rounds would you like to play? (1-10): ");
        int numRounds = input.nextInt();

        // Make sure number is between 1-10
        if (numRounds < 1 || numRounds > 10) {
            System.out.println("Invalid input. Quitting the game.");
            System.exit(0);
        }

        return numRounds;
    }
    
    /*
    Gets input from the user and converts it into corresponding integer.
    Inputs: None
    Outputs: Integer representing user choice
    */
    private int getUserChoice() {

        Scanner input = new Scanner(System.in);

        System.out.print("Take your pick! "
                + "(r)ock, (p)aper, or (s)cissors? ");
        String userChoice = input.next();

        // Convert user choice to an integer (Rock=1,Paper=2,Scissors=3)
        int userInt = convertChoiceToInt(userChoice);

        return userInt;
    }
    
    /*
    Converts choice of (r)ock, (p)aper, or (s)cissors to an integer.
    Inputs: String representing choice (R,r,P,p,S,s)
    Outputs: Integer representing choice
    */
    private int convertChoiceToInt(String userChoice) {

        Random rand = new Random();

        switch (userChoice) {
            case "R":
            case "r":
                return 1;
            case "P":
            case "p":
                return 2;
            case "S":
            case "s":
                return 3;
            default:
                System.out.println("Invalid input. Randomly assigning choice.");
                return rand.nextInt(3) + 1;
        }
    }
    
    /*
    Converts outputs of 1, 2, or 3 to Rock, Paper, or Scissors
    Inputs: Integer representing choice
    Outputs: Name of choice
    */
    private String convertIntToChoice(int choiceInt) {
        switch (choiceInt) {
            case 1:
                return "Rock";
            case 2:
                return "Paper";
            case 3:
                return "Scissors";
            default:
                return "Invalid.";
        }
    }
    
    /*
    Prints choices for user and computer
    Inputs: Integer for user choice, Integer for computer choice
    Outputs: None
    */
    private void printChoices(int userInt, int compInt){
        String userChoice = convertIntToChoice(userInt);
        String compChoice = convertIntToChoice(compInt);
        System.out.println("You chose " + userChoice);
        System.out.println("The computer chose " + compChoice);
    }

    
    /*
    Determine who won. Return 0 for a tie, 1 for a user win, or 2 for a 
    computer win. 
    
    Based off of the key (Rock=1,Paper=2,Scissors=3), you can subtract the user 
    choice from the computer choice to determine the winner using 
    (-2=comp, -1=user, 0=tie, 1=comp, 2=user)
    
    Inputs: Integer for user choice, Integer for computer choice
    Outputs: Integer representing winner
    */
    private int determineWinner(int userIntChoice, int compIntChoice) {
        int diff = compIntChoice - userIntChoice;

        switch (diff) {
            case 0:
                // Tie (3 in 9 chance)
                return 0;
            case 1:
                // Computer win (2 in 9 chance)
                return 2;
            case -1:
                // User win (2 in 9 chance)
                return 1;
            case 2:
                // User win (1 in 9 chance)
                return 1;
            case -2:
                // Computer win (1 in 9 chance)
                return 2;
        }

        return 0;
    }

    /*
    Print out final statistics for entire match.
    Inputs: User wins, Computer wins, Ties
    Outputs: None
    */
    private void printStats(int userWins, int compWins, int ties) {
        System.out.println("FINAL STATS:");
        System.out.println("-------------------");
        System.out.println("User Wins    |  " + userWins);
        System.out.println("-------------------");
        System.out.println("Computer Wins|  " + compWins);
        System.out.println("-------------------");
        System.out.println("Ties:        |  " + ties);
        System.out.println("-------------------");
    }
    
    /*
    Continue playing if user wants, otherwise quit the program
    Inputs: None
    Outputs: None
    */
    private void playAgain() {
        
        Scanner input = new Scanner(System.in);
        
        // Ask to play again
        System.out.print("Would you like to play again? (y/n) ");
        String playAgain = input.next();

        // if not Y or y, break from loop
        if (!playAgain.equalsIgnoreCase("y")) {
            System.out.println("Thanks for playing!");
            System.exit(0);
        } else {
            System.out.println("");
        }
    }
}
