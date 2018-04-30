/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.vendingmachine;

import java.math.BigDecimal;

/**
 *
 * @author pspethmann
 */
public class Scratch {

    public static void main(String[] args) {

        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        int pennies = 0;
        BigDecimal total =new BigDecimal("10.19");
        int cents = total.multiply(new BigDecimal("100")).intValue();

        while (cents / 25 > 0) {
            quarters++;
            System.out.println("Q");
            cents -= 25;
        }

        while (cents / 10 > 0) {
            dimes++;
            System.out.println("D");
            cents -= 10;
        }

        while (cents / 5 > 0) {
            nickels++;
            System.out.println("N");
            cents -= 5;
        }

        while (cents > 0) {
            pennies++;
            System.out.println("P");
            cents -= 1;
        }
    }
}
