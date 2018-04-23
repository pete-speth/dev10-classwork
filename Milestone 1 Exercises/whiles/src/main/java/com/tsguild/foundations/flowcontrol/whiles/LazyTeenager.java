/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.flowcontrol.whiles;

/**
 *
 * @author pspethmann
 */

import java.util.Random;

public class LazyTeenager {
    public static void main(String[] args) {
        
        Random rand = new Random();
        int outcome;
        int counter = 1;
        int odds = 5;
        boolean flag;
        
        do{
            System.out.println("Clean your room!! (x"+counter+")");
            outcome = rand.nextInt(100);
            
            if(outcome<odds){
                System.out.println("FINE!");
                break;
            } else if(counter == 15){
                System.out.println("That's it, you're grounded!");   
            }
            
            counter++;
            odds += 5;
            
        } while(true);
    }
}
