/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.consoleutils;

import java.util.Scanner;

/**
 *
 * @author pspethmann
 */
public class ConsoleIO {

    private Scanner input = new Scanner(System.in);

    public String readString(String prompt) {
        System.out.print(prompt);
        return input.nextLine();
    }

    public int readInt(String prompt) {
        boolean valid = false;
        int result = 0;
        do {
            String value = null;
            try {
                value = readString(prompt);
                result = Integer.parseInt(value);
                valid = true;
            } catch (NumberFormatException ex) {
                System.out.printf("\nThe value of '%s' is not an integer. ",
                        value);
            }
        } while (!valid);

        return result;
    }

    public int readInt(String prompt, int min, int max) {

        boolean valid = false;
        int result = 0;

        do {

            result = readInt(prompt);
            if (result >= min && result <= max) {
                valid = true;
            } else {
                System.out.printf("\nThe value must be between %d and %d.",
                        min, max);
            }
        } while (!valid);

        return result;
    }
    
    public double readDouble(String prompt){
        boolean valid = false;
        double result = 0;
        do {
            String value = null;
            try {
                value = readString(prompt);
                result = Double.parseDouble(value);
                valid = true;
            } catch (NumberFormatException ex) {
                System.out.printf("\nThe value of '%s' is not a double. ",
                        value);
            }
        } while (!valid);

        return result;
    }
}
