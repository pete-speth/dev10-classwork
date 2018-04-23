/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.ticticatoe;

/**
 *
 * @author pspethmann
 */
public class App {

    public static void main(String[] args) {

        HumanPlayer human = new HumanPlayer();
        ComputerPlayer comp = new ComputerPlayer();
        Game game = new Game(human, comp);

        game.playGame();
    }

}
