/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.movies.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

/**
 *
 * @author pspethmann
 */
public class Movie {
    
    public static final DateTimeFormatter DATE_FORMAT = 
            DateTimeFormatter.ofPattern("MM/dd/yyyy");
    
    private int idNumber;
    private String title;
    private LocalDate releaseDate;
    private String ratingMPAA;
    private String directorName;
    private String studio;
    private String userNote;


    public Movie() {
        title = "N/A";
        releaseDate = LocalDate.now();
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

    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        if (!(releaseDate == null)) {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.idNumber;
        hash = 23 * hash + Objects.hashCode(this.title);
        hash = 23 * hash + Objects.hashCode(this.releaseDate);
        hash = 23 * hash + Objects.hashCode(this.ratingMPAA);
        hash = 23 * hash + Objects.hashCode(this.directorName);
        hash = 23 * hash + Objects.hashCode(this.studio);
        hash = 23 * hash + Objects.hashCode(this.userNote);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Movie other = (Movie) obj;
        if (this.idNumber != other.idNumber) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.releaseDate, other.releaseDate)) {
            return false;
        }
        if (!Objects.equals(this.ratingMPAA, other.ratingMPAA)) {
            return false;
        }
        if (!Objects.equals(this.directorName, other.directorName)) {
            return false;
        }
        if (!Objects.equals(this.studio, other.studio)) {
            return false;
        }
        if (!Objects.equals(this.userNote, other.userNote)) {
            return false;
        }
        return true;
    }
    
    

}
