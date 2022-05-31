package con.tbs.payload;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class CreateReviewRequest {

    @NotBlank
    private String userId;

    @NotBlank
    private String contentId;

    @NotBlank
    private String comment;

    @Positive
    @Max(5)
    private int stars;
}
