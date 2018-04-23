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
public class LuckySevens {
    
    private int bet, max, rolls, rollsAtMax;
    
    public void play(){
        getBet();
        playGame();
        printResults();
    }
    
    private void getBet(){
        
        Scanner input = new Scanner(System.in);
        String userInput;
        
        System.out.print("How many dollars do you want to bet? ");
        userInput = input.nextLine();
        
        bet = Integer.parseInt(userInput);
    }
    
    private void playGame(){
        
        Random rand = new Random();
        rolls = 0;
        max = bet;
        rollsAtMax = 0;
        
        while (bet>0){
            int roll1 = rand.nextInt(6)+1;
            int roll2 = rand.nextInt(6)+1;
            
            rolls++;
            
            if (roll1+roll2 == 7){
                bet += 4;
                if (bet>max){
                    max = bet;
                    rollsAtMax = rolls;
                }
            } else {
                bet--;
            }
        }
    }
    
    private void printResults(){
        System.out.println("You are broke after "+rolls+" rolls.");
        System.out.println("You should have quit after "+rollsAtMax+" rolls"
                + " when you had "+max+" dollars.");
    }
}
