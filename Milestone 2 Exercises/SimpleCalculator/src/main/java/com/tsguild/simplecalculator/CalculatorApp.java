/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.simplecalculator;

import java.util.Scanner;

/**
 *
 * @author pspethmann
 */
public class CalculatorApp {

    Scanner input = new Scanner(System.in);
    SimpleCalculator calc = new SimpleCalculator();

    public static void main(String[] args) {
        
        CalculatorApp app = new CalculatorApp();
        app.runCalculator();
    }

    public void runCalculator() {
           
        boolean running = true;
        
        do{
            
            String userInput;
            int userChoice;
            
            // Print calculator menu and get decision
            System.out.println("\nMain menu:");
            System.out.println("=========================");
            System.out.println("1. Add two numbers");
            System.out.println("2. Subtract two numbers");
            System.out.println("3. Multiply two numbers");
            System.out.println("4. Divide two numbers");
            System.out.println("0. Quit program\n");
            
            System.out.print("What would you like to do?: ");
            userInput = input.nextLine();
            
            try{
                userChoice = Integer.parseInt(userInput);
            } catch (NumberFormatException ex){
                System.out.println("Invalid input.");
                break;
            }
            
            switch (userChoice){
                case 0:
                    running = false;
                    break;
                case 1:
                    consoleAdd();
                    break;
                case 2:
                    consoleSubtract();
                    break;
                case 3:
                    consoleMultiply();
                    break;
                case 4:
                    consoleDivide();
                    break;
                    
            }
            
        } while(running);
    }
    
    public void consoleAdd(){
        
        String str1, str2;
        double num1, num2;
        double result;
        
        System.out.print("Input the first number: ");
        str1 = input.nextLine();
        System.out.print("Input the second number: ");
        str2 = input.nextLine();
        
        try{
            num1 = Double.parseDouble(str1);
            num2 = Double.parseDouble(str2);
        } catch (NumberFormatException ex){
            System.out.println("Invalid input.");
            return;
        }
        
        result = calc.add(num1,num2);
        
        System.out.println(num1+" + "+num2+" = "+result);
        
    }
    
    public void consoleSubtract(){
        String str1, str2;
        double num1, num2;
        double result;
        
        System.out.print("Input the first number: ");
        str1 = input.nextLine();
        System.out.print("Input the second number: ");
        str2 = input.nextLine();
        
        try{
            num1 = Double.parseDouble(str1);
            num2 = Double.parseDouble(str2);
        } catch (NumberFormatException ex){
            System.out.println("Invalid input.");
            return;
        }
        
        result = calc.subtract(num1,num2);
        
        System.out.println(num1+" - "+num2+" = "+result);
    }
    
    public void consoleMultiply(){
        String str1, str2;
        double num1, num2;
        double result;
        
        System.out.print("Input the first number: ");
        str1 = input.nextLine();
        System.out.print("Input the second number: ");
        str2 = input.nextLine();
        
        try{
            num1 = Double.parseDouble(str1);
            num2 = Double.parseDouble(str2);
        } catch (NumberFormatException ex){
            System.out.println("Invalid input.");
            return;
        }
        
        result = calc.multiply(num1,num2);
        
        System.out.println(num1+" * "+num2+" = "+result);
    }
    
    public void consoleDivide(){
        String str1, str2;
        double num1, num2;
        double result;
        
        System.out.print("Input the first number: ");
        str1 = input.nextLine();
        System.out.print("Input the second number: ");
        str2 = input.nextLine();
        
        try{
            num1 = Double.parseDouble(str1);
            num2 = Double.parseDouble(str2);
        } catch (NumberFormatException ex){
            System.out.println("Invalid input.");
            return;
        }
        
        result = calc.divide(num1,num2);
        
        System.out.println(num1+" / "+num2+" = "+result);
    }
    
}
