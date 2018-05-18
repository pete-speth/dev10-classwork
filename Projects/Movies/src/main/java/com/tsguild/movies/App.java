/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.movies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author pspethmann
 */

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        
        SpringApplication.run(App.class, args);
        
        
        
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
        

//        // Spring DI with Annotations
//        ApplicationContext ctx = 
//                new AnnotationConfigApplicationContext(
//                        "com.tsguild.movies");
//        MoviesController controller
//                = ctx.getBean(MoviesController.class);
//        controller.run();

        
    }
}
