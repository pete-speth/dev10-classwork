/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.statstracker.dao;

import com.tsguild.statstracker.model.Game;
import java.util.List;

/**
 *
 * @author pspethmann
 */
public interface GameLogDao {

    public void add(Game game) throws GameLogDaoException;
    public List<Game> getAll(String playerName) throws GameLogDaoException;
    public void remove(Game game) throws GameLogDaoException;
}
