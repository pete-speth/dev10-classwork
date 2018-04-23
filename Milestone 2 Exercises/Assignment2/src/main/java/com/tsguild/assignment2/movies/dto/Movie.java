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
public class Movie {

    private int idNumber;
    private String title;
    private String releaseDate;
    private String ratingMPAA;
    private String directorName;
    private String studio;
    private String userNote;


    public Movie() {
        title = "N/A";
        releaseDate = "N/A";
        ratingMPAA = "N/A";
        directorName = "N/A";
        studio = "N/A";
        userNote = "N/A";
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (!title.isEmpty()) {
            this.title = title;
        }
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        if (!releaseDate.isEmpty()) {
            this.releaseDate = releaseDate;
        }
    }

    public String getRatingMPAA() {
        return ratingMPAA;
    }

    public void setRatingMPAA(String ratingMPAA) {
        if (!ratingMPAA.isEmpty()) {
            this.ratingMPAA = ratingMPAA;
        }
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        if (!directorName.isEmpty()) {
            this.directorName = directorName;
        }
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        if (!studio.isEmpty()) {
            this.studio = studio;
        }
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        if (!userNote.isEmpty()) {
            this.userNote = userNote;
        }
    }

}
