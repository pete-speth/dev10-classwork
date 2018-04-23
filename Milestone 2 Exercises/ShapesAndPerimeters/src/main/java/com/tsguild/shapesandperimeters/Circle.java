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
public class Circle extends Shape {
    
    private double radius;
    
    public Circle(String color, double radius){
        super(color);
        this.radius = radius;
    }
    
    @Override
    public double getPerimeter() {
        double perimeter = 2*Math.PI*radius;
        return perimeter;
    }
    
    @Override
    public double getArea() {
        double area = Math.PI*radius*radius;
        return area;
    }
    
    
}
