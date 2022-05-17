package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookService {
    private final Map<String, Book> books = new HashMap<>();
    private final IdGenerator idGeneratorImpl;

    public BookService(IdGenerator idGeneratorImpl) {
        this.idGeneratorImpl = idGeneratorImpl;
    }

    public Book registerBook(String isbn, String title, String author, int publishedYear) throws InvalidFieldFormatException, BookTooYoungException {

        if (isbn.isBlank() || title.isBlank() || author.isBlank() || publishedYear == 0) {
            throw new InvalidFieldFormatException("all fields are required!");
        }

        int diff = LocalDate.now().getYear() - publishedYear;
        if (diff <= 3) {
            throw new BookTooYoungException("Book too young!");
        }

        Book book = new Book();
        book.setId(idGeneratorImpl.getNext());
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
