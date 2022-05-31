package con.tbs.payload;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewResponse {
    private String reviewId;

    private String comment;
    private int stars;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ReviewResponse(String reviewId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.reviewId = reviewId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ReviewResponse() {
    }
}
