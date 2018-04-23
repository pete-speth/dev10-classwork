/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.assignment2.movies.dto;

/**
 *
 * @author pspethmann
 */
public class Movie2 {

    private int idNumber;
    private String[] title = {"N/A"};
    private String[] releaseDate = {"N/A"};
    private String[] ratingMPAA = {"N/A"};
    private String[] directorName = {"N/A"};
    private String[] studio = {"N/A"};
    private String[] userNote = {"N/A"};

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public String[] getTitle() {
        return title;
    }

    public void setTitle(String[] title) {
        this.title = title;
    }

    public String[] getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String[] releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String[] getRatingMPAA() {
        return ratingMPAA;
    }

    public void setRatingMPAA(String[] ratingMPAA) {
        this.ratingMPAA = ratingMPAA;
    }

    public String[] getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String[] directorName) {
        this.directorName = directorName;
    }

    public String[] getStudio() {
        return studio;
    }

    public void setStudio(String[] studio) {
        this.studio = studio;
    }

    public String[] getUserNote() {
        return userNote;
    }

    public void setUserNote(String[] userNote) {
        this.userNote = userNote;
    }

   

}

