/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.scanner;

/**
 *
 * @author pspethmann
 */

import java.util.Scanner;

public class MiniMadLibs {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        
        System.out.print("Noun: ");
        String w1 = input.next();
        System.out.print("Adjective: ");
        String w2 = input.next();
        System.out.print("Another Noun: ");
        String w3 = input.next();
        System.out.print("Number: ");
        int w4 = input.nextInt();
        System.out.print("Another Adjective: ");
        String w5 = input.next();
        System.out.print("Plural Noun: ");
        String w6 = input.next();
        System.out.print("Another one: ");
        String w7 = input.next();
        System.out.print("One more: ");
        String w8 = input.next();
        System.out.print("Verb (present-tense): ");
        String w9 = input.next();
        System.out.print("Same verb (past-tense): ");
        String w10 = input.next();
        
        System.out.println(w1+": the "+w2+" frontier. These are the voyages of the starship "+w3+". Its "+w4+"-year mission: to explore strange "+w5+" "+w6+", to seek out "+w5+" "+w7+" and "+w5+" "+w8+", to boldly "+w9+" where no one has "+w10+" before.");
    }
}
