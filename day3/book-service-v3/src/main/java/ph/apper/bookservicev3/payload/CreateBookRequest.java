package ph.apper.bookservicev3.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


/**
 * [REVIEW POINT]
 * Why create {@link CreateBookRequest} and be used in your ReST controller/operations? Isn't the same with th {@link ph.apper.bookservicev3.model.Book} model?
 * ANSWER: Segregation of concerns
 *
 * {@link CreateBookRequest} is for holding/representing the data to create a book, and {@link ph.apper.bookservicev3.model.Book} is the actual book.
 * What if soon creating a book requires the reviews of the early readers of it? The list of reviews can be added as a new field in {@link CreateBookRequest} but
 * {@link ph.apper.bookservicev3.model.Book} will not have that new field. It doesn't define the book. Maybe, reviews will be a separate model.
 *
 *
 * [REVIEW POINT]
 * Annotation validation. Instead of manually checking the value of the fields (E.g title.isBlank() in your code), you can just simply
 * add annotations on the fields and let spring do the validation. You can also set your own message when the validation wasn't met.
 *
 * REF:
 * https://www.baeldung.com/spring-boot-bean-validation
 * https://www.baeldung.com/javax-validation
 * https://stackoverflow.com/questions/68231771/how-can-i-validate-the-string-length-using-java-spring-validation
 *
 */

@Data
public class CreateBookRequest {
    @NotNull
    @Size(min = 10, message = "isbn must be at least 10 characters")
    private String isbn;

    @NotBlank
    private String title;

    @NotBlank(message = "Who is the author???")
    private String author;

    @NotNull
    @Positive(message = "Provide a valid year")
    private Integer publishedYear;
}
