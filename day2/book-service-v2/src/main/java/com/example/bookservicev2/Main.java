package com.example.bookservicev2;

import com.example.bookservicev2.model.Book;
import com.example.bookservicev2.service.BookNotFoundException;
import com.example.bookservicev2.service.BookService;
import com.example.bookservicev2.service.BookTooYoungException;
import com.example.bookservicev2.service.IdGenerator;
import com.example.bookservicev2.service.InvalidFieldFormatException;
import com.example.bookservicev2.service.UUIDFormatIdGenerator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * A singleton instance of this class will be registered in Spring context. Spring boot will detect this bean as
 * a {@link CommandLineRunner}; hence it will be executed upon boot up.
 *
 * [REVIEW POINT] Difference between @{@link Component} and @{@link org.springframework.stereotype.Service}
 *
 */
@Component
public class Main implements CommandLineRunner {

    private final BookService bookService;
    private final IdGenerator idGenerator;

    public Main(BookService bookService, IdGenerator idGenerator) {
        this.bookService = bookService;
        this.idGenerator = idGenerator;
    }

    @Override
    public void run(String... args) throws Exception {
        try {

            System.out.println();

            System.out.println("Current id generator is: " + (idGenerator instanceof UUIDFormatIdGenerator ? "uuid":"timestamp"));

            System.out.println();

            bookService.registerBook("ISBN-112", "Ain't Colorblind Coz I See Yellow", "O.Tumaneng", 10);
            bookService.registerBook("ISBN-223", "Ego is the Enemy", "Ryan Holiday", 2016);
            bookService.registerBook("ISBN-334", "Head First Java", "Bert Bates", 2003);
            bookService.registerBook("ISBN-445", "How to take charge of shyness", "O.Tumaneng", 2003);

            System.out.println("Here are all the registered books so far...");
            for (Book book : bookService.getAllBooks()) {
                System.out.println("[" + book.getId() + "]" + "[" + book.getIsbn() + "] " + book.getTitle() + " by " + book.getAuthor() + ". Published year: " + book.getPublishedYear());
            }

            System.out.println();

            System.out.println("My favorite book is " + bookService.getBook("ISBN-112").getTitle());

            System.out.println();

            Book book = bookService.getBook("ISBN-445");
            String oldAuthor = book.getAuthor();
            book.setAuthor("OFT");
            bookService.updateBook("ISBN-445", book);

            System.out.println("Book " + book.getIsbn() + " updated! From " + oldAuthor + " to " + book.getAuthor());

            System.out.println();

            System.out.println();

            bookService.registerBook("ISBN-556", "Good to Great", "Jim Collins", 2001);
            System.out.println("Current book count: " + bookService.getAllBooks().size());
            bookService.removeBook("ISBN-556");
            System.out.println("ISBN-556 deleted! Current book count: " + bookService.getAllBooks().size());

            System.out.println();

            System.out.println("There are " + bookService.countPublishedBooks(2003) + " book/s published in year 2003");
        } catch (BookTooYoungException | InvalidFieldFormatException | BookNotFoundException e) {
            e.printStackTrace();
        }
    }
}
