/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.assignment2.movies.controller;

import com.tsguild.assignment2.movies.dao.MoviesDao;
import com.tsguild.assignment2.movies.dao.MoviesDaoException;
import com.tsguild.assignment2.movies.dto.Movie;
import com.tsguild.assignment2.movies.ui.MoviesView;
import java.util.List;

/**
 *
 * @author pspethmann
 */
public class MoviesController {

    private MoviesView view;
    private MoviesDao dao;
    
    public MoviesController(MoviesView view, MoviesDao dao){
        this.view = view;
        this.dao = dao;
    }

    public void run() {

        view.printWelcomeMessage();

        boolean running = true;

        try {
            while (running) {

                int selection = view.printMenuAndGetSelection();

                switch (selection) {
                    case 0:
                        running = false;
                        break;
                    case 1:
                        listMovies();
                        break;
                    case 2:
                        addMovie();
                        break;
                    case 3:
                        editMovie();
                        break;
                    case 4:
                        removeMovie();
                        break;
                    case 5:
                        lookupMovie();
                        break;
                    case 6:
                        searchMovies();
                        break;

                } 
            }
            
            view.printGoodbye();
            
        } catch (MoviesDaoException ex) {
            view.printErrorMessage(ex);
        }
    }

    private void listMovies() throws MoviesDaoException {

        view.printListMessage();
        List<Movie> movies = dao.list();
        for (Movie currentMovie : movies) {
            view.printMovie(currentMovie);
        }
        view.pauseUntilEnter();
    }

    private void addMovie() throws MoviesDaoException {

        view.printAddMessage();
        Movie m = view.getMovieToAdd();
        dao.add(m);
        view.pauseUntilEnter();
    }

    private void editMovie() throws MoviesDaoException {
        
        Movie m;
        view.printEditMessage();
        m = userChoiceLookup();
        
        if (m != null){
            view.getEditInfo(m);
            dao.edit();
        }else {
            view.printNotFound();
        }
        
        view.pauseUntilEnter();

    }
    
    private void removeMovie() throws MoviesDaoException {
        
        view.printRemoveHeader();
        Movie m = userChoiceLookup();
        boolean removed = dao.remove(m);
        view.printRemovalStatus(removed);
        view.pauseUntilEnter();
    }


    private void lookupMovie() throws MoviesDaoException {
        
        Movie found = userChoiceLookup();
        if (found != null) {
            view.printInfoFromLookup(found);
        } else{
            view.printNotFound();
        }
        view.pauseUntilEnter();
    }

    private void searchMovies() throws MoviesDaoException {
        
        String searchStr = view.getSearchString();
        List<Movie> matches = dao.search(searchStr);
        view.printSearchResultHeader(matches.size(),searchStr);
        for (Movie currentMatch : matches) {
            view.printMovie(currentMatch);
        }
        view.pauseUntilEnter();

    }
    
    private Movie userChoiceLookup() throws MoviesDaoException{
        
        Movie m;
        
        String option = view.getSelectionMethod();
        if (option.equalsIgnoreCase("i")){
            int id = view.getID();
            m = dao.lookup(id);
        } else{
            String title = view.getTitle();
            m = dao.lookup(title);
        }
        
        return m;
    }
}
