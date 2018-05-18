/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.vendingmachine;

import com.tsguild.vendingmachine.controller.Controller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author pspethmann
 */
@Configuration
public class App {

    public static void main(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(
                "com.tsguild.vendingmachine");
        Controller controller = ctx.getBean(Controller.class);
        controller.run();
    }

}
