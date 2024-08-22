package com.typefigth.api_gateway.infrastructure.exceptions;


public class UnAuthenticationException extends RuntimeException {
    public UnAuthenticationException(String message) {
        super(message);
    }


}