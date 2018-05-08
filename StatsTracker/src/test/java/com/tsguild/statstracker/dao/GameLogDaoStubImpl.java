/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.statstracker.dao;

import com.tsguild.statstracker.model.Game;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author pspethmann
 */
public class GameLogDaoStubImpl implements GameLogDao {
    
    List<Game> games = new ArrayList<>();

    @Override
    public void add(Game game) throws GameLogDaoException {
        games.add(game);
    }

    @Override
    public List<Game> getAll(String playerName) throws GameLogDaoException {
        
        if (!playerName.isEmpty() && !games.isEmpty()){
        games = games.stream()
                .filter(g -> (g.getAwayPlayerName().equalsIgnoreCase(playerName)
                        || g.getHomePlayerName().equalsIgnoreCase(playerName)))
                .collect(Collectors.toList());
        }
        
        return games;
    }

    @Override
    public void remove(Game game) throws GameLogDaoException {
        
        for (Game storedGame : games){
            if (game.equals(storedGame)){
                game = storedGame;
                break;
            }
        }
        
        games.remove(game);
    }
    
}
