/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.arrays;

/**
 *
 * @author pspethmann
 */
public class FruitBasket {
    
    public static void main(String[] args) {
        String[] fruit = {"Orange", "Apple", "Orange", "Apple", "Orange", 
            "Apple", "Orange", "Apple", "Orange", "Orange", "Orange", "Apple", 
            "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Apple", 
            "Orange", "Apple", "Apple", "Orange", "Orange", "Apple", "Apple", 
            "Apple", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", 
            "Orange", "Orange", "Orange", "Apple", "Apple", "Apple", "Apple", 
            "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Orange", 
            "Orange", "Apple", "Apple", "Orange", "Orange", "Apple", "Orange", 
            "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Orange"};

        // Fruit sorting code goes here!
        int appleCount = 0;
        int orangeCount = 0;
        
        for(String item : fruit){
            if(item.equals("Apple")){
                appleCount++;
            } else{
                orangeCount++;
            }
        }
        
        System.out.println("Total apples: "+appleCount);
        System.out.println("Total oranges: "+orangeCount);
    }
}
