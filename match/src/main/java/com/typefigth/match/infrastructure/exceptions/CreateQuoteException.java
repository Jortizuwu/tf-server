package com.typefigth.match.infrastructure.exceptions;


public class CreateQuoteException extends RuntimeException {

    public CreateQuoteException(String message) {
        super(message);
    }

    public CreateQuoteException() {
        super();
    }
}

