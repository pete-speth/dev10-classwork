/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.statstracker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author pspethmann
 */
public class Scratch {
    
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String dateStr =  fmt.format(date);
        System.out.println(date);
        System.out.println(dateStr);
        
        System.out.println(Boolean.parseBoolean("t"));
    }
}
