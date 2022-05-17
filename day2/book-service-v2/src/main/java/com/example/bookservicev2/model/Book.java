package com.example.bookservicev2.model;

import lombok.Data;

@Data
public class Book {
    private String id;
    private String isbn;
    private String title;
    private String author;
    private int publishedYear;

    public Book(String isbn, String title, String author, int publishedYear) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
    }
}
