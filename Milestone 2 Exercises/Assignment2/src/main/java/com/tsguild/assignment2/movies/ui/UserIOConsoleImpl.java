/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.assignment2.movies.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author pspethmann
 */
public class UserIOConsoleImpl implements UserIO {

    private Scanner input = new Scanner(System.in);

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public double readDouble(String prompt) {
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

    @Override
    public double readDouble(String prompt, double min, double max) {

        boolean valid = false;
        double result = 0;

        do {

            result = readDouble(prompt);
            if (result >= min && result <= max) {
                valid = true;
            } else {
                System.out.printf("\nThe value must be between %f and %f.\n",
                        min, max);
            }
        } while (!valid);

        return result;
    }

    @Override
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

    @Override
    public int readInt(String prompt, int min, int max) {

        boolean valid = false;
        int result = 0;

        do {

            result = readInt(prompt);
            if (result >= min && result <= max) {
                valid = true;
            } else {
                System.out.printf("\nThe value must be between %d and %d.\n",
                        min, max);
            }
        } while (!valid);

        return result;
    }

    @Override
    public String readString(String prompt) {
        System.out.print(prompt);
        return input.nextLine();
    }

    @Override
    public LocalDate readLocalDate(String prompt, DateTimeFormatter format) {

        boolean valid = false;
        LocalDate ld = null;

        do {
            String value = readString(prompt);
            try {
                ld = LocalDate.parse(value, format);
                valid = true;
            } catch (DateTimeParseException ex) {
                System.out.println("Invalid. Date format must be MM/dd/yyyy");
            }
        } while (!valid);

        return ld;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {

        boolean valid = false;
        BigDecimal bd = null;

        do {
            String value = readString(prompt);
            try {
                bd = new BigDecimal(value);
                valid = true;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid. Please enter a number.");
            }
        } while (!valid);

        return bd;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, int min, int max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
