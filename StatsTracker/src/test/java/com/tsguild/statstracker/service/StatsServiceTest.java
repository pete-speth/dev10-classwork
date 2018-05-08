/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.statstracker.service;

import com.tsguild.statstracker.dao.GameLogDao;
import com.tsguild.statstracker.dao.GameLogDaoAlwaysFails;
import com.tsguild.statstracker.dao.GameLogDaoStubImpl;
import com.tsguild.statstracker.dao.RankingsDao;
import com.tsguild.statstracker.dao.RankingsDaoAlwaysFails;
import com.tsguild.statstracker.dao.RankingsDaoStubImpl;
import com.tsguild.statstracker.model.Game;
import com.tsguild.statstracker.model.Player;
import com.tsguild.statstracker.model.Result;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 *
 * @author pspethmann
 */
public class StatsServiceTest {

    RankingsDao rankings = new RankingsDaoStubImpl();
    GameLogDao gameLog = new GameLogDaoStubImpl();
    StatsService service = new StatsService(rankings, gameLog);
    
    RankingsDao failRankings = new RankingsDaoAlwaysFails();
    GameLogDao failLog = new GameLogDaoAlwaysFails();
    StatsService failService = new StatsService(failRankings, failLog);

    @Test
    public void testGetRankings() {

        addSampleGames();

        Result<List<Player>> result = service.getRankings();
        List<Player> players = result.getOutput();

        // Check that all players are loaded
        assertEquals(4, players.size());
        assertTrue(result.isValid());
        assertFalse(result.isExitFlag());

        // Check that players are ordered decreasing by ranking points
        double lastRank = Double.MAX_VALUE;

        for (Player player : players) {
            if (player.getRankingPoints() > lastRank) {
                fail("Players are not ordered by ranking points");
            }
        }
    }

    @Test
    public void testGetRankingsEmpty() {

        Result<List<Player>> result = service.getRankings();

        assertTrue(result.getOutput().isEmpty());
        assertFalse(result.isValid());
        assertFalse(result.isExitFlag());

    }

    @Test
    public void testGetRankingsDaoFail() {
        
        addSampleGames();
        
        Result<List<Player>> result = failService.getRankings();
        
        assertFalse(result.isValid());
        assertTrue(result.isExitFlag());
    }

    @Test
    public void testGetPlayer() {

        addSampleGames();

        Result<Player> result = service.getPlayer("testAway2");
        Player player = result.getOutput();

        assertEquals(1, player.getLossesReg());
        assertTrue(result.isValid());
        assertFalse(result.isExitFlag());
    }

    @Test
    public void testGetPlayerNull() {

        addSampleGames();

        Result<Player> result = service.getPlayer("iDontExist");
        Player player = result.getOutput();

        assertEquals(player, null);
        assertFalse(result.isValid());
        assertFalse(result.isExitFlag());
    }

    @Test
    public void testGetPlayerDaoFail() {
        
        addSampleGames();
        
        Result<Player> result = failService.getPlayer("testAway2");
        
        assertFalse(result.isValid());
        assertTrue(result.isExitFlag());
    }

    @Test
    public void testGetRecentGamesAllPlayers() {

        addSampleGames();

        Result<List<Game>> result = service.getRecentGames("");
        List<Game> games = result.getOutput();

        assertEquals(2, games.size());
        assertTrue(result.isValid());
        assertFalse(result.isExitFlag());
    }

    @Test
    public void testGetRecentGamesSpecificPlayer() {

        addSampleGames();

        Result<List<Game>> result = service.getRecentGames("testAway1");
        List<Game> games = result.getOutput();

        assertEquals(1, games.size());
        assertTrue(result.isValid());
        assertFalse(result.isExitFlag());
    }

    @Test
    public void testGetRecentGamesEmpty() throws Exception {

        addSampleGames();
        List<Game> games = gameLog.getAll("");
        service.removeGame(games.get(0));

        Result<List<Game>> result = service.getRecentGames("testAway1");
        games = result.getOutput();

        assertTrue(games.isEmpty());
        assertTrue(result.isValid());
        assertFalse(result.isExitFlag());
    }

    @Test
    public void testGetRecentGamesNull() {

        Result<List<Game>> result = service.getRecentGames("testAway1");
        List<Game> games = result.getOutput();

        assertEquals(null, games);
        assertFalse(result.isValid());
        assertFalse(result.isExitFlag());

    }

    @Test
    public void testGetRecentGamesDaoFail() {

        addSampleGames();
        
        Result<List<Game>> result = failService.getRecentGames("");
        
        assertFalse(result.isValid());
        assertTrue(result.isExitFlag());
    }

    @Test
    public void testGetGames() {

        addSampleGames();

        Result<List<Game>> result = service.getGames(
                "", LocalDate.of(2018, Month.MAY, 5));
        List<Game> games = result.getOutput();

        assertEquals(1, games.size());
        assertTrue(result.isValid());
        assertFalse(result.isExitFlag());

        result = service.getGames(
                "testAway2", LocalDate.of(2018, Month.MAY, 5));
        games = result.getOutput();

        assertTrue(games.isEmpty());
        assertTrue(result.isValid());
        assertFalse(result.isExitFlag());
    }

    @Test
    public void testGetGamesEmpty() {

        Result<List<Game>> result = service.getGames(
                "", LocalDate.of(2018, Month.MAY, 5));
        List<Game> games = result.getOutput();

        assertTrue(games.isEmpty());
        assertTrue(result.isValid());
        assertFalse(result.isExitFlag());
    }

    @Test
    public void testGetGamesNull() {

        Result<List<Game>> result = service.getGames(
                "iDontExist", LocalDate.of(2018, Month.MAY, 5));
        List<Game> games = result.getOutput();

        assertEquals(null, games);
        assertFalse(result.isValid());
        assertFalse(result.isExitFlag());
    }

    @Test
    public void testGetGamesDaoFail() {
        
        addSampleGames();
        
        Result<List<Game>> result = failService.getGames(
                "", LocalDate.of(2018, Month.MAY, 5));
        
        assertFalse(result.isValid());
        assertTrue(result.isExitFlag());
    }

    @Test
    public void testAddGame() throws Exception {

        addSampleGames();

        Game game = new Game();
        game.setDate(LocalDate.of(2018, Month.MAY, 7));
        game.setAwayPlayerName("testAway3");
        game.setHomePlayerName("testHome3");
        game.setAwayGoals(2);
        game.setHomeGoals(1);
        game.setWentToOT(true);
        game.setOutcome();

        Result result = service.addGame(game);

        assertTrue(result.isValid());
        assertFalse(result.isExitFlag());

        List<Game> games = gameLog.getAll("");

        assertEquals(3, games.size());
    }

    @Test
    public void testAddGameDaoFail() {
        
        addSampleGames();

        Game game = new Game();
        game.setDate(LocalDate.of(2018, Month.MAY, 7));
        game.setAwayPlayerName("testAway3");
        game.setHomePlayerName("testHome3");
        game.setAwayGoals(2);
        game.setHomeGoals(1);
        game.setWentToOT(true);
        game.setOutcome();

        Result result = failService.addGame(game);
        
        assertFalse(result.isValid());
        assertTrue(result.isExitFlag());
    }

    @Test
    public void testRemoveGame() throws Exception {
        
        addSampleGames();
        
        List<Game> games = gameLog.getAll("");       
        Result result = service.removeGame(games.get(1));
        
        assertEquals(1,games.size());
        assertTrue(result.isValid());
        assertFalse(result.isExitFlag());
    }

    @Test
    public void testRemoveGameDaoFail() throws Exception {
        
        addSampleGames();
        
        List<Game> games = gameLog.getAll("");       
        Result result = failService.removeGame(games.get(1));
        
        assertFalse(result.isValid());
        assertTrue(result.isExitFlag());
    }

    private void addSampleGames() {
        Game game1 = new Game();
        game1.setDate(LocalDate.of(2018, Month.MAY, 5));
        game1.setAwayPlayerName("testAway1");
        game1.setHomePlayerName("testHome1");
        game1.setAwayGoals(3);
        game1.setHomeGoals(1);
        game1.setWentToOT(false);
        game1.setOutcome();

        Game game2 = new Game();
        game2.setDate(LocalDate.of(2018, Month.MAY, 6));
        game2.setAwayPlayerName("testAway2");
        game2.setHomePlayerName("testHome2");
        game2.setAwayGoals(1);
        game2.setHomeGoals(3);
        game2.setWentToOT(false);
        game2.setOutcome();

        try {
            gameLog.add(game1);
            gameLog.add(game2);

            rankings.update(game1);
            rankings.update(game2);

        } catch (Exception ex) {
            fail("Setup failed.");
        }
    }
}
