package ph.apper.bookservicev3.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class UpdateBookRequest {
    @NotBlank
    private String title;

    @NotBlank(message = "Who is the author???")
    private String author;

    @NotNull
    @Positive(message = "Provide a valid year")
    private Integer publishedYear;
}
