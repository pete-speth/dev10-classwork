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

public class BiggerBetterAdder {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        
        System.out.print("What is your name?: ");
        String name = input.next();
        
        System.out.print("What is your favorite type of food?: ");
        String favFood = input.next();
        
        System.out.print("How many pets do you have?: ");
        byte numPets = input.nextByte();
        
        System.out.print("Have you eaten gnocchi?(true or false): ");
        boolean eatenGnocchi = input.nextBoolean();
        
        System.out.print("At what age did you learn to whistle?: ");
        byte whistleAge = input.nextByte();
        
        System.out.println("I am "+name+".");
        System.out.println("I have "+numPets+" pet(s).");
        System.out.println("My favorite food is "+favFood+"." );
        System.out.println("It is "+eatenGnocchi+" that I have eaten gnocchi.");
        System.out.println("And when I was "+whistleAge+" years old, I learned to whistle.");
        
    }
}
