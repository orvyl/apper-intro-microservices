package con.tbs.payload;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class ContentResponse {
    private String contentId;

    private String authorName;
    private String title;
    private String body;

    private Set<ReviewResponse> reviews = new HashSet<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ContentResponse(String contentId, String authorName, String title, String body) {
        this.contentId = contentId;
        this.authorName = authorName;
        this.title = title;
        this.body = body;
    }
}
