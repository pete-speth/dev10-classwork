/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.random;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author pspethmann
 */
public class GuessMeMore {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        int num = rand.nextInt(201)-100;
        
        System.out.print("Guess a number: ");
        int guess = input.nextInt();
        
        if(guess==num){
            System.out.println("Wow, nice guess! That was it!");
            
        } else if(guess < num){
            System.out.println("Ha, nice try - too low!");
            System.out.print("Guess a number: ");
            int newGuess = input.nextInt();
            if(newGuess==num){
            System.out.println("Wow, nice guess! That was it!");
            } else{
                System.out.println("Wrong again! I chose "+num);
            }
        } else{
            System.out.println("Ha, nice try - too high! I chose "+num);
            System.out.print("Guess a number: ");
            int newGuess = input.nextInt();
            if(newGuess==num){
            System.out.println("Wow, nice guess! That was it!");
            } else{
                System.out.println("Wrong again! I chose "+num);
            }
        }
    }
}
