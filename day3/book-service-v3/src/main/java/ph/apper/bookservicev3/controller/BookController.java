package ph.apper.bookservicev3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ph.apper.bookservicev3.model.Book;
import ph.apper.bookservicev3.payload.CreateBookRequest;
import ph.apper.bookservicev3.payload.CreateBookResponse;
import ph.apper.bookservicev3.payload.ErrorResponse;
import ph.apper.bookservicev3.payload.GetBookResponse;
import ph.apper.bookservicev3.payload.UpdateBookRequest;
import ph.apper.bookservicev3.service.BookAlreadyRegisteredException;
import ph.apper.bookservicev3.service.BookNotFoundException;
import ph.apper.bookservicev3.service.BookService;
import ph.apper.bookservicev3.service.BookTooYoungException;

import javax.validation.Valid;
import java.util.Map;

/**
 * [REVIEW POINT]
 *
 * Aside from the @{@link RestController}, you can add @{@link RequestMapping} to set a base url path.
 * Here, because of `@RequestMapping("book")`, all resource path inside will be prepended with "book"
 * E.g
 * registerBook(..)   --> POST /book
 * getBook(..)        --> GET /book/{isbn}
 *
 */

@RestController
@RequestMapping("book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    /**
     * [REVIEW POINT]
     *
     * @{@link ResponseStatus} sets your HTTP response if this method will NOT throw any error or exception. Here, it will
     * set the response status to HTTP-201 (Created) if it will not encounter any error or will not throw either a {@link BookTooYoungException}
     * or {@link BookAlreadyRegisteredException}
     *
     * ctrl+click {@link HttpStatus} to see all HTTP response code available. Review HTTP Response code. https://developer.mozilla.org/en-US/docs/Web/HTTP/Status
     *
     * [REVIEW POINT]
     * @{@link Valid} as an annotation of the parameter `CreateBookRequest request` activates the validation annotation set in the fields of @{@link CreateBookRequest}. This causes to throw {@link MethodArgumentNotValidException}
     * handled in handleValidationExceptions(..) at the bottom of this class.
     *
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBookResponse registerBook(
            @Valid @RequestBody CreateBookRequest request) throws BookTooYoungException, BookAlreadyRegisteredException {

        Book registeredBook = bookService.registerBook(
                request.getIsbn(),
                request.getTitle(),
                request.getAuthor(),
                request.getPublishedYear());

        return new CreateBookResponse(registeredBook.getId(), registeredBook.getIsbn(), registeredBook.getCreatedAt());
    }

    /**
     *
     * [REVIEW POINT]
     * @{@link RequestParam} is another way of sending data via the resource path or url. E.g /cat?breed=persian
     * It is usually used for optional data. In this case, we have a request parameter `full` that when set to `true`,
     * it should return the complete details of the book.
     *
     * E.g GET /book/ISBN1234678565
     * the response structure is like this:
     * {
     *     "id": "1653399199879",
     *     "isbn": "ISBN1234567756757",
     *     "title": "The quick brown fox",
     *     "publishedYear": 0
     * }
     *
     * if GET /book/ISBN1234678565?full=true
     * it's like this:
     * {
     *     "id": "1653399199879",
     *     "isbn": "ISBN1234567756757",
     *     "title": "The quick brown fox",
     *     "author": "L.Dog",
     *     "publishedYear": 2010,
     *     "createdAt": "2022-05-24T13:33:19.879945",
     *     "lastUpdated": "2022-05-24T13:33:19.879945"
     * }
     *
     */
    @GetMapping("{isbn}")
    public GetBookResponse getBook(
            @PathVariable String isbn,
            @RequestParam(name = "full", defaultValue = "false") boolean isFullDetails) throws BookNotFoundException {

        Book book = bookService.getBook(isbn);

        GetBookResponse response = new GetBookResponse();
        response.setId(book.getId());
        response.setIsbn(book.getIsbn());
        response.setTitle(book.getTitle());

        if (isFullDetails) {
            response.setAuthor(book.getAuthor());
            response.setPublishedYear(book.getPublishedYear());
            response.setCreatedAt(book.getCreatedAt());
            response.setLastUpdated(book.getLastUpdated());
        }

        return response;
    }

    @PutMapping("{isbn}")
    public void updateBook(@PathVariable String isbn, @Valid @RequestBody UpdateBookRequest request) throws BookNotFoundException {
        Book book = bookService.getBook(isbn);
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPublishedYear(request.getPublishedYear());

        bookService.updateBook(isbn, book);
    }

    @GetMapping("/count/{year}")
    public Map<String, Long> updateBook(@PathVariable int year)  {
        return Map.of("count", bookService.countPublishedBooks(year));
    }

    @DeleteMapping("{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable String isbn) {
        bookService.removeBook(isbn);
    }

    /**
     *
     * [REVIEW POINT]
     *
     * @{@link ExceptionHandler} makes the method to catch exception thrown by the http processor ( E.g registerBook(..) ) and
     * transform it into a response body. Here, caught exceptions will be converted into {@link ErrorResponse}.
     * REF:
     * https://www.baeldung.com/spring-boot-bean-validation
     * https://www.toptal.com/java/spring-boot-rest-api-error-handling
     *
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        return new ErrorResponse(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BookTooYoungException.class, BookAlreadyRegisteredException.class})
    public ErrorResponse handleBookTooYoungException(Exception ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BookNotFoundException.class)
    public ErrorResponse handleBookNotFoundException(BookNotFoundException ex) {
        return new ErrorResponse(ex.getMessage());
    }
}
