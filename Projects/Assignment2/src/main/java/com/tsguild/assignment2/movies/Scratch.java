/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.assignment2.movies;

import com.tsguild.assignment2.movies.ui.UserIO;
import com.tsguild.assignment2.movies.ui.UserIOConsoleImpl;
import java.math.BigDecimal;

/**
 *
 * @author pspethmann
 */
public class Scratch {
    
    public static void main(String[] args) {
        
        UserIO io = new UserIOConsoleImpl();
        
//        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//        LocalDate ld = io.readLocalDate("Enter a date: ", format);
//        System.out.println(ld.toString());

          BigDecimal num = io.readBigDecimal("Insert a number: ");
    }
}
