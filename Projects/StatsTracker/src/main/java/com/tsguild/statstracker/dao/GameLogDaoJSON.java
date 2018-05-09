/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.statstracker.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tsguild.statstracker.model.Game;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;

/**
 *
 * @author pspethmann
 */
@Component
public class GameLogDaoJSON implements GameLogDao {

    private String masterLog = "masterLog.json";
    private final String DELIMITER = "::";

    public void setMasterLog(String masterLog) {
        this.masterLog = masterLog;
    }

    @Override
    public void add(Game game) throws GameLogDaoException {

        String[] filenames = {masterLog,
            "PlayerLogs/" + game.getAwayPlayerName().toLowerCase() + ".json",
            "PlayerLogs/" + game.getHomePlayerName().toLowerCase() + ".json"
        };

        for (String filename : filenames) {
            writeToFile(game, filename);
        }
    }

    @Override
    public List<Game> getAll(String playerName) throws GameLogDaoException {

        String filename;

        if (playerName.isEmpty()) {
            filename = masterLog;
        } else {
            playerName = playerName.toLowerCase();
            filename = "PlayerLogs/" + playerName + ".json";
        }

        return readFile(filename);
    }

    @Override
    public void remove(Game game) throws
            GameLogDaoException {

        String[] filenames = {masterLog,
            "PlayerLogs/" + game.getAwayPlayerName().toLowerCase() + ".json",
            "PlayerLogs/" + game.getHomePlayerName().toLowerCase() + ".json"
        };

        for (String filename : filenames) {
            List<Game> games = readFile(filename);

            for (Game storedGame : games) {
                if (game.equals(storedGame)) {
                    game = storedGame;
                    break;
                }
            }

            games.remove(game);

            overwriteFile(games, filename);
        }
    }

    private List<Game> readFile(String filename) throws GameLogDaoException {

        Gson gson = new Gson();
        List<Game> games = new ArrayList<>();

        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)))) {

            String gamesJson = sc.nextLine();

            JsonParser parser = new JsonParser();
            JsonArray array = parser.parse(gamesJson).getAsJsonArray();

            for (JsonElement element : array) {
                games.add(gson.fromJson(element, Game.class));
            }

        } catch (FileNotFoundException ex) {
            throw new GameLogDaoException(ex.getMessage());
        }

        return games;
    }

    private void writeToFile(Game game, String filename)
            throws GameLogDaoException {

        File file = new File(filename);
        Gson gson = new Gson();
        List<Game> games;

        if (file.exists()) {
            
            games = readFile(filename);
            games.add(game);
        } else {
            games = new ArrayList<>();
        }

        try (PrintWriter fileOut = new PrintWriter(new FileWriter(filename, true))) {

            String gamesJson = gson.toJson(games);
            fileOut.println(gamesJson);

        } catch (IOException ex) {
            throw new GameLogDaoException(ex.getMessage());
        }
    }

    private void overwriteFile(List<Game> games, String filename)
            throws GameLogDaoException {

        Gson gson = new Gson();

        try (PrintWriter fileOut = new PrintWriter(new FileWriter(filename))) {

            String gamesJson = gson.toJson(games);
            fileOut.println(gamesJson);

        } catch (IOException ex) {
            throw new GameLogDaoException(ex.getMessage());
        }
    }
}
