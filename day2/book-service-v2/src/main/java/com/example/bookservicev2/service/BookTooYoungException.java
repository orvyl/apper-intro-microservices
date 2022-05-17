package com.example.bookservicev2.service;

public class BookTooYoungException extends Exception {
    public BookTooYoungException() {
        super();
    }

    public BookTooYoungException(String message) {
        super(message);
    }

    public BookTooYoungException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookTooYoungException(Throwable cause) {
        super(cause);
    }

    protected BookTooYoungException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
