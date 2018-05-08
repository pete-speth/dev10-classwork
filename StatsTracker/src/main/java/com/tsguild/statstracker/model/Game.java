package com.tsguild.statstracker.model;

import java.time.LocalDate;
import java.util.Objects;

public class Game implements Cloneable {

    LocalDate date;
    String awayPlayerName;
    String homePlayerName;
    int awayGoals;
    int homeGoals;
    boolean wentToOT;
    GameOutcome outcome;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getAwayPlayerName() {
        return awayPlayerName;
    }

    public void setAwayPlayerName(String awayPlayerName) {
        this.awayPlayerName = awayPlayerName;
    }

    public String getHomePlayerName() {
        return homePlayerName;
    }

    public void setHomePlayerName(String homePlayerName) {
        this.homePlayerName = homePlayerName;
    }

    public int getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }

    public int getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }

    public boolean getWentToOT() {
        return wentToOT;
    }

    public void setWentToOT(boolean wentToOT) {
        this.wentToOT = wentToOT;
    }

    public GameOutcome getOutcome() {
        return outcome;
    }

    public void setOutcome() {

        if (homeGoals > awayGoals) {
            if (wentToOT) {
                outcome = GameOutcome.HOME_WIN_OT;
            } else {
                outcome = GameOutcome.HOME_WIN_REG;
            }
        } else {
            if (wentToOT) {
                outcome = GameOutcome.HOME_LOSS_OT;
            } else {
                outcome = GameOutcome.HOME_LOSS_REG;
            }
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Game other = (Game) obj;
        if (this.awayGoals != other.awayGoals) {
            return false;
        }
        if (this.homeGoals != other.homeGoals) {
            return false;
        }
        if (this.wentToOT != other.wentToOT) {
            return false;
        }
        if (!Objects.equals(this.awayPlayerName, other.awayPlayerName)) {
            return false;
        }
        if (!Objects.equals(this.homePlayerName, other.homePlayerName)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (this.outcome != other.outcome) {
            return false;
        }
        return true;
    }
    
    @Override
    public Game clone() {
        
        Game game = new Game();
        
        game.date = this.date;
        game.awayPlayerName = this.awayPlayerName;
        game.homePlayerName = this.homePlayerName;
        game.awayGoals = this.awayGoals;
        game.homeGoals = this.homeGoals;
        game.wentToOT = this.wentToOT;
        game.outcome = this.outcome;
        
        return game;
    }
    
    

}
