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
public class App {
    
    public static void main(String[] args) {
        App app = new App();
        
        Shape rect = new Rectangle("Blue",13,3);
        app.printShape(rect);
        
        Shape circle = new Circle("Red",3);
        app.printShape(circle);
        
        Shape square1 = new Square("Orange", 5);
        app.printShape(square1);
        
        Shape triangle = new RegularPolygon("Green",3,7);
        app.printShape(triangle);
        
        Shape square2 = new RegularPolygon("Purple",4,5);
        app.printShape(square2);
    }
    
    public void printShape(Shape shape){
        System.out.println("Color is: "+shape.getColor());
        System.out.println("Perimeter is: "+ shape.getPerimeter());
        System.out.println("Area is: " + shape.getArea());
    }
    
}
