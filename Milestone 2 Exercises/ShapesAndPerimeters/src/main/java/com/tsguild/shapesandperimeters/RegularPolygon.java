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
public class RegularPolygon extends Shape {
    
    private int numSides;
    private double sideLength;
    
    public RegularPolygon(String color, int numSides, double sideLength){
        super(color);
        this.numSides = numSides;
        this.sideLength = sideLength;
    }
    
    @Override
    public double getPerimeter(){
        double perimeter = numSides*sideLength;
        return perimeter;
    }
    
    @Override
    public double getArea(){
        double area = (1.0/4.0)*numSides*Math.pow(sideLength, 2)*(1/Math.tan(Math.PI/numSides));
        return area;
    }
}
