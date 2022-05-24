package ph.apper.bookservicev3.payload;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateBookResponse {
    private String id;
    private String isbn;
    private LocalDateTime createdAt;

    public CreateBookResponse(String id, String isbn, LocalDateTime createdAt) {
        this.id = id;
        this.isbn = isbn;
        this.createdAt = createdAt;
    }
}
