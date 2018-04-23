/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.scanner;

/**
 *
 * @author pspethmann
 */

import java.util.Scanner;

public class PassingTheTuringTest {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        
        System.out.print("What is your name? ");
        String name = input.next();
        
        System.out.print("Hi, "+name+"! I'm Peter. What's your favorite color? ");
        String color = input.next();
        
        System.out.print("Oh, "+color+"? Mine is blue! It reminds me of blueberries, which are my favorite food! What is yours? ");
        String food = input.next();
        
        System.out.print("No way! I really like "+food+" too! What is your favorite number? ");
        int num = input.nextInt();
        
        
        System.out.println(num+" is an okay number, but I like 16 better.");
        System.out.println("I'll talk to ya later "+name+"!");
    }   
}
