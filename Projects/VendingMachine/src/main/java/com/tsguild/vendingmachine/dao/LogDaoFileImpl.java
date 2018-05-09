/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.vendingmachine.dao;

import com.tsguild.vendingmachine.model.PurchaseInfo;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author pspethmann
 */
public class LogDaoFileImpl implements LogDao {

    private String filename = "vendingLog.txt";
    private final String DELIMITER = ", ";
    
    public LogDaoFileImpl(){
    }
    
    public LogDaoFileImpl(String filename){
        this.filename = filename;
    }

    @Override
    public void addEntry(PurchaseInfo transaction) throws LogDaoException {

        writeEntryToFile(transaction);
    }

    private void writeEntryToFile(PurchaseInfo transaction)
            throws LogDaoException {

        try (PrintWriter writer = new PrintWriter(
                new FileWriter(filename, true))) {

            writer.println(transaction.getTimestamp().format(DateTimeFormatter
                    .ofPattern("MM/dd/yyyy HH:mm:ss")) + DELIMITER
                    + transaction.getItemName() + DELIMITER
                    + transaction.getOutcome() + DELIMITER
                    + transaction.getItemsLeft());

        } catch (IOException ex) {
            throw new LogDaoException(ex.getMessage());
        }
    }
}
