/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.flowcontrol.fors;

/**
 *
 * @author pspethmann
 */

import java.util.Scanner;

public class ForTimesFor {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        int inputNum;
        int guess;
        int counter = 0;
        
        System.out.print("Which times table should I recite? ");
        inputNum = input.nextInt();
        
        for(int i=1;i<=15;i++){
            System.out.println(i+" * "+inputNum+" is: ");
            guess = input.nextInt();
            
            if(guess == i*inputNum){
                System.out.println("Correct!");
                counter++;
            } else{
                System.out.println("Sorry, it's actually "+(inputNum*i));
            }
        }
        
        System.out.println("You got "+counter+"/15 correct.");
    }
}
