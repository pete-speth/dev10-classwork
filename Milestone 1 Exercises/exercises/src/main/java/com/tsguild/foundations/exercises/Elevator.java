/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.exercises;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author pspethmann
 */
public class Elevator {
    
    public static final int BUILDING_FLOORS = 20;
    
    public static void main(String[] args) {
        ArrayList requestQueue = new ArrayList();
        requestQueue.add(1);
        requestQueue.add(5);
        requestQueue.add(2);
        requestQueue.add(4);
        
        Collections.sort(requestQueue);
        
        System.out.println(requestQueue);
        System.out.println(requestQueue.get(1));
    }
    
    public static int operateElevator(int currentFloor){
        
        // Check for requests
        // If request is recieved
            // Get request floor
            // If requestFloor >= currentFloor and less than max floor
                // Add request to requestQueue
                // Sort requestQueue array
                 
            // Else
                // Deny request
            
        //If requestQueue has values or destinationQueue has values

            // If currentFloor is in requestQueue
                // Open doors
                // Get destination floor
                // If destination < currentFloor
                    //do not allow
                // Else 
                    // add destination to destinationQueue array
                    // Sort destinationQueue array
                // Close doors

            // If currentFloor is in destinationQueue
                // Open doors
                // Say goodbye to passenger
                // Close doors

            // Go up one floor
            // Update elevator state
        //Else
            // Stay Still
            // Update elevator state
       
        // Print elevator state
        // Return current floor
        
        Scanner input = new Scanner(System.in);
        String requestReply = "";
        ArrayList requestQueue = new ArrayList();
        ArrayList destinationQueue = new ArrayList();
        
        // Check for requests
        System.out.print("Are there any new elevator requests? (y/n): ");
        requestReply = input.next();
        
        if (requestReply.equalsIgnoreCase("y")){
            // Get request floor
            System.out.print("Which floor did the request come from? ");
            int requestFloor = input.nextInt();
            
            if (requestFloor >= currentFloor && requestFloor <= BUILDING_FLOORS){
                requestQueue.add(requestFloor);
                Collections.sort(requestQueue);
                
            } else{
                System.out.println("Invalid request.");
            }
        }
        
        if (!requestQueue.isEmpty() || !destinationQueue.isEmpty()){
            if (requestQueue.contains(currentFloor)){
                openDoors();
                
                // Get destination floor
                System.out.println("What is your desired destination?");
                int destinationFloor = input.nextInt();
            }
        }
        
        
        return 0;
    }
    
    public static void elevatorUp(){
        
    }
    
    public static void elevatorDown(){
        
    }
    
    public static void elevatorStop(){
        
    }
    
    public static void openDoors(){
        
    }
    
    public static void closeDoors(){
        
    }
}
