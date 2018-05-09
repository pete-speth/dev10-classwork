package com.tsguild.statstracker.service;

import com.tsguild.statstracker.dao.GameLogDao;
import com.tsguild.statstracker.dao.GameLogDaoException;
import com.tsguild.statstracker.dao.RankingsDao;
import com.tsguild.statstracker.dao.RankingsDaoException;
import com.tsguild.statstracker.model.Game;
import com.tsguild.statstracker.model.Player;
import com.tsguild.statstracker.model.Result;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatsService {

    RankingsDao rankings;
    GameLogDao gameLog;

    @Autowired
    public StatsService(RankingsDao rankings, GameLogDao gameLog) {
        this.rankings = rankings;
        this.gameLog = gameLog;
    }

    public Result<List<Player>> getRankings() {

        Result<List<Player>> result = new Result();

        try {
            List<Player> players = rankings.getAll();

            if (players == null || players.isEmpty()) {
                result.setValid(false);
                result.setMessage("There are no players on file.");
            } else {

                // Ensure that the players are sorted by ranking points
                players = players.stream()
                        .sorted((p1, p2) -> -Double.compare(
                        p1.getRankingPoints(), p2.getRankingPoints()))
                        .collect(Collectors.toList());

                result.setValid(true);
                result.setMessage("Players loaded successfully");
            }

            result.setOutput(players);

        } catch (RankingsDaoException ex) {

            result.setValid(false);
            result.setMessage("Error loading players.");
            result.setOutput(null);
            result.setExitFlag(true);
        }

        return result;
    }

    public Result<Player> getPlayer(String name) {

        Result<Player> result = new Result();

        try {
            Player player = rankings.lookUp(name);

            if (player == null) {
                result.setValid(false);
                result.setMessage(name + " is not on file as a player.");
            } else {
                result.setValid(true);
                result.setMessage(name + " found.");
            }

            result.setOutput(player);

        } catch (RankingsDaoException ex) {

            result.setValid(false);
            result.setMessage("Error loading players.");
            result.setOutput(null);
            result.setExitFlag(true);
        }

        return result;
    }

    public Result<List<Game>> getRecentGames(String playerName) {

        Result<List<Game>> result;
        List<Game> games;

        result = fetchGames(playerName);
        games = result.getOutput();

        if (result.isValid()) {

            // Return only the 5 most recent games
            if (games.size() > 5) {
                games = games.subList(games.size() - 5, games.size() - 1);
            }

            result.setOutput(games);
        }

        return result;
    }

    public Result<List<Game>> getGames(String playerName, LocalDate date) {

        Result<List<Game>> result;
        List<Game> games;

        result = fetchGames(playerName);
        games = result.getOutput();

        if (result.isValid()) {
            // Filter games by date
            games = games.stream()
                    .filter(g -> g.getDate().equals(date))
                    .collect(Collectors.toList());

            if (games.isEmpty()) {
                result.setMessage("No games found on " + date.format(
                        DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            }

            result.setOutput(games);
        }

        return result;
    }

    public Result addGame(Game game) {

        Result result = new Result();

        try {
            rankings.update(game);
            gameLog.add(game);

            result.setValid(true);
            result.setMessage("Game added successfully.");

        } catch (RankingsDaoException ex) {

            result.setValid(false);
            result.setMessage("Error updating the rankings.");
            result.setExitFlag(true);

        } catch (GameLogDaoException ex) {

            result.setValid(false);
            result.setMessage("Error updating the game logs.");
            result.setExitFlag(true);
        }

        return result;
    }

    public Result removeGame(Game game) {

        Result result = new Result();

        try {
            rankings.removeFromRecord(game);
            gameLog.remove(game);

            result.setValid(true);
            result.setMessage("Game removed successfully.");

        } catch (RankingsDaoException ex) {

            result.setValid(false);
            result.setMessage("Error updating the rankings.");
            result.setExitFlag(true);

        } catch (GameLogDaoException ex) {

            result.setValid(false);
            result.setMessage("Error updating the game logs.");
            result.setExitFlag(true);
        }

        return result;
    }

    private Result<List<Game>> fetchGames(String playerName) {

        Result<List<Game>> result = new Result();
        List<Game> games;

        // User master game log if no player is specified
        if (playerName.equals("")) {
            try {
                games = gameLog.getAll(playerName);

                if (games.isEmpty()) {
                    result.setValid(true);
                    result.setMessage("No recent games to display.");
                } else {
                    result.setValid(true);
                    result.setMessage(games.size() + " games found.");
                }

                result.setOutput(games);

            } catch (GameLogDaoException ex) {
                result.setValid(false);
                result.setMessage("Error loading game log.");
                result.setOutput(null);
                result.setExitFlag(true);
            }

            // Find player log file if player is specified    
        } else {

            // Get a list of all players
            try {
                List<Player> players = rankings.getAll();
                games = null;

                for (Player player : players) {
                    if (player.getName().equalsIgnoreCase(playerName)) {
                        games = gameLog.getAll(playerName);
                        playerName = player.getName();
                        break;
                    }
                }

                if (games == null) {
                    result.setValid(false);
                    result.setMessage("Player is not on file.");
                } else if (games.isEmpty()) {
                    result.setValid(true);
                    result.setMessage("No games to display for " + playerName);
                } else {
                    result.setValid(true);
                    result.setMessage(games.size()
                            + " games found for " + playerName);
                }

                result.setOutput(games);

            } catch (RankingsDaoException ex) {
                result.setValid(false);
                result.setMessage("Error loading players.");
                result.setOutput(null);
                result.setExitFlag(true);
            } catch (GameLogDaoException ex) {
                result.setValid(false);
                result.setMessage("Error loading game log.");
                result.setOutput(null);
                result.setExitFlag(true);
            }

        }

        return result;
    }

}
