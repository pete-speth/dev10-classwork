/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.assignment2.movies.dao;

import com.tsguild.assignment2.movies.dao.MoviesDaoException;
import com.tsguild.assignment2.movies.dto.Movie;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author pspethmann
 */
public class MoviesDaoFileImpl implements MoviesDao {

    private List<Movie> movies = new ArrayList<>();

    private final String FILE = "moviesWithIDs.txt";
    private final String DELIMITER = "::";
    
    public MoviesDaoFileImpl() throws MoviesDaoException{
        readFile();
    }

    @Override
    public List<Movie> list() throws MoviesDaoException {
        readFile();
        return movies;
    }

    @Override
    public Movie add(Movie m) throws MoviesDaoException {
        m.setIdNumber(movies.size() + 1);
        movies.add(m);
        writeFile();
        return m;
    }

    @Override
    public boolean remove(Movie m) throws MoviesDaoException {

        boolean removed = movies.remove(m);
        writeFile();
        return removed;
    }

    @Override
    public Movie lookup(String title) throws MoviesDaoException {
        
        readFile();
        
        for (Movie m : movies) {
            if (m.getTitle().equalsIgnoreCase(title)) {
                return m;
            }
        }

        return null;
    }
    
    @Override
    public Movie lookup(int id) throws MoviesDaoException {
        
        readFile();
        
        for (Movie m : movies) {
            if (m.getIdNumber() == id) {
                return m;
            }
        }

        return null;
    }
    
    @Override
    public void edit() throws MoviesDaoException {
        writeFile();
    }

    @Override
    public List<Movie> search(String str) throws MoviesDaoException {
        
        readFile();
        
        List<Movie> filteredMovies = new ArrayList<>();

        for (Movie m : movies) {
            if (m.getTitle().startsWith(str)) {
                filteredMovies.add(m);
            }
        }

        return filteredMovies;
    }

    private void writeFile()
            throws MoviesDaoException {

        try (PrintWriter fileOut = new PrintWriter(FILE)) {
            for (Movie m : movies) {
                fileOut.println(m.getIdNumber() + DELIMITER
                        + m.getTitle() + DELIMITER
                        + m.getReleaseDate() + DELIMITER
                        + m.getRatingMPAA() + DELIMITER
                        + m.getDirectorName() + DELIMITER
                        + m.getStudio() + DELIMITER
                        + m.getUserNote());
            }
        } catch (FileNotFoundException ex) {
            throw new MoviesDaoException(ex.getMessage());
        }

    }

    private void readFile()
            throws MoviesDaoException {

        try (Scanner sc = new Scanner(
                new BufferedReader(new FileReader(FILE)))) {
            movies = new ArrayList<>();

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] props = line.split(DELIMITER);

                Movie m = new Movie();
                m.setIdNumber(Integer.parseInt(props[0]));
                m.setTitle(props[1]);
                m.setReleaseDate(LocalDate.parse(props[2],Movie.DATE_FORMAT));
                m.setRatingMPAA(props[3]);
                m.setDirectorName(props[4]);
                m.setStudio(props[5]);
                m.setUserNote(props[6]);

                movies.add(m);
            }

        } catch (FileNotFoundException ex){
            throw new MoviesDaoException(ex.getMessage());
        }

    }
}
