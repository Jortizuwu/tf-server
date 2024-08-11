package com.typefigth.user.infrastructure.exceptions;

public class DuplicatedValueException extends RuntimeException {

    public DuplicatedValueException(String message) {
        super(message);
    }

    public DuplicatedValueException() {
        super();
    }
}
