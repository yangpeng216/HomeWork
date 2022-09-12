package org.home.work.Exception;

public class CheckRoleException extends RuntimeException{

    private String message;

    public CheckRoleException() {
    }

    public CheckRoleException(String message) {
        super(message);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
