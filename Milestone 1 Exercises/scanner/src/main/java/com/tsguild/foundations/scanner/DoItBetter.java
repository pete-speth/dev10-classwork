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

public class DoItBetter {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        
        System.out.print("How many miles can you run? ");
        byte miles = input.nextByte();
        System.out.println("You weakling! I can run "+(miles*2+1)+" miles easily!");
        
        System.out.print("How many hot dogs can you eat? ");
        byte hotdogs = input.nextByte();
        System.out.println("That's pathetic! I can eat "+(hotdogs*2+1)+"!");
        
        System.out.print("How many languages do you speak? ");
        byte languages = input.nextByte();
        System.out.println("You must not be very smart! I can speak "+(languages*2+1)+"!");
        
        
        }
}
