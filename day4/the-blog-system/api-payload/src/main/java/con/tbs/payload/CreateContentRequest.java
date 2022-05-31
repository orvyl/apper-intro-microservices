package con.tbs.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateContentRequest {
    @NotBlank
    private String userId;

    @NotBlank
    private String title;

    @NotBlank
    private String body;
}
