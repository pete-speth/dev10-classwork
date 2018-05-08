/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster;

import com.sg.classroster.controller.ClassRosterController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author pspethmann
 */
public class App {

    public static void main(String[] args) {
//        UserIO io = new UserIOConsoleImpl();
//        ClassRosterView view = new ClassRosterView(io);
//        ClassRosterDao dao = new ClassRosterDaoFileImpl();
//        ClassRosterAuditDao auditDao = new ClassRosterAuditDaoFileImpl();
//        ClassRosterServiceLayer service = new ClassRosterServiceLayerImpl(dao, auditDao);
//        ClassRosterController controller = new ClassRosterController(view, service);
//        
//        controller.run();

        ApplicationContext ctx = 
                new ClassPathXmlApplicationContext("applicationContext.xml");
        ClassRosterController controller = 
                ctx.getBean("controller", ClassRosterController.class);
        controller.run();
    }
}
