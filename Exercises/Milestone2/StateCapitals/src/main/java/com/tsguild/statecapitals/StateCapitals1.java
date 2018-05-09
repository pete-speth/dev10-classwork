/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.statecapitals;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author pspethmann
 */
public class StateCapitals1 {

    public static void main(String[] args) {

        Map<String, String> stateCapitals = new HashMap<>();

        stateCapitals.put("Alabama", "Montgomery");
        stateCapitals.put("Alaska", "Juneau");
        stateCapitals.put("Arizona", "Phoenix");
        stateCapitals.put("Arkansas", "Little Rock");
        stateCapitals.put("California", "Sacramento");
        stateCapitals.put("Colorado", "Denver");

        Set<String> keySet = stateCapitals.keySet();

        System.out.println("States:");
        System.out.println("==============");
        keySet.forEach((key) -> {
            System.out.println(key);
        });
        System.out.println("");

        Collection<String> valueSet = stateCapitals.values();

        System.out.println("Capitals:");
        System.out.println("==============");
        valueSet.forEach((value) -> {
            System.out.println(value);
        });
        System.out.println("");
        
        System.out.println("State/Capital Pairs:");
        System.out.println("============================");
        keySet.forEach((key) -> {
            System.out.println(key+" - "+stateCapitals.get(key));
        });
        
    }

}
