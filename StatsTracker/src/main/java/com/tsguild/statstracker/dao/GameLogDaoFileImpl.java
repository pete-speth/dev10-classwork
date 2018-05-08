package com.tsguild.statstracker.dao;

import com.tsguild.statstracker.model.Game;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;

@Component
public class GameLogDaoFileImpl implements GameLogDao {

    private String masterLog = "masterLog.txt";
    private final String DELIMITER = "::";

    public void setMasterLog(String masterLog) {
        this.masterLog = masterLog;
    }

    @Override
    public void add(Game game) throws GameLogDaoException {

        String[] filenames = {masterLog,
            "PlayerLogs/" + game.getAwayPlayerName().toLowerCase() + ".txt",
            "PlayerLogs/" + game.getHomePlayerName().toLowerCase() + ".txt"
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
            filename = "PlayerLogs/" + playerName + ".txt";
        }

        return readFile(filename);
    }

    @Override
    public void remove(Game game) throws
            GameLogDaoException {

        String[] filenames = {masterLog,
            "PlayerLogs/" + game.getAwayPlayerName().toLowerCase() + ".txt",
            "PlayerLogs/" + game.getHomePlayerName().toLowerCase() + ".txt"
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

        List<Game> games = new ArrayList<>();

        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)))) {

            while (sc.hasNextLine()) {
                Game game = new Game();

                String line = sc.nextLine();
                String[] tokens = line.split(DELIMITER);

                try {

                    game.setDate(LocalDate.parse(tokens[0]));
                    game.setAwayPlayerName(tokens[1]);
                    game.setHomePlayerName(tokens[2]);
                    game.setAwayGoals(Integer.parseInt(tokens[3]));
                    game.setHomeGoals(Integer.parseInt(tokens[4]));
                    game.setWentToOT(Boolean.parseBoolean(tokens[5]));
                    game.setOutcome();

                    games.add(game);

                } catch (NumberFormatException ex) {
                    throw new GameLogDaoException(ex.getMessage());
                }

            }

        } catch (FileNotFoundException ex) {
            throw new GameLogDaoException(ex.getMessage());
        }

        return games;
    }

    private void writeToFile(Game game, String filename)
            throws GameLogDaoException {

        try (PrintWriter fileOut = new PrintWriter(new FileWriter(filename, true))) {

            fileOut.println(game.getDate() + DELIMITER
                    + game.getAwayPlayerName() + DELIMITER
                    + game.getHomePlayerName() + DELIMITER
                    + game.getAwayGoals() + DELIMITER
                    + game.getHomeGoals() + DELIMITER
                    + game.getWentToOT() + DELIMITER
                    + game.getOutcome());

        } catch (IOException ex) {
            throw new GameLogDaoException(ex.getMessage());
        }
    }

    private void overwriteFile(List<Game> games, String filename)
            throws GameLogDaoException {

        try (PrintWriter fileOut = new PrintWriter(new FileWriter(filename))) {

            for (Game game : games) {
                fileOut.println(game.getDate() + DELIMITER
                        + game.getAwayPlayerName() + DELIMITER
                        + game.getHomePlayerName() + DELIMITER
                        + game.getAwayGoals() + DELIMITER
                        + game.getHomeGoals() + DELIMITER
                        + game.getWentToOT() + DELIMITER
                        + game.getOutcome());
            }

        } catch (IOException ex) {
            throw new GameLogDaoException(ex.getMessage());
        }
    }

}
