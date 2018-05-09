/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.random;

/**
 *
 * @author pspethmann
 */

import java.util.Random;

public class CoinFlipper {
    public static void main(String[] args) {
        Random rand = new Random();
        
        System.out.println("Ready, set, flip...!!");
        boolean side = rand.nextBoolean();
        if(side){
            System.out.println("You got HEADS!");
        } else{
            System.out.println("You got TAILS!");
        }
    }
}
