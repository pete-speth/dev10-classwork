/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.flowcontrol.whiles;

import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author pspethmann
 */
public class BewareTheKraken {
     public static void main(String[] args) {

        System.out.println("Already, get those flippers and wetsuit on - we're going diving!");
        System.out.println("Here we goooOOooOooo.....! *SPLASH*");
        
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        int depthDivedInFt = 0;
        String stop;

        // Turns out the ocean is only so deep, 36200 at the deepest survey,
        // so if we reach the bottom ... we should probably stop.
        
        // Nothing changes if condition change to true. Still break at 20k ft
        while(depthDivedInFt < 36200){
            System.out.println("So far, we've swam " + depthDivedInFt + " feet");

            if(depthDivedInFt >= 20000){
                System.out.println("Uhhh, I think I see a Kraken, guys ....");
                System.out.println("TIME TO GO!");
                break;
            }
            
            String fish;
            int fishType = rand.nextInt(3);
            switch(fishType){
                case 0:
                    fish = "Pufferfish";
                    break;
                case 1:
                    fish = "Manta Ray";
                    break;
                case 2:
                    fish = "Clown Fish";
                    break;
                default:
                    fish = "Shark";
            }
            System.out.println("Woah, look at that "+fish+"!");
            
            System.out.print("Do you want to stop? (y/n): ");
            stop = input.next();
            if(stop.equals("y")){
                System.out.println("Heading back to the surface!");
                break;
            }

            // I can swim, really fast! 500ft at a time!
            depthDivedInFt += 1000;
        }
        System.out.println("");
        System.out.println("We ended up swimming " + depthDivedInFt + " feet down.");
        System.out.println("I bet we can do better next time!");
    }
}
