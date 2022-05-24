package ph.apper.bookservicev3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    /**
     * Henlo, Cadets!
     *
     * Here's a complete ReST implementation of book-service.
     *
     * NOTES:
     * 1. Don't be overwhelmed. Permit yourself to be curious specially with the [REVIEW POINT]s :). Check all class and pom.xml.
     * 2. Start with the controller and understanding the purpose of the classes in the `payload` package.
     * 3. Check and play around with the validation annotations. E.g {@link ph.apper.bookservicev3.payload.CreateBookRequest}
     * 4. {@link ph.apper.bookservicev3.service.BookServiceImpl} was updated. Remove unnecessary validations and added exceptions.
     * 5. {@link ph.apper.bookservicev3.controller.BookController} has methods(aside from the HTTP Verb representations)
     *      that handle the exceptions to error responses.
     * 6. Go back to note#1
     * 7. Message me for questions and clarifications
     *
     */

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
