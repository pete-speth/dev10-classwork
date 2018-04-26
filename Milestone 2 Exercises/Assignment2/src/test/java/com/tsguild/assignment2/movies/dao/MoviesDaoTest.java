/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.assignment2.movies.dao;

import com.tsguild.assignment2.movies.dto.Movie;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pspethmann
 */
public class MoviesDaoTest {

    MoviesDao dao = new MoviesDaoFileImpl();

    public MoviesDaoTest() throws Exception {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {

        List<Movie> movieList = dao.list();
        movieList.clear();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class MoviesDao.
     */
    @Test
    public void testAddAndLookup() throws Exception {

        Movie movie = new Movie();
        movie.setTitle("Good Will Hunting");
        movie.setReleaseDate(LocalDate.parse("01/01/1997", Movie.DATE_FORMAT));
        movie.setRatingMPAA("R");
        movie.setDirectorName("Gus Van Sant");
        movie.setStudio("Miramax");
        movie.setUserNote("Great movie!");
        
        // Test add and lookup_string
        dao.add(movie);
        Movie fromDao = dao.lookup("Good Will Hunting");
        assertEquals(movie, fromDao);
        
        // Test lookup_int
        fromDao = dao.lookup(fromDao.getIdNumber());
        assertEquals(movie,fromDao);
    }

    /**
     * Test of list method, of class MoviesDao.
     */
    @Test
    public void testList() throws Exception {
        
        Movie movie1 = new Movie();
        movie1.setTitle("Good Will Hunting");
        movie1.setReleaseDate(LocalDate.parse("01/01/1997", Movie.DATE_FORMAT));
        movie1.setRatingMPAA("R");
        movie1.setDirectorName("Gus Van Sant");
        movie1.setStudio("Miramax");
        movie1.setUserNote("Great movie!");
        dao.add(movie1);
        
        Movie movie2 = new Movie();
        movie2.setTitle("UP");
        movie2.setReleaseDate(LocalDate.parse("01/01/2009", Movie.DATE_FORMAT));
        movie2.setRatingMPAA("R");
        movie2.setDirectorName("Pete Docter, Bob Peterson");
        movie2.setStudio("Disney Pixar");
        movie2.setUserNote("Good movie for kids.");
        dao.add(movie2);
        
        assertEquals(2,dao.list().size());    
    }

    /**
     * Test of edit method, of class MoviesDao.
     */
    @Test
    public void testEdit() throws Exception {
        
        Movie movie1 = new Movie();
        movie1.setTitle("Good Will Hunting");
        movie1.setReleaseDate(LocalDate.parse("01/01/1997", Movie.DATE_FORMAT));
        movie1.setRatingMPAA("R");
        movie1.setDirectorName("Gus Van Sant");
        movie1.setStudio("Miramax");
        movie1.setUserNote("Great movie!");
        dao.add(movie1);
        
        movie1.setRatingMPAA("PG");
        dao.edit();
        
        movie1 = dao.lookup("Good Will Hunting");
        assertEquals(movie1.getRatingMPAA(),"PG");
    }

    /**
     * Test of remove method, of class MoviesDao.
     */
    @Test
    public void testRemove() throws Exception {
        
        Movie movie1 = new Movie();
        movie1.setTitle("Good Will Hunting");
        movie1.setReleaseDate(LocalDate.parse("01/01/1997", Movie.DATE_FORMAT));
        movie1.setRatingMPAA("R");
        movie1.setDirectorName("Gus Van Sant");
        movie1.setStudio("Miramax");
        movie1.setUserNote("Great movie!");
        dao.add(movie1);
        
        Movie movie2 = new Movie();
        movie2.setTitle("UP");
        movie2.setReleaseDate(LocalDate.parse("01/01/2009", Movie.DATE_FORMAT));
        movie2.setRatingMPAA("R");
        movie2.setDirectorName("Pete Docter, Bob Peterson");
        movie2.setStudio("Disney Pixar");
        movie2.setUserNote("Good movie for kids.");
        dao.add(movie2);
        
        boolean removed = dao.remove(movie1);
        System.out.println(removed);
        assertEquals(1,dao.list().size());
        assertTrue(removed);
        
        removed = dao.remove(movie2);
        System.out.println(removed);
        assertEquals(0,dao.list().size());
        assertTrue(removed);
    }

    /**
     * Test of search method, of class MoviesDao.
     */
    @Test
    public void testSearch() throws Exception {
        
        Movie movie1 = new Movie();
        movie1.setTitle("Good Will Hunting");
        movie1.setReleaseDate(LocalDate.parse("01/01/1997", Movie.DATE_FORMAT));
        movie1.setRatingMPAA("R");
        movie1.setDirectorName("Gus Van Sant");
        movie1.setStudio("Miramax");
        movie1.setUserNote("Great movie!");
        dao.add(movie1);
        
        Movie movie2 = new Movie();
        movie2.setTitle("UP");
        movie2.setReleaseDate(LocalDate.parse("01/01/2009", Movie.DATE_FORMAT));
        movie2.setRatingMPAA("R");
        movie2.setDirectorName("Pete Docter, Bob Peterson");
        movie2.setStudio("Disney Pixar");
        movie2.setUserNote("Good movie for kids.");
        dao.add(movie2);
        
        Movie movie3 = new Movie();
        movie3.setTitle("Ghostbusters");
        movie3.setReleaseDate(LocalDate.parse("01/01/1984", Movie.DATE_FORMAT));
        movie3.setRatingMPAA("PG");
        movie3.setDirectorName("Ivan Reitman");
        movie3.setStudio("Columbia Pictures");
        movie3.setUserNote("Classic");
        dao.add(movie3);
        
        List<Movie> filteredMovies = dao.search("G");
        assertEquals(2, filteredMovies.size());
        
        filteredMovies = dao.search("Gh");
        assertEquals(1, filteredMovies.size());
        assertEquals(movie3, filteredMovies.get(0));
    }

}
