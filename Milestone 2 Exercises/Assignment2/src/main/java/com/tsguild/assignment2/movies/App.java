/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.assignment2.movies;

import com.tsguild.assignment2.movies.controller.MoviesController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author pspethmann
 */
public class App {

    public static void main(String[] args) {
        
//        // Constructor DI
//        UserIO io = new UserIOConsoleImpl();
//        MoviesView view = new MoviesView(io);
//        try {
//            MoviesDao dao = new MoviesDaoFileImpl();
//            MoviesController controller = new MoviesController(view, dao);
//
//            controller.run();
//        } catch (MoviesDaoException ex) {
//            System.out.println("ERROR: " + ex.getMessage());
//        }
        

//        // Spring DI with xml
//        ApplicationContext ctx = 
//                new ClassPathXmlApplicationContext("applicationContext.xml");
//        MoviesController controller = 
//                ctx.getBean("controller", MoviesController.class);
//        controller.run();
        

        // Spring DI with Annotations
        ApplicationContext ctx = 
                new AnnotationConfigApplicationContext(
                        "com.tsguild.assignment2.movies");
        MoviesController controller
                = ctx.getBean(MoviesController.class);
        controller.run();

        
    }
}
