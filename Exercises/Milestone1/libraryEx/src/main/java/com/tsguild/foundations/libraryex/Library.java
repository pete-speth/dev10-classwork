/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.libraryex;

/**
 *
 * @author pspethmann
 */
public class Library {

    private Book[] bookList = new Book[10];
    private int bookCount = 0;

    public int getBookCount() {
        return bookCount;
    }

    public Book[] getBookList() {
        return bookList;
    }

    public void addBook(Book newBook) {

        if (bookCount < bookList.length) {

            bookList[bookCount] = newBook;
            System.out.println("Book added!");

           

        } else {

            Book[] tempBookList = bookList;
            bookList = new Book[bookCount + 5];

            for (int i = 0; i < bookCount; i++) {
                bookList[i] = tempBookList[i];
            }
            
            bookList[bookCount] = newBook;
        }
        
         bookCount++;
    }

    public void checkOutBook(int bookIndex) {
        
        boolean available = bookList[bookIndex].isAvailable();
        
        if(available){
            bookList[bookIndex].setAvailable(false);
            System.out.println("Book checked out!");
        } else {
            System.out.println("Sorry, that book is already checked out!");
        }
        
        
    }

    public void returnBook(int bookIndex) {
    
        boolean available = bookList[bookIndex].isAvailable();
        
        if(!available){
            bookList[bookIndex].setAvailable(true);
            System.out.println("Book returned. Thank you!");
        } else {
            System.out.println("You already returned this book!");
        }
    }
    
    public void generateStockedBooks(){
        
        Book book0 = new Book();
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        Book book4 = new Book();
        Book book5 = new Book();
        
        book0.setTitle("The Alchemist");
        book0.setAuthor("Paolo Coelho");
        
        book1.setTitle("Harry Potter");
        book1.setAuthor("JK Rowling");
        
        book2.setTitle("The Lord of the Rings");
        book2.setAuthor("JRR Tolkien");
        
        book3.setTitle("The Great Gatsby");
        book3.setAuthor("F. Scott Fitzgerald");
        
        book4.setTitle("Game of Thrones");
        book4.setAuthor("George RR Martin");
        
        book5.setTitle("Eragon");
        book5.setAuthor("Christopher Paolini");
        
        bookList[0] = book0;
        bookList[1] = book1;
        bookList[2] = book2;
        bookList[3] = book3;
        bookList[4] = book4;
        bookList[5] = book5;
        
        bookCount = 6;
    }

}
