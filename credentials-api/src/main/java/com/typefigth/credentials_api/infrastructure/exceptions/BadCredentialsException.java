package com.typefigth.credentials_api.infrastructure.exceptions;

public class BadCredentialsException extends RuntimeException {

    public BadCredentialsException(String message) {
        super(message);
    }

    public BadCredentialsException() {
        super();
    }
}
