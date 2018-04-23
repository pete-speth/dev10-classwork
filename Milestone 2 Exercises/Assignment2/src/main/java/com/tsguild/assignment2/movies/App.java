/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.assignment2.movies;

import com.tsguild.assignment2.movies.controller.MoviesController;
import com.tsguild.assignment2.movies.dao.MoviesDao;
import com.tsguild.assignment2.movies.dao.MoviesDaoException;
import com.tsguild.assignment2.movies.dao.MoviesDaoFileImpl;
import com.tsguild.assignment2.movies.ui.MoviesView;
import com.tsguild.assignment2.movies.ui.UserIO;
import com.tsguild.assignment2.movies.ui.UserIOConsoleImpl;

/**
 *
 * @author pspethmann
 */
public class App {

    public static void main(String[] args) {

        UserIO io = new UserIOConsoleImpl();
        MoviesView view = new MoviesView(io);
        try {
            MoviesDao dao = new MoviesDaoFileImpl();
            MoviesController controller = new MoviesController(view, dao);

            controller.run();
        } catch (MoviesDaoException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        
    }
}
