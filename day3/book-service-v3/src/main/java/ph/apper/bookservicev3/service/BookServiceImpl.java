package ph.apper.bookservicev3.service;


import org.springframework.stereotype.Service;
import ph.apper.bookservicev3.model.Book;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {

    private final Map<String, Book> books = new HashMap<>();

    /**
     * [REVIEW POINT] I hope at this point, you can appreciate the advantage of using the {@link IdGenerator} interface here instead of
     * its existing implementor {@link UUIDFormatIdGenerator} or {@link TimestampIdGenerator}
     */
    private final IdGenerator idGenerator;

    public BookServiceImpl(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public Book registerBook(String isbn, String title, String author, int publishedYear) throws BookTooYoungException, BookAlreadyRegisteredException {

        if(books.containsKey(isbn)) {
            throw new BookAlreadyRegisteredException("Book already registered");
        }

        int diff = LocalDate.now().getYear() - publishedYear;
        if (diff <= 3) {
            throw new BookTooYoungException("Book too young!");
        }

        Book book = new Book(isbn, title, author, publishedYear);
        book.setId(idGenerator.getNext());

        books.put(isbn, book);

        return book;
    }

    @Override
    public Book getBook(String isbn) throws BookNotFoundException {
        if (books.containsKey(isbn)) {
            return books.get(isbn);
        }

        throw new BookNotFoundException("Book not found!");
    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    @Override
    public void updateBook(String isbn, Book book) throws BookNotFoundException {
        if (!books.containsKey(isbn)) {
            throw new BookNotFoundException("Book to update not found!");
        }

        book.setLastUpdated(LocalDateTime.now());
        books.put(isbn, book);
    }

    @Override
    public void removeBook(String isbn) {
        books.remove(isbn);
    }

    @Override
    public long countPublishedBooks(int publishedYear) {
        return books.values().stream()
                .filter(book -> book.getPublishedYear() == publishedYear)
                .count();
    }
}
