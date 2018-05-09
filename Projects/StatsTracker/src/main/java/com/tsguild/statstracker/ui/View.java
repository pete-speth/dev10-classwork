package com.tsguild.statstracker.ui;

import com.tsguild.statstracker.model.Game;
import com.tsguild.statstracker.model.Player;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class View {

    UserIO io;

    @Autowired
    public View(UserIO io) {
        this.io = io;
    }

    public int displayMenuAndGetOption() {
        io.print("");
        io.print("*****NHL STATS TRACKER*****");
        io.print("---------------------------");
        io.print("1. View Current Rankings");
        io.print("2. View Recent Games");
        io.print("3. Search For Games By Date");
        io.print("4. Add a New Game");
        io.print("5. Remove a Game");
        io.print("6. Edit a Game");
        io.print("0. Exit Program");

        return io.readInt("What would you like to do?: ", 0, 6);
    }

    public void displayRankings(List<Player> players) {
        
        DecimalFormat fmt = new DecimalFormat("###.##");
        
        io.print("NAME" + addSpaces(16)
                + "RANK PTS   "
                + "WINS     "
                + "LOSSES   "
                + "OT LOSSES    "
                + "GF    "
                + "GA    "
                + "DIFF    "
                + "GFA    "
                + "GAA");
        
        
        
        for (Player player : players) {
            
            String name = player.getName();
            String rankPts = fmt.format(player.getRankingPoints());
            String wins = ""+player.getWins();
            String losses = ""+player.getLossesReg();
            String lossesOT = ""+player.getLossesOT();
            String gf = ""+player.getGoalsFor();
            String ga = ""+player.getGoalsAgainst();
            String diff = ""+player.getGoalDiff();
            String gfa = fmt.format(player.getAvgGoalsFor());
            String gaa = fmt.format(player.getAvgGoalsAgainst());
            
            io.print(name + addSpaces(20 - name.length())
                    + rankPts + addSpaces(11-rankPts.length())
                    + wins + addSpaces(9-wins.length())
                    + losses + addSpaces(9-losses.length())
                    + lossesOT + addSpaces(13-lossesOT.length())
                    + gf + addSpaces(6-gf.length())
                    + ga + addSpaces(6-ga.length())
                    + diff + addSpaces(8-diff.length())
                    + gfa + addSpaces(7-gfa.length())
                    + gaa);
        }
    }

    public String getPlayerName() {
        io.print("Would you like to view the games for a specific player?");
        return io.readString("Enter player name or press [ENTER]: ");
    }

    public String promptForValidPlayerName(String playerName) {
        return io.readString("Please enter a valid name or press [ENTER]: ");
    }

    public LocalDate getDate() {
        String reply = io.readString(
                "Please enter a date, or press [ENTER] to use current date "
                + "(MM/dd/yyyy): ");

        LocalDate date = null;
        boolean valid = false;

        do {
            try {
                date = LocalDate.parse(reply,
                        DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                valid = true;
            } catch (DateTimeParseException ex) {

                if (reply.isEmpty()) {
                    return LocalDate.now();
                }

                reply = io.readString(
                        "Invalid entry. Please use format (MM/dd/yyyy): ");
                valid = false;
            }
        } while (!valid);

        return date;

    }

    public void displayGames(List<Game> games) {

        for (Game game : games) {
            io.print("------------------");
            io.print(game.getDate().format(
                    DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            io.print(game.getAwayPlayerName() + " vs. " + game.getHomePlayerName());

            switch (game.getOutcome()) {
                case HOME_WIN_REG:
                    io.print(game.getHomePlayerName() + " won " + game.getHomeGoals() + " to " + game.getAwayGoals());
                    break;
                case HOME_WIN_OT:
                    io.print(game.getHomePlayerName() + " won " + game.getHomeGoals() + " to " + game.getAwayGoals() + " in OT");
                    break;
                case HOME_LOSS_REG:
                    io.print(game.getAwayPlayerName() + " won " + game.getAwayGoals() + " to " + game.getHomeGoals());
                    break;
                case HOME_LOSS_OT:
                    io.print(game.getAwayPlayerName() + " won " + game.getAwayGoals() + " to " + game.getHomeGoals() + " in OT");
                    break;
            }
        }
    }

    public int displayListedGames(List<Game> games) {

        int count = 0;

        for (Game game : games) {
            io.print("------------------");
            io.print(count + ". " + game.getDate().format(
                    DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            io.print(game.getAwayPlayerName() + " vs. " + game.getHomePlayerName());

            switch (game.getOutcome()) {
                case HOME_WIN_REG:
                    io.print(game.getHomePlayerName() + " won " + game.getHomeGoals() + " to " + game.getAwayGoals());
                    break;
                case HOME_WIN_OT:
                    io.print(game.getHomePlayerName() + " won " + game.getHomeGoals() + " to " + game.getAwayGoals() + " in OT");
                    break;
                case HOME_LOSS_REG:
                    io.print(game.getAwayPlayerName() + " won " + game.getAwayGoals() + " to " + game.getHomeGoals());
                    break;
                case HOME_LOSS_OT:
                    io.print(game.getAwayPlayerName() + " won " + game.getAwayGoals() + " to " + game.getHomeGoals() + " in OT");
                    break;
            }

            count++;
        }

        return io.readInt("\nPlease select a game: ", 0, games.size() - 1);
    }

    public String getPlayer(boolean wasHome) {

        if (wasHome) {
            return io.readString("Who was the Home player?: ");
        } else {
            return io.readString("Who was the Away player?: ");
        }
    }

    public String getPlayerToEdit(String currentName, boolean wasHome) {

        if (wasHome) {
            return io.readString("Who was the Home player? [" + currentName + "]: ");
        } else {
            return io.readString("Who was the Away player? [" + currentName + "]: ");
        }
    }

    public boolean confirmPlayerCreation(String name) {

        String reply = io.readString("Create a new player file for "
                + name + "? (y/n): ");
        return reply.equalsIgnoreCase("y");
    }

    public void getOtherGameInfo(Game game) {

        int diff = 0;

        while (diff == 0) {
            game.setAwayGoals(io.readInt(
                    "How many goals did " + game.getAwayPlayerName() + " score?: ",
                    0, Integer.MAX_VALUE));
            game.setHomeGoals(io.readInt(
                    "How many goals did " + game.getHomePlayerName() + " score?: ",
                    0, Integer.MAX_VALUE));

            diff = Math.abs(game.getAwayGoals() - game.getHomeGoals());
            if (diff == 0) {
                io.print("Seems fishy... You can't tie in NHL");
            }
        }

        if (diff == 1) {
            String ot = io.readString("Did the game go into OT? (y/n): ");
            game.setWentToOT(ot.equalsIgnoreCase("y"));
        } else {
            game.setWentToOT(false);
        }

        game.setDate(LocalDate.now());
        game.setOutcome();
    }

    public void getEditInfo(Game edited) {

        int diff = 0;

        while (diff == 0) {

            String reply;
            boolean valid = false;

            while (!valid) {
                reply = io.readString("How many goals did "
                        + edited.getAwayPlayerName() + " score?: ["
                        + edited.getAwayGoals() + "]: ");

                if (!reply.isEmpty()) {
                    try {
                        int goals = Integer.parseInt(reply);
                        edited.setAwayGoals(goals);
                        valid = true;
                    } catch (NumberFormatException ex) {
                        io.print("Invalid input.");
                    }
                } else {
                    valid = true;
                }
            }

            valid = false;

            while (!valid) {
                reply = io.readString(
                        "How many goals did " + edited.getHomePlayerName()
                        + " score?: [" + edited.getHomeGoals() + "]: ");
                if (!reply.isEmpty()) {
                    try {
                        int goals = Integer.parseInt(reply);
                        edited.setHomeGoals(goals);
                        valid = true;
                    } catch (NumberFormatException ex) {
                        io.print("Invalid input.");
                    }
                } else {
                    valid = true;
                }
            }

            diff = Math.abs(edited.getAwayGoals() - edited.getHomeGoals());
            if (diff == 0) {
                io.print("Seems fishy... You can't tie in NHL");
            }
        }

        if (diff == 1) {
            String ot = io.readString("Did the game go into OT? (y/n)[" + edited.getWentToOT() + "]: ");
            if (!ot.isEmpty()) {
                edited.setWentToOT(ot.equalsIgnoreCase("y"));
            }
        } else {
            edited.setWentToOT(false);
        }

        edited.setOutcome();
    }

    public void printMessage(String message) {

        io.print(message);
    }

    public void waitForPrompt() {

        io.print("---------------------------");
        io.readString("Press [ENTER] to continue.");
    }
    
    private String addSpaces(int numSpaces) {

        String str = "";
        for (int i = 0; i < numSpaces; i++) {
            str += " ";
        }

        return str;
    }

}
