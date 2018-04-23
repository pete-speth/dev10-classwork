/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.refactorintoobjects;

import java.util.Scanner;

/**
 *
 * @author pspethmann
 */
public class Factorizer {
    
    int numberToFactor;
    int[] factors;
    boolean isPerfect, isPrime;
    
    public void factorize(){
        getNumberToFactor();
        findFactors();
        findIsPerfect();
        findIsPrime();
        printResults();
    }
    
    private void getNumberToFactor(){
        
        Scanner input = new Scanner(System.in);
        String userInput;
        
        System.out.print("What number would you like to factor? ");
        userInput = input.nextLine();
        
        numberToFactor = Integer.parseInt(userInput);
    }
    
    private void findFactors(){
        
        int numFactors = 0;
        
        for (int i=1; i<=(numberToFactor/2); i++){
            if (numberToFactor%i ==0){
                numFactors++;
            }
        }
        
        factors = new int[numFactors];
        numFactors = 0;
        
        for (int i=1; i<=(numberToFactor/2); i++){
            if (numberToFactor%i ==0){
                factors[numFactors] = i;
                numFactors++;
            }
        }
    }
    
    private void findIsPerfect(){
        
        int sum = 0;
        
        for (int num : factors){
            sum += num;
        }
        
        isPerfect = (sum == numberToFactor);
    }
    
    private void findIsPrime(){
        
        isPrime =  factors.length == 1;
    }
    
    private void printResults(){
        
        System.out.println("The factors of "+numberToFactor+" are:");
        
        for (int num: factors){
            System.out.println(num);
        }
        
        if (isPerfect){
            System.out.println(numberToFactor+" is a perfect number.");
        } else{
            System.out.println(numberToFactor+" is not a perfect number.");
        }
        
        if (isPrime){
            System.out.println(numberToFactor+" is a prime number.");
        } else{
            System.out.println(numberToFactor+" is not a prime number.");
        }
        
    }
}
