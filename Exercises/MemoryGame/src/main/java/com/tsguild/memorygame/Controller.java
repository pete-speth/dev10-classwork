/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.memorygame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pspethmann
 */

@RestController
public class Controller {
    
    int player1Score = 0;
    int player2Score = 0;
    
//    public static void main(String[] args) {
//        
//        List<Integer> orderedArray = new ArrayList<>();
//        
//        for (int i=1; i <= 18; i++){
//            orderedArray.add(i);
//            orderedArray.add(i);
//        }
//        
//        List<Integer> shuffled = shuffleList(orderedArray);
//        System.out.println(shuffled);
//        
//    }
    
    public List<Integer> shuffleList(List<Integer> list){
        
        List<Integer> shuffled = new ArrayList<>();
        
        while (list.size() > 0) {
            
            Random rand = new Random();
            int index = rand.nextInt(list.size());
            shuffled.add(list.get(index));
            list.remove(index);
        }
        
        return shuffled;
    }
    
    @GetMapping("api/cards")
    public List<Integer> getCards() {
        
        List<Integer> orderedArray = new ArrayList<>();
        
        for (int i=1; i <= 18; i++){
            orderedArray.add(i);
            orderedArray.add(i);
        }
        
        List<Integer> shuffled = shuffleList(orderedArray);
        return shuffled;
        
    }
}
