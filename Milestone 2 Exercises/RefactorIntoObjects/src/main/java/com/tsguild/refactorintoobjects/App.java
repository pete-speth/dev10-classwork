/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.refactorintoobjects;

/**
 *
 * @author pspethmann
 */
public class App {
    
    public static void main(String[] args) {
        
        InterestCalculator calc = new InterestCalculator();
        Factorizer factorizer = new Factorizer();
        LuckySevens lucky = new LuckySevens();
        RockPaperScissors rps = new RockPaperScissors();
        
        //calc.calculateInterest();
        //factorizer.factorize();
        //lucky.play();
        //rps.play();
    }
}
