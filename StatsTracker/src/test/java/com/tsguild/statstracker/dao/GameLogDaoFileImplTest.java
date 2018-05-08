/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.statstracker.dao;

import com.tsguild.statstracker.model.Game;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author pspethmann
 */
public class GameLogDaoFileImplTest {

    GameLogDaoFileImpl log = new GameLogDaoFileImpl();

    Game game1;
    Game game2;

    @Before
    public void setUp() {

        log.setMasterLog("testMasterLog.txt");

        try {

            String[] playerNames = {"", "testAway1", "testHome1", "testAway2",
                "testHome2"};

            for (String name : playerNames) {
                List<Game> games = log.getAll(name);
                for (Game game : games) {
                    log.remove(game);
                }
            }

        } catch (Exception ex) {
            fail("Couldn't clear file during setup.");
        }

        game1 = new Game();
        game1.setDate(LocalDate.of(2018, Month.MAY, 5));
        game1.setAwayPlayerName("testAway1");
        game1.setHomePlayerName("testHome1");
        game1.setAwayGoals(3);
        game1.setHomeGoals(1);
        game1.setWentToOT(false);
        game1.setOutcome();

        game2 = new Game();
        game2.setDate(LocalDate.of(2018, Month.MAY, 6));
        game2.setAwayPlayerName("testAway2");
        game2.setHomePlayerName("testHome2");
        game2.setAwayGoals(1);
        game2.setHomeGoals(3);
        game2.setWentToOT(false);
        game2.setOutcome();

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddAndGetAll() throws Exception {

        log.add(game1);
        log.add(game2);

        // Get all from Master log
        List<Game> games = log.getAll("");
        assertEquals(2, games.size());

        // Get all from a particular player
        games = log.getAll("testAway2");
        assertEquals(1, games.size());
    }

    @Test
    public void testRemove() throws Exception {

        List<Game> games;

        log.add(game1);
        log.add(game2);

        log.remove(game1);

        games = log.getAll("");
        assertEquals(1, games.size());

        games = log.getAll("testHome1");
        assertEquals(0, games.size());

        games = log.getAll("testAway1");
        assertEquals(0, games.size());

        log.remove(game2);

        games = log.getAll("");
        assertEquals(0, games.size());

        games = log.getAll("testHome2");
        assertEquals(0, games.size());

        games = log.getAll("testAway2");
        assertEquals(0, games.size());

    }

}
