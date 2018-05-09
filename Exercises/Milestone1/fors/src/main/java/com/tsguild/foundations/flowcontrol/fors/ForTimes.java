/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.flowcontrol.fors;

/**
 *
 * @author pspethmann
 */

import java.util.Scanner;

public class ForTimes {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        int inputNum;
        
        System.out.print("Which times table should I recite? ");
        inputNum = input.nextInt();
        
        for(int i=1;i<=15;i++){
            System.out.println(i+" * "+inputNum+" is: "+(i*inputNum));
        }
    }
}
