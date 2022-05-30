package ph.apper;

public class AnimalNotFoundException extends Exception {
    public AnimalNotFoundException() {
    }

    public AnimalNotFoundException(String message) {
        super(message);
    }

    public AnimalNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AnimalNotFoundException(Throwable cause) {
        super(cause);
    }

    public AnimalNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
