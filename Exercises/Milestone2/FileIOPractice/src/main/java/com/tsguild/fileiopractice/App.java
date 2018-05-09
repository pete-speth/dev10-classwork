/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.fileiopractice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author pspethmann
 */
public class App {

    public static void main(String[] args) throws Exception {

        // Writing to file
        PrintWriter out = new PrintWriter(new FileWriter("OutFile.txt"));
        Person p0 = new Person("Mary", 26);
        Person p1 = new Person("Peter", 23);
        Person p2 = new Person("David", 21);
        Person p3 = new Person("Michael", 19);

        Person[] pArray = {p0, p1, p2, p3};
        int count = 0;

        for (Person p : pArray) {
            out.printf("%d::%s::%d\n", count, p.getName(), p.getAge());
            count++;
        }
        out.flush();
        out.close();
        
        // Reading from file
        Scanner sc = new Scanner(
                new BufferedReader(new FileReader("OutFile.txt")));
        
        while (sc.hasNextLine()){
            String currentLine = sc.nextLine();
            String[] props = currentLine.split("::");
            System.out.println(props);
        }

    }
}
