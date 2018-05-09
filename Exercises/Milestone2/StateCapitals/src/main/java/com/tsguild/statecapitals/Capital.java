/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.statecapitals;

/**
 *
 * @author pspethmann
 */
public class Capital {
    
    private String name;
    private int population;
    private int sqMileage;
    
    public Capital(String name, int population, int sqMileage){
        this.name = name;
        this.population = population;
        this.sqMileage = sqMileage;
    }
    
    public int getPopulation(){
        return this.population;
    }
    
    public void printInfo(){
        System.out.println(name+" | Pop: "+population
                +" | Area: "+sqMileage+" sq mi");
    }
}
