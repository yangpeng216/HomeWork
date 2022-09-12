package org.home.work.Exception;

public class RoleException extends RuntimeException{

    private String message;

    public RoleException() {
    }

    public RoleException(String message) {
        super(message);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
