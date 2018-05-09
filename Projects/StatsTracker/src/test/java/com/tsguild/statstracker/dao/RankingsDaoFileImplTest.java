/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.statstracker.dao;

import com.tsguild.statstracker.model.Game;
import com.tsguild.statstracker.model.Player;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pspethmann
 */
public class RankingsDaoFileImplTest {

    RankingsDaoFileImpl rankings = new RankingsDaoFileImpl();

    @Before
    public void setUp() {
        
        rankings.setFilename("testRankings.txt");
        
        Game game = new Game();
        game.setDate(LocalDate.of(2018, Month.MAY, 5));
        game.setAwayPlayerName("testAway1");
        game.setHomePlayerName("testHome1");
        game.setAwayGoals(3);
        game.setHomeGoals(1);
        game.setWentToOT(false);
        game.setOutcome();

        try {
            PrintWriter fileOut = new PrintWriter("testRankings.txt");
            fileOut.print("");
            fileOut.close();
            
            rankings.update(game);
            
        } catch (Exception ex) {
            fail("Could not update rankings in setup");
        }

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testUpdateAndGetAll() throws Exception {

        Game game = new Game();
        game.setDate(LocalDate.of(2018, Month.MAY, 6));
        game.setAwayPlayerName("testAway2");
        game.setHomePlayerName("testHome2");
        game.setAwayGoals(1);
        game.setHomeGoals(3);
        game.setWentToOT(false);
        game.setOutcome();

        rankings.update(game);

        List<Player> players = rankings.getAll();
        assertEquals(4, players.size());
    }
    
    @Test
    public void testLookUp() throws Exception {
        
        Player player = rankings.lookUp("testHome1");
        
        assertEquals(1, player.getLossesReg());
    }

}
