package com.bsuir.diploma.recycleappbackend.exception;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message) {
        super(message);
    }
}