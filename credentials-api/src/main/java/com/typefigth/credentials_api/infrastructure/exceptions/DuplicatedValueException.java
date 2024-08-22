package com.typefigth.credentials_api.infrastructure.exceptions;

public class DuplicatedValueException extends RuntimeException {

    public DuplicatedValueException(String message) {
        super(message);
    }

    public DuplicatedValueException() {
        super();
    }
}
