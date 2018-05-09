/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.shapesandperimeters;

/**
 *
 * @author pspethmann
 */
public class Square extends Rectangle {
    
    private double sideLength;
    
    public Square(String color, double sideLength) {
        super(color, sideLength, sideLength);
        this.sideLength = sideLength;
    }
    
    
    
    
    
    
}
