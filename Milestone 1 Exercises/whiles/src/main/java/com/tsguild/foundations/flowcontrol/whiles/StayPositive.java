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

import java.util.Scanner;

public class StayPositive {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        int counter = 1;
        
        System.out.print("What number should I count back from? ");
        int num = input.nextInt();
        System.out.println();
        
        while(num>=0){
            
            if(counter%10 == 0){
                System.out.print(num+"\n");
            } else{
                System.out.print(num+" ");
            }
            counter++;
            num--;
        }
        
    }
}
