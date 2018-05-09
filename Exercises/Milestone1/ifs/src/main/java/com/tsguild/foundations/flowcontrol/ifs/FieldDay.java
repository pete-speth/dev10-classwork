/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.flowcontrol.ifs;

import java.util.Scanner;
/**
 *
 * @author pspethmann
 */
public class FieldDay {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        String name;
        
        System.out.print("What is your last name? ");
        name = input.next();
        name = name.toLowerCase();
        
        if(name.compareTo("baggins")<0){
            System.out.println("You're on the Red Dragons!");
        } else if(name.compareTo("baggins")<0){
            System.out.println("You're on the Dark Wizards!");
        }
    }
    
    
}
