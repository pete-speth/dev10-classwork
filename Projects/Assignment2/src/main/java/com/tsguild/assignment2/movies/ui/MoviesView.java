/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.assignment2.movies.ui;

import com.tsguild.assignment2.movies.dao.MoviesDaoException;
import com.tsguild.assignment2.movies.dto.Movie;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 *
 * @author pspethmann
 */

@Component
@Configuration
public class MoviesView {

    UserIO io;
    
    @Autowired
    public MoviesView(UserIO io) {
        this.io = io;
    }

    public void printWelcomeMessage() {
        io.print("Welcome to your movie database! Let's get started: ");
    }

    public int printMenuAndGetSelection() {

        io.print("\nMOVIE DATABASE MENU:");
        io.print("====================");
        io.print("1. List all movies");
        io.print("2. Add a movie");
        io.print("3. Edit info for an existing movie");
        io.print("4. Remove a movie");
        io.print("5. Look up movie info");
        io.print("6. Search for a movie title");
        io.print("0. Exit program");

        int selection = io.readInt("\nWhat would you like to do?: ", 0, 6);

        return selection;
    }

    public void printListMessage() {
        io.print("========== MOVIE LIST ==========");
    }

    public void printMovie(Movie movie) {
        io.print(movie.getIdNumber() + ". "
                + movie.getTitle() + " ("
                + movie.getReleaseDate() + ") - "
                + movie.getRatingMPAA());
    }

    public void printAddMessage() {
        io.print("========== ADD A MOVIE ==========: ");
    }

    public Movie getMovieToAdd() {
        Movie m = new Movie();

        m.setTitle(io.readString("Title: "));

        try {
            m.setReleaseDate(LocalDate.parse(
                    io.readString("Release Date (MM/dd/yyyy): "),
                    Movie.DATE_FORMAT));
        } catch (DateTimeParseException ex) {
            m.setReleaseDate(null);
        }

        m.setRatingMPAA(io.readString("MPAA Rating: "));
        m.setDirectorName(io.readString("Director: "));
        m.setStudio(io.readString("Studio: "));
        m.setUserNote(io.readString("Notes: "));

        io.print("Movie added!");

        return m;
    }

    public void printEditMessage() {
        io.print("========== EDIT MOVIE ==========");

    }

    // Add fail message for edit
    public Movie getEditInfo(Movie m) {

        io.print("Enter in new values for the fields you would like to edit.");

        String reply;

        reply = io.readString("Title (" + m.getTitle() + "): ");
        if (!reply.isEmpty()) {
            m.setTitle(reply);
        }

        reply = io.readString("Release Date (" + m.getReleaseDate() + "): ");
        if (!reply.isEmpty()) {

            try {
                m.setReleaseDate(LocalDate.parse(reply, Movie.DATE_FORMAT));
            } catch (DateTimeParseException ex) {
                m.setReleaseDate(null);
            }
        }

        reply = io.readString("MPAA Rating (" + m.getRatingMPAA() + "): ");
        if (!reply.isEmpty()) {
            m.setRatingMPAA(reply);
        }

        reply = io.readString("Director (" + m.getDirectorName() + "): ");
        if (!reply.isEmpty()) {
            m.setDirectorName(reply);
        }

        reply = io.readString("Studio (" + m.getStudio() + "): ");
        if (!reply.isEmpty()) {
            m.setStudio(reply);
        }

        reply = io.readString("Notes (" + m.getUserNote() + "): ");
        if (!reply.isEmpty()) {
            m.setUserNote(reply);
        }

        io.print("Editing complete.");

        return m;
    }

    public void printRemoveHeader() {
        io.print("========== REMOVE MOVIE ==========");
    }

    public void printRemovalStatus(boolean wasRemoved) {
        if (wasRemoved) {
            io.print("Movie successfully removed.");
        } else {
            io.print("Movie not found.");
        }
    }

    public String getTitleToLookup() {
        io.print("========== LOOK UP MOVIE ==========");
        return io.readString(
                "What is the title or ID # of the movie you want to look up?: ");
    }

    public void printInfoFromLookup(Movie m) {
        io.print("Fetching data...\n");
        io.print("       Title: " + m.getTitle());
        io.print("Release Date: " + m.getReleaseDate());
        io.print(" MPAA Rating: " + m.getRatingMPAA());
        io.print("    Director: " + m.getDirectorName());
        io.print("      Studio: " + m.getStudio());
        io.print("       Notes: " + m.getUserNote());
    }

    public void printNotFound() {
        io.print("Movie not found.");
    }

    public String getSearchString() {
        io.print("========== SEARCH MOVIES ==========");
        return io.readString(
                "Input all or part of the title you want to search: ");
    }

    public void printSearchResultHeader(int matches, String search) {
        io.print("Found " + matches
                + " movie title(s) starting with '" + search + "':");
    }

    public void printErrorMessage(MoviesDaoException ex) {
        io.print("ERROR: " + ex.getMessage());
    }

    public void printGoodbye() {
        io.print("Goodbye!");
    }

    public void pauseUntilEnter() {
        io.readString("Press ENTER to continue. ");
    }

    public int getID() {
        return io.readInt("Input the ID#: ");
    }

    public String getTitle() {
        return io.readString("Input the title: ");
    }

    public String getSelectionMethod() {
        return io.readString(
                "Would you like to select movie by ID# or title? "
                + "(Enter 'i' to search by ID#): ");
    }
}
