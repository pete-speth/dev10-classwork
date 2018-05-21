/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.vendingmachine.dao;

import com.tsguild.vendingmachine.model.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;

/**
 *
 * @author pspethmann
 */
//@Component
public class InventoryDaoFileImpl implements InventoryDao {

    private String filename = "inventory.txt";
    private final String DELIMITER = "::";
    private List<Item> stock;
    
    public InventoryDaoFileImpl(){
    }
    
    public InventoryDaoFileImpl(String filename){
        
        this.filename = filename;
    }

    @Override
    public List<Item> getStock() throws InventoryDaoException {
        readFile();
        return stock;
    }

    @Override
    public Item lookUpItem(int id) {
        for (Item item : stock) {
            if (id == item.getId()) {
                return item;
            }
        }

        return null;
    }

    @Override
    public void update(Item item) throws InventoryDaoException {
        writeFile();
    }

    private void readFile() throws InventoryDaoException {

        stock = new ArrayList<>();

        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)))) {
            while (sc.hasNextLine()) {
                Item item = new Item();
                String[] values = sc.nextLine().split(DELIMITER);

                item.setId(Integer.parseInt(values[0]));
                item.setName(values[1]);
                item.setCost(new BigDecimal(values[2]));
                item.setCount(Integer.parseInt(values[3]));

                stock.add(item);
            }
        } catch (FileNotFoundException ex) {
            throw new InventoryDaoException(ex.getMessage());
        }
    }

    private void writeFile() throws InventoryDaoException {

        try (PrintWriter writer = new PrintWriter(filename)) {

            for (Item item : stock) {
                writer.println(item.getId() + DELIMITER
                        + item.getName() + DELIMITER
                        + item.getCost() + DELIMITER
                        + item.getCount());
            }
        } catch (FileNotFoundException ex) {
            throw new InventoryDaoException(ex.getMessage());
        }
    }

}
