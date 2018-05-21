/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.summative1;

/**
 *
 * @author pspethmann
 */
import java.util.Scanner;
import java.text.DecimalFormat;

public class HealthyHearts {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#");

        // Get user age
        System.out.print("What is your age? ");
        int age = input.nextInt();

        // Calculate max heartrate and target boundaries
        int maxHR = 220 - age;
        float lowTarget = maxHR * 0.5f;
        float highTarget = maxHR * 0.85f;

        // Print results
        System.out.println("Your maximum heart rate should be " + maxHR + " beats per minute.");
        System.out.println("Your target HR zone is " + df.format(lowTarget) + " - " + df.format(highTarget) + " beats per minute");
    }
}
