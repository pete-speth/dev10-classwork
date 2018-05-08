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
import com.tsguild.statstracker.model.Player;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;

/**
 *
 * @author pspethmann
 */
//@Component
public class RankingsDaoJSON implements RankingsDao {

    private String filename = "rankings.json";
    private final String DELIMITER = "::";

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public List<Player> getAll() throws RankingsDaoException {

        return readFile();
    }

    @Override
    public Player lookUp(String name) throws RankingsDaoException {

        List<Player> players = readFile();

        if (players != null) {
            for (Player player : players) {
                if (player.getName().equalsIgnoreCase(name)) {
                    return player;
                }
            }
        }

        return null;
    }

    public void update(Game game) throws RankingsDaoException {

        List<Player> players = readFile();
        Player away = null;
        Player home = null;

        // Can't use lookUp because the list must be written back to the file
        if (players != null) {
            for (Player storedPlayer : players) {

                if (game.getAwayPlayerName().equals(storedPlayer.getName())) {
                    away = storedPlayer;
                }
                if (game.getHomePlayerName().equals(storedPlayer.getName())) {
                    home = storedPlayer;
                }
            }
        } else {
            players = new ArrayList<>();
        }

        if (away == null) {
            away = new Player();
            away.setName(game.getAwayPlayerName());
            players.add(away);
        }

        if (home == null) {
            home = new Player();
            home.setName(game.getHomePlayerName());
            players.add(home);
        }

        away.updateRecord(game.getOutcome(), false);
        away.updateStats(game.getAwayGoals(), game.getHomeGoals(),
                away.getRankingPoints() - home.getRankingPoints());

        home.updateRecord(game.getOutcome(), true);
        home.updateStats(game.getHomeGoals(), game.getAwayGoals(),
                home.getRankingPoints() - away.getRankingPoints());

        writeFile(players);
    }

    public void removeFromRecord(Game game) throws RankingsDaoException {

        List<Player> players = readFile();

        Player away = null;
        Player home = null;

        // Can't use lookUp because the list must be written back to the file
        for (Player storedPlayer : players) {

            if (game.getAwayPlayerName().equals(storedPlayer.getName())) {
                away = storedPlayer;
            }
            if (game.getHomePlayerName().equals(storedPlayer.getName())) {
                home = storedPlayer;
            }
        }

        if (home == null || away == null) {
            throw new RankingsDaoException("Remove game failed.");
        }

        away.removeFromRecord(game.getOutcome(), false);
        away.updateStats(-game.getAwayGoals(), -game.getHomeGoals(),
                -(away.getRankingPoints() - home.getRankingPoints()));

        home.removeFromRecord(game.getOutcome(), true);
        home.updateStats(-game.getHomeGoals(), -game.getAwayGoals(),
                -(home.getRankingPoints() - away.getRankingPoints()));

        writeFile(players);
    }

    private List<Player> readFile() throws RankingsDaoException {

        Gson gson = new Gson();
        List<Player> players = new ArrayList<>();

        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)))) {

            String playersJson = sc.nextLine();

            JsonParser parser = new JsonParser();
            JsonArray array = parser.parse(playersJson).getAsJsonArray();
            
            for ( JsonElement element : array) {
                players.add(gson.fromJson(element, Player.class));
            }

        } catch (FileNotFoundException ex) {
            throw new RankingsDaoException(ex.getMessage());
        }

        return players;
    }

    private void writeFile(List<Player> players) throws RankingsDaoException {

        Gson gson = new Gson();

        String playersJson = gson.toJson(players);

        try (PrintWriter fileOut = new PrintWriter(filename)) {
            fileOut.println(playersJson);
        } catch (FileNotFoundException ex) {
            throw new RankingsDaoException(ex.getMessage());
        }
    }
}
