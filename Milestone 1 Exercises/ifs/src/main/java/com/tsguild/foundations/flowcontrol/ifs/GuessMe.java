/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.flowcontrol.ifs;

/**
 *
 * @author pspethmann
 */

import java.util.Scanner;

public class GuessMe {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int num = 14;
        System.out.print("Guess a number: ");
        int guess = input.nextInt();
        
        if(guess==num){
            System.out.println("Wow, nice guess! That was it!");
        } else if(guess < num){
            System.out.println("Ha, nice try - too low! I chose "+num);
        } else{
            System.out.println("Ha, nice try - too high! I chose "+num);
        }
    }
}
