package ph.apper.bookservicev3.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Book {
    private String id;

    private String isbn;
    private String title;
    private String author;
    private int publishedYear;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime lastUpdated = LocalDateTime.now();

    public Book(String isbn, String title, String author, int publishedYear) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
    }
}
