/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.exercises;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author pspethmann
 */
public class InterestCalculator {

    public static void main(String[] args) {
        // Define variables
        Scanner input = new Scanner(System.in);
        DecimalFormat fmt = new DecimalFormat("#.##");
        double rate, amount;
        int years, compoundFreq;
        String howToCompound;

        // Get inputs (Annual interest rate, principal amount, number of years)
        System.out.println("INTEREST CALCULATOR");
        System.out.println("-------------------");
        
        System.out.print("Input the annual interest rate: ");
        rate = input.nextDouble();
        
        System.out.print("Input the initial principal amount: ");
        amount = input.nextDouble();
        
        System.out.print("Input the number of years in the fund: ");
        years = input.nextInt();
        
        //Add input for how to compound interest?
        System.out.print("Will the interest be compounded "
                + "quarterly, monthly, or daily? (Q/M/D): ");
        howToCompound = input.next();
        
        // Find frequncy for compounding
        switch(howToCompound){
            case "Q":
                compoundFreq = 4;
                break;
            case "M":
                compoundFreq = 12;
                break;
            case "D":
                compoundFreq = 365;
                break;
            default:
                System.out.println("Invalid input. Compounding quarterly.");
                compoundFreq = 4;
        }
        
        System.out.println("----------------------------------");
        
        // While number of years is greater than 0
        int count = 0;
        while(years>0){
            
            // Ouput year number
            System.out.println("YEAR:               "+(2018+count));
            
            // Output principal at beginning of year
            System.out.println("STARTING PRINCIPAL: $"+fmt.format(amount));
            
            // Calculate and output interest for the year
            double interest = 0;
            double newAmount = amount;
            
            for(int i=0;i<compoundFreq;i++){
                interest = newAmount*(rate/compoundFreq/100);
                newAmount += interest;
            }
   
            System.out.println("INTEREST COLLECTED: $"+fmt.format(newAmount-amount));
            
            // Calculate and output principal at the end of the year
            amount = newAmount;
            System.out.println("ENDING PRINCIPAL:   $"+fmt.format(amount));
            
            //Decrement number of years
            years--;
            count++;
            System.out.println("----------------------------------");
        }
    }
}
