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

public class TriviaNight {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        
        byte answer;
        byte counter = 0;
        
        System.out.println("QUESTION 1");
        System.out.println("What is the capital of Maine?");
        System.out.println("1) St. Paul         2) Augusta");
        System.out.println("3) Richfield        4) Bismarck");
        System.out.print("YOUR ANSWER: ");
        answer = input.nextByte();
        
        
        if(answer==2){
            counter+=1;
        }
        
        System.out.println("QUESTION 2");
        System.out.println("What year did Columbus come to America?");
        System.out.println("1) 1512        2) 1243");
        System.out.println("3)1325        4) 1492");
        System.out.print("YOUR ANSWER: ");
        answer = input.nextByte();
        
        if(answer==4){
            counter+=1;
        }
        
        
        System.out.println("QUESTION 3");
        System.out.println("What is 1024^(1/10)");
        System.out.println("1) 2         2) 1096424");
        System.out.println("3) 1024       4) 0");
        System.out.print("YOUR ANSWER: ");
        answer = input.nextByte();
        
        if(answer==1){
            counter+=1;
        }
        
        System.out.println("You got "+counter+" correct!");
    }
}
