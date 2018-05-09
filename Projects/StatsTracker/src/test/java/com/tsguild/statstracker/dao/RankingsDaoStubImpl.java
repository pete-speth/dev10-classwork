/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.statstracker.dao;

import com.tsguild.statstracker.model.Game;
import com.tsguild.statstracker.model.Player;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pspethmann
 */
public class RankingsDaoStubImpl implements RankingsDao {

    List<Player> players = new ArrayList<>();
    
    @Override
    public List<Player> getAll() throws RankingsDaoException {
        return players;
    }

    @Override
    public Player lookUp(String name) throws RankingsDaoException {
        
        for (Player player : players){
            if (name.equalsIgnoreCase(player.getName())){
                return player;
            }
        }
        
        return null;
    }

    @Override
    public void update(Game game) throws RankingsDaoException {
        
        Player away = null;
        Player home = null;
        
        // Can't use lookUp because the list must be written back to the file
        for (Player storedPlayer : players){
            
            if (game.getAwayPlayerName().equals(storedPlayer.getName())){
                away = storedPlayer;
            }
            if (game.getHomePlayerName().equals(storedPlayer.getName())){
                home = storedPlayer;
            }
        }
        
        if(away == null){
            away = new Player();
            away.setName(game.getAwayPlayerName());
            players.add(away);
        }
        
        if (home == null){
            home = new Player();
            home.setName(game.getHomePlayerName());
            players.add(home);
        }
                
        away.updateRecord(game.getOutcome(), false);
        away.updateStats(game.getAwayGoals(), game.getHomeGoals(), 
                away.getRankingPoints()-home.getRankingPoints());
        
        home.updateRecord(game.getOutcome(), true);
        home.updateStats(game.getHomeGoals(), game.getAwayGoals(), 
                home.getRankingPoints()-away.getRankingPoints());
    }

    @Override
    public void removeFromRecord(Game game) throws RankingsDaoException {
        
        Player away = null;
        Player home = null;
        
        // Can't use lookUp because the list must be written back to the file
        for (Player storedPlayer : players){
            
            if (game.getAwayPlayerName().equals(storedPlayer.getName())){
                away = storedPlayer;
            }
            if (game.getHomePlayerName().equals(storedPlayer.getName())){
                home = storedPlayer;
            }
        }
        
        if (home == null || away == null){
            throw new RankingsDaoException("Remove game failed.");
        }
        
        
        away.removeFromRecord(game.getOutcome(), false);
        away.updateStats(-game.getAwayGoals(), -game.getHomeGoals(),
                -(away.getRankingPoints()-home.getRankingPoints()));
        
        home.removeFromRecord(game.getOutcome(), true);
        home.updateStats(-game.getHomeGoals(), -game.getAwayGoals(),
                -(home.getRankingPoints()-away.getRankingPoints()));
    }
    
}
