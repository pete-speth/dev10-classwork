/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.consoleutils;

/**
 *
 * @author pspethmann
 */
public class App {

    public static void main(String[] args) {

        ConsoleIO cio = new ConsoleIO();

//        String firstName = cio.readString("Please enter your first name: ");
//        System.out.println(firstName);
//        int result = cio.readInt("Input an integer: ");
//        System.out.println(result);
//        int result = cio.readInt("Input an integer between 1 and 125: ",1,125);
//        System.out.println(result);
        double result = cio.readDouble("Input a double: ");
        System.out.println(result);
    }

}
