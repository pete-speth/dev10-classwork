/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.vendingmachine;

import com.tsguild.vendingmachine.controller.Controller;
import com.tsguild.vendingmachine.dao.InventoryDao;
import com.tsguild.vendingmachine.dao.InventoryDaoFileImpl;
import com.tsguild.vendingmachine.dao.LogDao;
import com.tsguild.vendingmachine.dao.LogDaoFileImpl;
import com.tsguild.vendingmachine.service.VendingService;

/**
 *
 * @author pspethmann
 */
public class App {

    public static void main(String[] args) {
        InventoryDao inventory = new InventoryDaoFileImpl();
        LogDao log = new LogDaoFileImpl();
        VendingService service = new VendingService(inventory, log);
        Controller controller = new Controller(service);
        controller.run();
    }
}
