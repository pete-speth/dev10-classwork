/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.statstracker.dao;

import com.tsguild.statstracker.model.Game;
import com.tsguild.statstracker.model.Player;
import java.util.List;

/**
 *
 * @author pspethmann
 */
public interface RankingsDao {
    
    public List<Player> getAll() throws RankingsDaoException;
    public Player lookUp(String name) throws RankingsDaoException;
    public void update(Game game) throws RankingsDaoException;
    public void removeFromRecord(Game game) throws RankingsDaoException;
}
