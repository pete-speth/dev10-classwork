/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.movies.dao;

/**
 *
 * @author pspethmann
 */
public class MoviesDaoException extends Exception {
    
    public MoviesDaoException(String message){
        super(message);
    }
    
    public MoviesDaoException(String message, Exception cause){
        super(message,cause);
    }
}
