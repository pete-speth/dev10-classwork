/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.movies.controllers;

import com.tsguild.movies.dao.JdbcTemplateDao;
import com.tsguild.movies.dao.MoviesDaoException;
import com.tsguild.movies.dto.Movie;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pspethmann
 */
@RestController
public class MoviesRestController {

    @Autowired
    private JdbcTemplateDao dao;

    @GetMapping("api/movies")
    public List<Movie> getAllMovies() {
        try {
            return dao.list();
        } catch (MoviesDaoException ex) {
            System.out.println("yikes");
            return null;
        }
    }

    @PostMapping("api/movie")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        try {
            Movie added = dao.add(movie);
            if (added != null) {
                return ResponseEntity.ok(added);
            } else {
                return ResponseEntity.noContent().build();
            }

        } catch (MoviesDaoException ex) {
            System.out.println("yikes");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    
    @PutMapping("api/movie/{id}")
    public ResponseEntity<Void> updateMovie(
            @PathVariable int id, @RequestBody Movie movie){
        
        if (movie.getIdNumber()<=0 || id <=0 || movie.getIdNumber() != id){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        try {
          dao.edit(movie); 
          return ResponseEntity.status(HttpStatus.ACCEPTED).build();
            
        } catch (MoviesDaoException ex) {
            System.out.println("yikes");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @DeleteMapping("api/movie/{id}")
    public ResponseEntity<Void> deleteMovie(
            @PathVariable int id) {

        try {
            Movie storedMovie = dao.lookup(id);
            
            if (storedMovie != null){
                dao.remove(storedMovie);
                return ResponseEntity.status(HttpStatus.ACCEPTED).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

        } catch (MoviesDaoException ex) {
            System.out.println("yikes");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
