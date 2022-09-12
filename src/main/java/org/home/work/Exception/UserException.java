package org.home.work.Exception;

public class UserException extends RuntimeException{

    private String message;

    public UserException() {
    }

    public UserException(String message) {
        super(message);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
