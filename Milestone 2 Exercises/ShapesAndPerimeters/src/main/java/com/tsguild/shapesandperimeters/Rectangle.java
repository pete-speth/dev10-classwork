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
public class Rectangle extends Shape{
    
    private double width;
    private double height;
    
    public Rectangle(String color, double width, double height){
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    public double getPerimeter() {
        double perimeter = 2*(width+height);
        return perimeter;
    }
    
    @Override
    public double getArea() {
        double area = width*height;
        return area;
    }
    
    
}

