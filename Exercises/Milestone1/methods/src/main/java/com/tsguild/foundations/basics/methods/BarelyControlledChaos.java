/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.basics.methods;

import java.util.Random;

/**
 *
 * @author pspethmann
 */
public class BarelyControlledChaos {
    
    public static void main(String[] args) {
        String color = chooseRandomColor(); // call color method here 
        String animal = chooseRandomAnimal(); // call animal method again here 
        String colorAgain = chooseRandomColor(); // call color method again here 
        int weight = chooseNumberFromRange(5,200); // call number method, 
            // with a range between 5 - 200 
        int distance = chooseNumberFromRange(10, 20); // call number method, 
            // with a range between 10 - 20 
        int number = chooseNumberFromRange(10000, 20000); // call number method, 
            // with a range between 10000 - 20000 
        int time = chooseNumberFromRange(2, 6); // call number method, 
            // with a range between 2 - 6            
    
        System.out.println("Once, when I was very small...");

        System.out.println("I was chased by a " + color + ", "
            + weight + "lb " + " miniature " + animal 
            + " for over " + distance + " miles!!");

        System.out.println("I had to hide in a field of over " 
            + number + " " + colorAgain + " poppies for nearly " 
            + time + " hours until it left me alone!");

        System.out.println("\nIt was QUITE the experience, " 
            + "let me tell you!");
    }
    
    public static String chooseRandomColor(){
        
        Random rand = new Random();
        int num = rand.nextInt(5);
        String color = "";
        
        switch(num){
            case 0:
                color = "Red";
                break;
            case 1:
                color = "Blue";
                break;
            case 2:
                color = "Green";
                break;
            case 3:
                color = "Purple";
                break;
            case 4:
                color = "Orange";
                break;  
        }
        
        return color;
    }
    
    public static String chooseRandomAnimal(){
        Random rand = new Random();
        int num = rand.nextInt(5);
        String animal = "";
        
        switch(num){
            case 0:
                animal = "Kangaroo";
                break;
            case 1:
                animal = "Penguin";
                break;
            case 2:
                animal = "Turtle";
                break;
            case 3:
                animal = "Duck";
                break;
            case 4:
                animal = "Cow";
                break; 
        }
        
        return animal;
    }
    
    public static int chooseNumberFromRange(int min, int max){
        Random rand = new Random();
        return rand.nextInt(max-min)+min;
    }
}
