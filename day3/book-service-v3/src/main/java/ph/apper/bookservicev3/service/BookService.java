package ph.apper.bookservicev3.service;



import ph.apper.bookservicev3.model.Book;

import java.util.List;

/**
 * [REVIEW POINT] What's the point of creating an interface {@link BookService}?
 *
 */

public interface BookService {
    Book registerBook(String isbn, String title, String author, int publishedYear) throws BookTooYoungException, BookAlreadyRegisteredException;
    Book getBook(String isbn) throws BookNotFoundException;
    List<Book> getAllBooks();
    void updateBook(String isbn, Book book) throws BookNotFoundException;
    void removeBook(String isbn);
    long countPublishedBooks(int publishedYear);
}
