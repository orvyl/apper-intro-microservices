package ph.apper.bookservicev3.service;

public class BookAlreadyRegisteredException extends Exception {
    public BookAlreadyRegisteredException() {
        super();
    }

    public BookAlreadyRegisteredException(String message) {
        super(message);
    }

    public BookAlreadyRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookAlreadyRegisteredException(Throwable cause) {
        super(cause);
    }

    protected BookAlreadyRegisteredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
