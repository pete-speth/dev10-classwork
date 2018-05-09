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

public class AllTheTrivia {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        
        System.out.print("What is the tallest mountain on earth? ");
        String mountain = input.next();
        
        System.out.print("What is the Minnesota state bird? ");
        String bird = input.next();
        
        System.out.print("What is 6*3? ");
        byte num = input.nextByte();
        
        System.out.println("Woah, I had no idea that there were "+num+" "+bird+"s for every "+mountain+" in Wisconsin!");
        
    }   
}
