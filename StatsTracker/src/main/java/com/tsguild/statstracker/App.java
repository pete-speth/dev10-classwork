package com.tsguild.statstracker;

import com.tsguild.statstracker.controller.Controller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class App {
     
    public static void main(String[] args) {
        
        ApplicationContext context = 
                new AnnotationConfigApplicationContext(
                        "com.tsguild.statstracker");
        Controller controller = context.getBean(Controller.class);
        
        controller.run();
    }
}
