/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.flowcontrol.ifs;

/**
 *
 * @author pspethmann
 */

import java.util.Scanner;

public class YourLifeInMovies {
   public static void main(String[] args){
       Scanner input = new Scanner(System.in);
       
       System.out.print("What year were you born? ");
       int year = input.nextInt();
       
       if (year<2005){
           System.out.println("Pixar's 'Up' came out half a decade ago");
       }
       if (year<1995){
           System.out.println("The first Harry Potter movie came out over 15 years ago");
       }
       if (year<1985){
           System.out.println("Space Jam came out not last decade, but the one before THAT");
       }
       if (year<1975){
           System.out.println("The original Jurassic Park release is closer to the lunar landing than today");
       }
       if (year<1965){
           System.out.println("MASH has been around for almost half a century!");
       }
   } 
}
