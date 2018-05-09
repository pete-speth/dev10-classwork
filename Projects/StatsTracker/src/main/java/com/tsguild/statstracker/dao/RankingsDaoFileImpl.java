package com.tsguild.statstracker.dao;

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

//@Component
public class RankingsDaoFileImpl implements RankingsDao {

    private String filename = "rankings.txt";
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

        for (Player player : players) {
            if (player.getName().equalsIgnoreCase(name)) {
                return player;
            }
        }

        return null;
    }

    public void update(Game game) throws RankingsDaoException {

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

        List<Player> players = new ArrayList<>();

        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)))) {

            while (sc.hasNextLine()) {
                Player player = new Player();
                String line = sc.nextLine();
                String[] tokens = line.split(DELIMITER);

                try {
                    player.setName(tokens[0]);
                    player.setWins(Integer.parseInt(tokens[1]));
                    player.setLossesReg(Integer.parseInt(tokens[2]));
                    player.setLossesOT(Integer.parseInt(tokens[3]));
                    player.setGoalsFor(Integer.parseInt(tokens[4]));
                    player.setGoalsAgainst(Integer.parseInt(tokens[5]));
                    player.setGoalDiff(Integer.parseInt(tokens[6]));
                    player.setAvgGoalsFor(Double.parseDouble(tokens[7]));
                    player.setAvgGoalsAgainst(Double.parseDouble(tokens[8]));
                    player.setRankingPoints(Double.parseDouble(tokens[9]));
                    player.setRankingAdjustments(Double.parseDouble(tokens[10]));
                } catch (NumberFormatException ex) {
                    throw new RankingsDaoException(ex.getMessage());
                }

                players.add(player);
            }

        } catch (FileNotFoundException ex) {
            throw new RankingsDaoException(ex.getMessage());
        }

        return players;
    }

    private void writeFile(List<Player> players) throws RankingsDaoException {

        try (PrintWriter fileOut = new PrintWriter(filename)) {

            for (Player player : players) {
                fileOut.println(player.getName() + DELIMITER
                        + player.getWins() + DELIMITER
                        + player.getLossesReg() + DELIMITER
                        + player.getLossesOT() + DELIMITER
                        + player.getGoalsFor() + DELIMITER
                        + player.getGoalsAgainst() + DELIMITER
                        + player.getGoalDiff() + DELIMITER
                        + player.getAvgGoalsFor() + DELIMITER
                        + player.getAvgGoalsAgainst() + DELIMITER
                        + player.getRankingPoints() + DELIMITER
                        + player.getRankingAdjustments());
            }

        } catch (FileNotFoundException ex) {
            throw new RankingsDaoException(ex.getMessage());
        }
    }

}
