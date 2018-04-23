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

public class BirthStones {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        
        System.out.print("What is your birth month (#): ");
        byte month = input.nextByte();
        
        if(month == 1){
            System.out.println("Your birthstone is Garnet");
        } else if(month == 2){
            System.out.println("Your birthstone is Amethyst");
        } else if(month == 3){
            System.out.println("Your birthstone is Aquamarine");
        } else if(month == 4){
            System.out.println("Your birthstone is Diamond");
        } else if(month == 5){
            System.out.println("Your birthstone is Emerald");
        } else if(month == 6){
            System.out.println("Your birthstone is Pearl");
        } else if(month == 7){
            System.out.println("Your birthstone is Ruby");
        } else if(month == 8){
            System.out.println("Your birthstone is Peridot");
        } else if(month == 9){
            System.out.println("Your birthstone is Sapphire");
        } else if(month == 10){
            System.out.println("Your birthstone is Opal");
        } else if(month == 11){
            System.out.println("Your birthstone is Topaz");
        } else if(month == 12){
            System.out.println("Your birthstone is Turquiose");
        } else{
            System.out.println("Invalid month.");
        }
    }
    
}
