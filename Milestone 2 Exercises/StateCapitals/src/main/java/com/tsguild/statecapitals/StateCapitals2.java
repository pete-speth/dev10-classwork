/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.statecapitals;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Consumer;

/**
 *
 * @author pspethmann
 */
public class StateCapitals2 {
    
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        Map<String, Capital> stateCapitals = new HashMap<>();

        stateCapitals.put("Alabama", new Capital("Montgomery",205000,156));
        stateCapitals.put("Alaska", new Capital("Juneau",31000,3255));
        stateCapitals.put("Arizona", new Capital("Phoenix",1445000,517));
        stateCapitals.put("Arkansas", new Capital("Little Rock",193000,116));
        
        Set<String> keySet = stateCapitals.keySet();
        
        System.out.println("State/Capital Pairs:");
        System.out.println("============================");
        keySet.forEach((key) -> {
            Capital cap = stateCapitals.get(key);
            System.out.print(key+" - ");
            cap.printInfo();
        });
        
        Map<String,Capital> filteredCapitals = new HashMap<>();
        filteredCapitals.putAll(stateCapitals);
        
        System.out.print("\nPlease enter the lower limit for population size: ");
        int limit = Integer.parseInt(input.nextLine());
        
        keySet.forEach((key) -> {
            Capital cap = stateCapitals.get(key);
            if (cap.getPopulation()<limit) {
                filteredCapitals.remove(key);
            }
        });
        
        Set<String> filteredKeys = filteredCapitals.keySet();
        
        System.out.println("Listing capitals with populations greater than "
                +limit+":\n");
        filteredKeys.forEach((key)->{
            Capital cap = stateCapitals.get(key);
            System.out.print(key+" - ");
            cap.printInfo();
        });
        
        
        
    }
}
