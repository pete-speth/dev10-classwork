/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.exercises;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        BigDecimal rate, amount;
        int years, compoundFreq;
        String howToCompound;

        // Get inputs (Annual interest rate, principal amount, number of years)
        System.out.println("INTEREST CALCULATOR");
        System.out.println("-------------------");

        System.out.print("Input the annual interest rate: ");
        String userRate = input.nextLine();
        rate = new BigDecimal(userRate);

        System.out.print("Input the initial principal amount: ");
        String userAmount = input.nextLine();
        amount = new BigDecimal(userAmount);
        amount = amount.setScale(2,RoundingMode.HALF_UP);

        System.out.print("Input the number of years in the fund: ");
        String userYears = input.nextLine();
        years = Integer.parseInt(userYears);

        //Add input for how to compound interest?
        System.out.print("Will the interest be compounded "
                + "quarterly, monthly, or daily? (Q/M/D): ");
        howToCompound = input.nextLine();

        // Find frequncy for compounding
        switch (howToCompound) {
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

        for (int i = 0; i < years; i++) {

            // Ouput year number
            System.out.println("YEAR:               " + (2018 + count));

            // Output principal at beginning of year
            System.out.println("STARTING PRINCIPAL: $" + amount);

            // Calculate and output interest for the year
            BigDecimal interest;
            BigDecimal newAmount = amount;

            for (int j = 0; j < compoundFreq; j++) {
                BigDecimal freq = new BigDecimal(Integer.toString(compoundFreq));
                BigDecimal adjustedRate = rate.divide(freq,2,RoundingMode.HALF_UP);
                BigDecimal multiplier = adjustedRate.divide(new BigDecimal("100"),2,RoundingMode.HALF_UP);
                interest = newAmount.multiply(multiplier);
                newAmount = newAmount.add(interest).setScale(2,RoundingMode.HALF_UP);
            }

            System.out.println("INTEREST COLLECTED: $" + newAmount.subtract(amount));

            // Calculate and output principal at the end of the year
            amount = newAmount;
            System.out.println("ENDING PRINCIPAL:   $" + amount);

            count++;
            System.out.println("----------------------------------");
        }
    }
}
