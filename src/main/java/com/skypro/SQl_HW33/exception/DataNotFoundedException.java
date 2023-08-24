package com.skypro.SQl_HW33.exception;

public class DataNotFoundedException extends RuntimeException {
    public DataNotFoundedException() {
    }

    public DataNotFoundedException(String message) {
        super(message);
    }

    public DataNotFoundedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataNotFoundedException(Throwable cause) {
        super(cause);
    }

    public DataNotFoundedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
