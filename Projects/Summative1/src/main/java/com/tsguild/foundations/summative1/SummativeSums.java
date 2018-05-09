/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.summative1;

/**
 *
 * @author pspethmann
 */
public class SummativeSums {

    public static void main(String[] args) {
        // Define test arrays
        int[] testArray1 = {1, 90, -33, -55, 67, -16, 28, -55, 15};
        int[] testArray2 = {999, -60, -77, 14, 160, 301};
        int[] testArray3 = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120,
            130, 140, 150, 160, 170, 180, 190, 200, -99};

        // Run sumArray on test arrays
        System.out.println("#1 Array Sum: " + sumArray(testArray1));
        System.out.println("#2 Array Sum: " + sumArray(testArray2));
        System.out.println("#3 Array Sum: " + sumArray(testArray3));
    }

    /* 
    Find the sum of all numbers within an array.
    Inputs: Array of integers
    Outputs: Integer representing sum
    */
    public static int sumArray(int[] intArray) {

        int sum = 0;

        for (int num : intArray) {
            sum += num;
        }

        return sum;
    }
}
