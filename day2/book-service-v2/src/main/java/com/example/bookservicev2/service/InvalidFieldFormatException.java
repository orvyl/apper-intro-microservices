package com.example.bookservicev2.service;

public class InvalidFieldFormatException extends Exception {
    public InvalidFieldFormatException() {
        super();
    }

    public InvalidFieldFormatException(String message) {
        super(message);
    }

    public InvalidFieldFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidFieldFormatException(Throwable cause) {
        super(cause);
    }

    protected InvalidFieldFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
