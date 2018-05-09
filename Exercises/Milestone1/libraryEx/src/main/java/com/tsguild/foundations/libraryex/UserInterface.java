/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.libraryex;

import java.util.Scanner;

/**
 *
 * @author pspethmann
 */
public class UserInterface {

    Library localLibrary = new Library();
    Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        UserInterface app = new UserInterface();
        app.run();
    }

    public void run() {

        int userChoice;
        boolean running = true;
        
        localLibrary.generateStockedBooks();

        while (running) {

            displayMenu();
            userChoice = takeOption();

            switch (userChoice) {
                case 0:
                    running = false;
                    return;
                case 1:
                    addBook();
                    break;
                case 2:
                    listBooks();
                    break;
                case 3:
                    checkOutBook();
                    break;
                case 4:
                    returnBook();
                    break;
                default:
                    System.out.println("I don't understand that number.");
                    break;
            }
        }
    }

    public void displayMenu() {
        System.out.println("\nWelcome to the library!");
        System.out.println("=======================");
        System.out.println("1. Add a book");
        System.out.println("2. See list of books");
        System.out.println("3. Check out a book");
        System.out.println("4. Return a book");
        System.out.println("(Input 0 to quit.)\n");
    }

    public int takeOption() {

        String userInput;
        int userChoice = 0;

        boolean valid = false;
        do {
            try {
                System.out.print("What would you like to do?: ");
                userInput = input.nextLine();
                userChoice = Integer.parseInt(userInput);
                valid = true;
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number.");
            }
        } while (!valid);

        return userChoice;
    }

    public void addBook() {

        Book newBook = new Book();

        System.out.print("What is the title?: ");
        newBook.setTitle(input.nextLine());
        System.out.print("What is the author's name?: ");
        newBook.setAuthor(input.nextLine());

        localLibrary.addBook(newBook);

    }

    public void listBooks() {

        Book[] bookList = localLibrary.getBookList();
        int bookCount = localLibrary.getBookCount();

        System.out.println("\n Here is the current book list: ");
        System.out.println("---------------------------------");

        for (int i = 0; i < bookCount; i++) {
            Book thisBook = bookList[i];
            System.out.println(i + ".)");
            System.out.println("Title: " + thisBook.getTitle());
            System.out.println("Author: " + thisBook.getAuthor());
            System.out.print("Status: ");
            if (thisBook.isAvailable()){
                System.out.println("Available");
            } else{
                System.out.println("Checked out");
            }
            
            System.out.println("---------------------------------");
        }
    }

    public void checkOutBook() {

        String userInput;
        int bookNumber = 0;
        int bookCount = localLibrary.getBookCount();

        boolean valid = false;

        do {
            try {
                System.out.print("Input the number of the book you wish to check out: ");
                userInput = input.nextLine();
                bookNumber = Integer.parseInt(userInput);
                valid = true;

            } catch (NumberFormatException ex) {
                System.out.println("Invalid input.");
            }

        } while (!valid);
        
        if (bookNumber < bookCount){
            localLibrary.checkOutBook(bookNumber);
        } else{
            System.out.println("That is not a valid book number.");
        }
        
    }
    
    public void returnBook(){
        String userInput;
        int bookNumber = 0;
        int bookCount = localLibrary.getBookCount();

        boolean valid = false;

        do {
            try {
                System.out.print("Input the number of the book you wish to return: ");
                userInput = input.nextLine();
                bookNumber = Integer.parseInt(userInput);
                valid = true;

            } catch (NumberFormatException ex) {
                System.out.println("Invalid input.");
            }

        } while (!valid);
        
        if (bookNumber < bookCount){
            localLibrary.returnBook(bookNumber);
        } else{
            System.out.println("That is not a valid book number.");
        }
    }

}
