package con.tbs.payload;

import lombok.Data;

import java.util.List;

@Data
public class GetAllContentsResponse {
    private Long currentContentCount;
    private List<ContentResponse> contents;

    public GetAllContentsResponse(Long currentContentCount, List<ContentResponse> contents) {
        this.currentContentCount = currentContentCount;
        this.contents = contents;
    }

    public GetAllContentsResponse() {
    }
}
