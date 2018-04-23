/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.summative1;

import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author pspethmann
 */
public class DogGenetics {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String dogName;

        String[] breeds = {"Golden Retriever", "Poodle", "German Shepherd",
            "Pomeranian", "Newfoundland"
        };

        int[] percentages = new int[breeds.length];

        // Get dog's name
        System.out.print("What is your dog's name? ");
        dogName = input.nextLine();

        // Output that report is being generated
        System.out.println("Generating DNA report for " + dogName + "...\n");

        // Generate random DNA percentages
        percentages = generateRandomPercentages(breeds.length);

        // Output report
        System.out.println(dogName + " is: ");
        printReport(breeds, percentages);
    }
    
    /*
    Generates an array of random numbers that add up to 100.
    Inputs: Desired size of array
    Outputs: Array of integers representing percentages
    */
    public static int[] generateRandomPercentages(int numBreeds) {

        Random rand = new Random();
        int[] percentages = new int[numBreeds];

        int usedPercent = 0;

        for (int i = 0; i < percentages.length - 1; i++) {
            int newPercent = rand.nextInt(101 - usedPercent);
            percentages[i] = newPercent;
            usedPercent += newPercent;
        }

        percentages[percentages.length - 1] = 100 - usedPercent;

        return percentages;
    }
    
    /*
    Print each breed name and its corresponding percentage.
    Inputs: Array of breeds, Array of percentages
    Outputs: None
    */
    public static void printReport(String[] breeds, int[] percentages) {

        System.out.println("-----------------------");

        for (int i = 0; i < breeds.length; i++) {
            
            System.out.print(breeds[i] + ":");
            
            for (int j = 0; j < (18-breeds[i].length()); j++){
                System.out.print(" ");
            }
            
            System.out.println(percentages[i]+"%");
            System.out.println("-----------------------");
        }
        
        
        System.out.println("\nWow, that is QUITE the dog!");
    }
}
