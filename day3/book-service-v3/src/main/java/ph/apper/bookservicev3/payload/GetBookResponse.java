package ph.apper.bookservicev3.payload;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetBookResponse {
    private String id;

    private String isbn;
    private String title;
    private String author;
    private int publishedYear;

    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;
}
