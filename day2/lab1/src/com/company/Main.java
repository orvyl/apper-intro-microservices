package com.company;

public class Main {


    public static void main(String[] args) {
        BookService bookService = new BookService();

        bookService.registerBook("ISBN-112", "Ain't Colorblind Coz I See Yellow", "O.Tumaneng", 2020);
        bookService.registerBook("ISBN-223", "Ego is the Enemy", "Ryan Holiday", 2016);
        bookService.registerBook("ISBN-334", "Head First Java", "Bert Bates", 2003);
        bookService.registerBook("ISBN-445", "How to take charge of shyness", "O.Tumaneng", 2003);

        System.out.println();

        System.out.println("Here are all the registered books so far...");
        for (Book book : bookService.getAllBooks()) {
            System.out.println("[" + book.getIsbn() + "] " + book.getTitle() + " by " + book.getAuthor() + ". Published year: " + book.getPublishedYear());
        }

        System.out.println();

        try {
            System.out.println("My favorite book is " + bookService.getBook("ISBN-112").getTitle());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();

        try {
            Book book = bookService.getBook("ISBN-445");
            String oldAuthor = book.getAuthor();
            book.setAuthor("OFT");
            bookService.updateBook("ISBN-445", book);

            System.out.println("Book " + book.getIsbn() + " updated! From " + oldAuthor + " to " + book.getAuthor());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();

        bookService.registerBook("ISBN-556", "Good to Great", "Jim Collins", 2001);
        System.out.println("Current book count: " + bookService.getAllBooks().size());
        bookService.removeBook("ISBN-556");
        System.out.println("ISBN-556 deleted! Current book count: " + bookService.getAllBooks().size());

        System.out.println();

        System.out.println("There are " + bookService.countPublishedBooks(2003) + " book/s published in year 2003");
    }
}
