package com.bsuir.diploma.recycleappbackend.exception;

public class InvalidAuthenticationTokenException extends RuntimeException {

    public InvalidAuthenticationTokenException(String message) {
        super(message);
    }
}
