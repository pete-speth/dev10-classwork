package com.tsguild.statstracker.model;

import java.util.Objects;

public class Player {

    String name;
    int wins;
    int lossesReg;
    int lossesOT;
    int goalsFor;
    int goalsAgainst;
    int goalDiff;
    double avgGoalsFor;
    double avgGoalsAgainst;
    double rankingPoints;
    double rankingAdjustments = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public int getLossesReg() {
        return lossesReg;
    }

    public int getLossesOT() {
        return lossesOT;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public int getGoalDiff() {
        return goalDiff;
    }

    public double getAvgGoalsFor() {
        return avgGoalsFor;
    }

    public double getAvgGoalsAgainst() {
        return avgGoalsAgainst;
    }

    public double getRankingPoints() {
        return rankingPoints;
    }
    
    
    public double getRankingAdjustments() {
        return rankingAdjustments;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLossesReg(int lossesReg) {
        this.lossesReg = lossesReg;
    }

    public void setLossesOT(int lossesOT) {
        this.lossesOT = lossesOT;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public void setGoalDiff(int goalDiff) {
        this.goalDiff = goalDiff;
    }

    public void setAvgGoalsFor(double avgGoalsFor) {
        this.avgGoalsFor = avgGoalsFor;
    }

    public void setAvgGoalsAgainst(double avgGoalsAgainst) {
        this.avgGoalsAgainst = avgGoalsAgainst;
    }

    public void setRankingPoints(double rankingPoints) {
        this.rankingPoints = rankingPoints;
    }

    public void setRankingAdjustments(double rankingAdjustments) {
        this.rankingAdjustments = rankingAdjustments;
    }
    
    

    public void updateRecord(GameOutcome outcome, boolean wasHome) {

        switch (outcome) {
            case HOME_WIN_REG:
                if (wasHome) {
                    wins++;
                } else {
                    lossesReg++;
                }
                break;
            case HOME_WIN_OT:
                if (wasHome) {
                    wins++;
                } else {
                    lossesOT++;
                }
                break;
            case HOME_LOSS_REG:
                if (wasHome) {
                    lossesReg++;
                } else {
                    wins++;
                }
                break;
            case HOME_LOSS_OT:
                if (wasHome) {
                    lossesOT++;
                } else {
                    wins++;
                }
                break;
        }
    }

    public void removeFromRecord(GameOutcome outcome, boolean wasHome) {

        switch (outcome) {
            case HOME_WIN_REG:
                if (wasHome) {
                    wins--;
                } else {
                    lossesReg--;
                }
                break;
            case HOME_WIN_OT:
                if (wasHome) {
                    wins--;
                } else {
                    lossesOT--;
                }
                break;
            case HOME_LOSS_REG:
                if (wasHome) {
                    lossesReg--;
                } else {
                    wins--;
                }
                break;
            case HOME_LOSS_OT:
                if (wasHome) {
                    lossesOT--;
                } else {
                    wins--;
                }
                break;
        }
    }

    public void updateStats(int numGoalsFor, int numGoalsAgainst, double rankingDiff) {
        goalsFor += numGoalsFor;
        goalsAgainst += numGoalsAgainst;

        goalDiff = goalsFor - goalsAgainst;
        avgGoalsFor = (double) goalsFor / (wins + lossesReg + lossesOT);
        avgGoalsAgainst = (double) goalsFor / (wins + lossesReg + lossesOT);

        /*
        The ranking system works as follows:
        A player gets 1 point for a win, 0 for an OT loss, and -1 for a 
        regulation loss.
        To adjust these points based on strength of opponent, the equation
        used is:
        
        BasePts - (playerRankPts-OpponentRankPts)/100
        
        This equation is used up to a point difference of 100. Aboce this point,
        the adjustment term is set to 1.
        */
        
        if (Math.abs(rankingDiff) <= 100) {
            rankingAdjustments += rankingDiff / 100;
        } else if (rankingDiff > 0) {
            rankingAdjustments += 1;
        } else {
            rankingAdjustments += -1;
        }
        
        rankingPoints = wins - lossesReg - rankingAdjustments;

    }

}
