package com.tsguild.statstracker.controller;

import com.tsguild.statstracker.model.Game;
import com.tsguild.statstracker.model.Player;
import com.tsguild.statstracker.model.Result;
import com.tsguild.statstracker.service.StatsService;
import com.tsguild.statstracker.ui.View;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Controller {

    View view;
    StatsService service;

    @Autowired
    public Controller(View view, StatsService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {

        boolean running = true;

        while (running) {
            int option = view.displayMenuAndGetOption();

            switch (option) {
                case 1:
                    displayRankings();
                    break;
                case 2:
                    viewRecentGames();
                    break;
                case 3:
                    viewGamesByDate();
                    break;
                case 4:
                    addGame();
                    break;
                case 5:
                    removeGame();
                    break;
                case 6:
                    editGame();
                    break;
                case 0:
                    running = false;
                    break;
            }
        }
    }

    private void displayRankings() {

        Result<List<Player>> result = service.getRankings();

        if (result.isValid()) {
            List<Player> players = result.getOutput();
            view.displayRankings(players);
        } else {
            view.printMessage(result.getMessage());
        }
        
        view.waitForPrompt();

    }

    private void viewRecentGames() {

        String playerName = view.getPlayerName();

        Result<List<Game>> result = service.getRecentGames(playerName);

        List<Game> games = result.getOutput();

        while (!result.isValid() && !result.isExitFlag()) {
            view.printMessage(result.getMessage());
            playerName = view.promptForValidPlayerName(playerName);
            result = service.getRecentGames(playerName);
            games = result.getOutput();
        }

        if (games == null || games.isEmpty() ) {
            view.printMessage(result.getMessage());
        } else {
            view.displayGames(games);
        }

        view.waitForPrompt();

    }

    private void viewGamesByDate() {

        Result<List<Game>> result = searchGamesByDate();
        List<Game> games = result.getOutput();

        if (result.isValid()) {

            if (games.isEmpty()) {
                view.printMessage(result.getMessage());
            } else {
                view.displayGames(games);
            }
        }

        view.waitForPrompt();

    }

    private void addGame() {

        Game game = new Game();
        Player away;
        Player home;

        away = getAndConfirmPlayer(false);
        game.setAwayPlayerName(away.getName());

        home = getAndConfirmPlayer(true);
        game.setHomePlayerName(home.getName());

        view.getOtherGameInfo(game);

        Result result = service.addGame(game);
        view.printMessage(result.getMessage());
        view.waitForPrompt();

    }

    private void removeGame() {

        Result<List<Game>> gamesResult = searchGamesByDate();
        List<Game> games = gamesResult.getOutput();

        if (gamesResult.isValid()) {
            int choice = view.displayListedGames(games);
            Result removeResult = service.removeGame(games.get(choice));
            view.printMessage(removeResult.getMessage());
        } 

        view.waitForPrompt();

    }

    private void editGame() {

        Result<List<Game>> result = searchGamesByDate();
        List<Game> games = result.getOutput();

        if (result.isValid()) {
            int choice = view.displayListedGames(games);

            Game original = games.get(choice);
            Game edited = original.clone();

            Player away;
            Player home;

            // Don't change player names if they are the same or input is blank
            away = getAndConfirmPlayer(edited.getAwayPlayerName(), false);
            if (away != null
                    && !edited.getAwayPlayerName().
                            equalsIgnoreCase(away.getName())) {

                edited.setAwayPlayerName(away.getName());

            }

            home = getAndConfirmPlayer(edited.getHomePlayerName(), true);
            if (home != null && 
                    !edited.getHomePlayerName().
                            equalsIgnoreCase(home.getName())) {

                edited.setHomePlayerName(home.getName());

            }

            view.getEditInfo(edited);

            Result addResult;
            Result removeResult;

            if (!edited.equals(original)) {
                addResult = service.removeGame(original);
                removeResult = service.addGame(edited);

                if (addResult.isValid() && removeResult.isValid()) {
                    view.printMessage("Game edited successfully.");
                } else {
                    if (!addResult.isValid()) {
                        view.printMessage(addResult.getMessage());
                    }
                    if (!removeResult.isValid()) {
                        view.printMessage(removeResult.getMessage());
                    }
                }

            } else {
                view.printMessage("No changes were made.");
            }
        }
        
        view.waitForPrompt();

    }

    private Result<List<Game>> searchGamesByDate() {

        LocalDate date = view.getDate();
        String playerName = view.getPlayerName();

        Result<List<Game>> result = service.getGames(playerName, date);
        List<Game> games = result.getOutput();

        while (!result.isValid() && !result.isExitFlag()) {
            view.printMessage(result.getMessage());
            playerName = view.promptForValidPlayerName(playerName);
            result = service.getGames(playerName, date);
            games = result.getOutput();
        }
        
        if (result.isExitFlag()){
            view.printMessage(result.getMessage());
        }

        return result;
    }

    /* 
        Get Players for the new game. If a player doesn't exist, check to make
        sure that the user wants to create a new player, otherwise allow them
        to try to reenter the name.
     */
    private Player getAndConfirmPlayer(boolean wasHome) {

        Player player;
        Result<Player> result;

        boolean moveOn;

        do {
            moveOn = true;
            String playerName;

            playerName = view.getPlayer(wasHome);

            result = service.getPlayer(playerName);
            player = result.getOutput();

            if (!result.isValid()) {

                view.printMessage(result.getMessage());

                if (!view.confirmPlayerCreation(playerName)) {
                    moveOn = false;
                } else {
                    player = new Player();
                    player.setName(playerName);
                }
            }
        } while (!moveOn);

        return player;
    }

    private Player getAndConfirmPlayer(String currentName, boolean wasHome) {

        Player player;
        Result<Player> result;

        boolean moveOn;

        do {
            moveOn = true;
            String playerName;

            playerName = view.getPlayerToEdit(currentName, wasHome);

            if (playerName.isEmpty()) {
                return null;
            }

            result = service.getPlayer(playerName);
            player = result.getOutput();

            if (!result.isValid() && !result.isExitFlag()) {

                view.printMessage(result.getMessage());

                if (!view.confirmPlayerCreation(playerName)) {
                    moveOn = false;
                } else {
                    player = new Player();
                    player.setName(playerName);
                }
            }
        } while (!moveOn);

        return player;
    }

}
