/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.ticticatoe;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author pspethmann
 */
public class Game {

    HumanPlayer human;
    ComputerPlayer comp;
    int[] board = new int[9];
    int[] availableMoves = new int[9];
    boolean gameOver = false;
    Scanner sc = new Scanner(System.in);
    int level;

    public Game() {

    }

    public Game(HumanPlayer human, ComputerPlayer comp) {
        this.human = human;
        this.comp = comp;
    }

    /*
    public Game(HumanPlayer human, IntelligentComputer iComp) {
        this.human = human;
        this.iComp = iComp;
    }
     */

    public void playGame() {

        Random rand = new Random();
        level = -1;
        boolean recievedLevel = false;

//        for (int i=0; i< board.length; i++){
//            board[i] = 0;
//        }
        for (int i = 0; i < 9; i++) {
            availableMoves[i] = i;
        }

        System.out.println("Here is the board layout: ");
        System.out.println("0|1|2");
        System.out.println("-----");
        System.out.println("3|4|5");
        System.out.println("-----");
        System.out.println("6|7|8");
        while (!recievedLevel) {
            System.out.println("\nWhat level would you like to play at? (1-10)");
            try {
                level = sc.nextInt();
                if (level < 0 || level > 10) {
                    throw new NumberFormatException();
                }
                recievedLevel = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }

        System.out.println("You are X, the computer is O.");

        // Randomize who goes first
        System.out.println("Choosing who will go first...");
        boolean computerMove = rand.nextBoolean();
        if (computerMove) {
            System.out.println("The computer goes first!");
        } else {
            System.out.println("You go first!");
        }

        do {
            takeTurn(computerMove);
            computerMove = !computerMove;
            
            if (availableMoves.length == 0){
                System.out.println("It's a tie!");
                gameOver = true;
            } 

        } while (!gameOver);

    }

    private int checkWinner(int[] arr) {
        // Return 0 for no winner, 1 for computer win, 2 for user win

        // Check horizontals
        for (int i = 0; i <= 6; i += 3) {
            int sum = arr[i] + arr[i + 1] + arr[i + 2];
            if (sum == 3) {
                return 1;
            } else if (sum == -3) {
                return 2;
            }
        }

        // Check verticals
        for (int i = 0; i < 3; i++) {
            int sum = arr[i] + arr[i + 3] + arr[i + 6];
            if (sum == 3) {
                return 1;
            } else if (sum == -3) {
                return 2;
            }
        }

        // Check diagonals
        int sum = arr[0] + arr[4] + arr[8];
        if (sum == 3) {
            return 1;
        } else if (sum == -3) {
            return 2;
        }

        sum = arr[2] + arr[4] + arr[6];
        if (sum == 3) {
            return 1;
        } else if (sum == -3) {
            return 2;
        }

        return 0;
    }

    private void printBoard() {

        for (int i = 0; i < 9; i++) {

            if (i % 3 == 0 && i != 0) {
                System.out.println("");
            }

            if (board[i] == 0) {
                System.out.print(" . ");
            } else if (board[i] == 1) {
                System.out.print(" O ");
            } else {
                System.out.print(" X ");
            }
        }

        System.out.println("\n");

    }

    private int[] removeIndex(int[] arr, int removeIndex) {
        if (removeIndex < 0 || arr.length <= removeIndex) {
            System.out.println("removeIndex() used incorrectly");
            return null;
        }
        int[] newArr = new int[arr.length - 1];
        int currIndex = 0;
        for (int i = 0; i < newArr.length; i++) {
            if (currIndex == removeIndex) {
                currIndex++;
            }
            newArr[i] = arr[currIndex];
            currIndex++;
        }
        return newArr;
    }

    private static int[] removeElement(int[] arr, int removeItem) {
        /*
       if (removeIndex < 0 || arr.length <= removeIndex) {
           System.out.println("removeIndex() used incorrectly");
           return null;
       }
         */

        int[] newArr = new int[arr.length - 1];

        int currIndex = 0;
        for (int i = 0; i < newArr.length; i++) {
            //if (currIndex == removeIndex) {
            if (arr[currIndex] == removeItem) {
                currIndex++;
            }
            newArr[i] = arr[currIndex];
            currIndex++;
        }
        return newArr;
    }

    private void takeTurn(boolean computerChoice) {

        int compChoice, userChoice;

        if (computerChoice) {
            Random rd = new Random();
            int randomNum = rd.nextInt(10) + 1;
            if(randomNum <= level) {
                compChoice = comp.getIntelligentMove(availableMoves, board);
            } else {
                compChoice = comp.getComputerMove(availableMoves);
            }
            availableMoves = removeElement(availableMoves, compChoice);
            board[compChoice] = 1;
            System.out.println("\nComputer's move: ");
            printBoard();
        } else {

            userChoice = human.getUserMove(availableMoves);
            availableMoves = removeElement(availableMoves, userChoice);
            board[userChoice] = -1;
            System.out.println("\nYour move: ");
            printBoard();
        }

        int result = checkWinner(board);

        switch (result) {
            case 0:
                break;
            case 1:
                System.out.println("Computer wins!");
                gameOver = true;
                break;
            case 2:
                System.out.println("You win!");
                gameOver = true;
                break;
            default:
                break;
        }
    }

}
