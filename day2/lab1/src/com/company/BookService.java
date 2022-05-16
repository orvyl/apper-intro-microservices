package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookService {
    private final Map<String, Book> books = new HashMap<>();

    public Book registerBook(String isbn, String title, String author, int publishedYear) {
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublishedYear(publishedYear);

        books.put(isbn, book);

        return book;
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    public Book getBook(String isbn) throws Exception {
        if (books.containsKey(isbn)) {
            return books.get(isbn);
        }

        throw new Exception("Book not found!");
    }

    public void updateBook(String isbn, Book updatedBook) throws Exception {
        if (!books.containsKey(isbn)) {
            throw new Exception("Book to update not found!");
        }

        books.put(isbn, updatedBook);
    }

    public void removeBook(String isbn) {
        books.remove(isbn);
    }

    public long countPublishedBooks(int publishedYear) {
        return books.values().stream()
                .filter(book -> book.getPublishedYear() == publishedYear)
                .count();
    }

}
