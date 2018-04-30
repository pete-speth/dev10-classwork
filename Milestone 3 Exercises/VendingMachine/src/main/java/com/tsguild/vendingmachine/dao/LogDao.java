/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.vendingmachine.dao;

import com.tsguild.vendingmachine.model.PurchaseInfo;

/**
 *
 * @author pspethmann
 */
public interface LogDao {

    void addEntry(PurchaseInfo transaction) throws LogDaoException;
    
}
