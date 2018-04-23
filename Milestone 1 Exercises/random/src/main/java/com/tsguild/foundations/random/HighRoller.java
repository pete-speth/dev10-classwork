/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.random;

/**
 *
 * @author pspethmann
 */

import java.util.Random;
import java.util.Scanner;

public class HighRoller {

    public static void main(String[] args) {
        
        Random diceRoller = new Random();
        Scanner input = new Scanner(System.in);
        int sides;
        
        System.out.print("How many sides does the die have? ");
        sides = input.nextInt();

        int rollResult = diceRoller.nextInt(sides) + 1;

        System.out.println("TIME TO ROOOOOOLL THE DICE!");
        System.out.println("I rolled a " + rollResult);

        if (rollResult == 1) {
            System.out.println("You rolled a critical failure!");
        } else if(rollResult == sides){
            System.out.println("You rolled a critical! Nice job!");
        }
    }
}
