package com.example.bookservicev2.service;

import com.example.bookservicev2.model.Book;

import java.util.List;

/**
 * [REVIEW POINT] What's the point of creating an interface {@link BookService}?
 *
 */

public interface BookService {
    Book registerBook(String isbn, String title, String author, int publishedYear) throws BookTooYoungException, InvalidFieldFormatException;
    Book getBook(String isbn) throws BookNotFoundException;
    List<Book> getAllBooks();
    void updateBook(String isbn, Book book) throws BookNotFoundException;
    void removeBook(String isbn);
    long countPublishedBooks(int publishedYear);
}
