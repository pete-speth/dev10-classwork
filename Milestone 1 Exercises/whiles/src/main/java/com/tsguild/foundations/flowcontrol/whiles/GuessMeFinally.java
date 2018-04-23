/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.flowcontrol.whiles;

/**
 *
 * @author pspethmann
 */

import java.util.Scanner;
import java.util.Random;

public class GuessMeFinally {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner input = new Scanner(System.in);
        int guess;
        int num = rand.nextInt(201)-100;
        
        System.out.println("I've chosen a number between -100 and 100. Betch can't guess it!");
        
        do{
            System.out.print("Your guess: ");
            guess = input.nextInt();
            System.out.println();
            
            if(guess>num){
                System.out.println("Ha, nice try - too high! Try again!");
            } else if(guess<num){
                System.out.println("Ha, nice try - too low! Try again!");
            } else{
                System.out.println("Finally! It's about time you got it!");
            }  
        } while(num!=guess);
    }
}
