/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.vendingmachine.model;

import java.math.BigDecimal;

/**
 *
 * @author pspethmann
 */
public class Change {

    private int quarters;
    private int dimes;
    private int nickels;
    private int pennies;
    
    public Change(BigDecimal total) {

        int cents = total.multiply(new BigDecimal("100")).intValue();

        while (cents / 25 > 0) {
            quarters++;
            cents -= 25;
        }

        while (cents / 10 > 0) {
            dimes++;
            cents -= 10;
        }

        while (cents / 5 > 0) {
            nickels++;
            cents -= 5;
        }

        while (cents > 0) {
            pennies++;
            cents -= 1;
        }
    }

    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public int getPennies() {
        return pennies;
    }
    
    

    
}
