/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.ticticatoe;

import java.util.Scanner;

/**
 *
 * @author pspethmann
 */
public class HumanPlayer {
    
    public int getUserMove(int[] availableMoves){
        
        Scanner input = new Scanner(System.in);
        String userInput;
        int userChoice=-1;
        

        
        boolean valid = false;
        
        do{
            
            System.out.print("Input the number for the board "
                + "position you'd like to choose: ");
            
            try{
                userInput = input.nextLine();
                userChoice = Integer.parseInt(userInput);
                
                if (userChoice<0 || userChoice>8 || 
                        !checkIfAvailable(availableMoves,userChoice)){
                    throw new NumberFormatException();
                } else{
                    
                    valid = true;
                }
                
            } catch (NumberFormatException ex){
                System.out.println("Invalid input.");
            }
            
            
        } while (!valid);
        
        
        return userChoice;
    }
    
    private boolean checkIfAvailable(int[] availableMoves, int choice){
        
        for (int move : availableMoves){
            if (choice == move){
                return true;
            }
        }
        
        return false;
    }
}
