/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.ticticatoe;

import java.util.Random;

/**
 *
 * @author pspethmann
 */
public class ComputerPlayer {
    
    public int getComputerMove(int[] availableMoves){
        
        Random rand = new Random();
        int possibleMoves = availableMoves.length;
        int moveIndex =  rand.nextInt(possibleMoves);
        
        return availableMoves[moveIndex];
    }
    
    public int getIntelligentMove(int[] availableMoves, int[] board){
        
        //Check if we can win, if so make move
        for (int move : availableMoves){
            int[] tempBoard = board.clone();
            tempBoard[move] = 1;
            int result = checkWinner(tempBoard);
            if (result == 1){
                return move;
            }
            
        }
        
        // Check if user can win, if so then block
        
        for (int move : availableMoves){
            int[] tempBoard = board.clone();
            tempBoard[move] = -1;
            int result = checkWinner(tempBoard);
            if (result == 2){
                return move;
            }
            
        }
        
        // If middle is available, take it
        for (int move: availableMoves){
            if (move == 4){
                return move;
            }
        }
        
        // Move into a corner in a row/col that the user is in
        int cornerMove = checkCorners(board, availableMoves);
        if (cornerMove >= 0){
            
        }
        
        // Otherwise, take random move
        Random rand = new Random();
        int possibleMoves = availableMoves.length;
        int moveIndex =  rand.nextInt(possibleMoves);
        
        return availableMoves[moveIndex];
        
        
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
     
    private int checkCorners(int[] arr, int[] availableMoves) {
        // Return 0 for no winner, 1 for computer win, 2 for user win

        // Check horizontals
        for (int i = 0; i <= 6; i += 6) {
            int sum = arr[i] + arr[i + 1] + arr[i + 2];
            System.out.println(sum);
            if (sum == -1 && contains(availableMoves,i)) {        
                return i;
            } 
        }

        // Check verticals
        for (int i = 0; i < 3; i+=2) {
            int sum = arr[i] + arr[i + 3] + arr[i + 6];
            System.out.println(sum);
            if (sum == -1 && contains(availableMoves,i)) {
                return i;
            } 
        }
        
        return -1;
    }
    
    private boolean contains(int [] arr, int element){
        for (int num : arr){
            if (num == element){
                return true;
            }
        }
        
        return false;
    }
}
