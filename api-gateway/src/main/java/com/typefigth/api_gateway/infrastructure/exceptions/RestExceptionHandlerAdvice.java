package com.typefigth.api_gateway.infrastructure.exceptions;

import org.springframework.cloud.gateway.support.ServiceUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandlerAdvice {

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<Map<String, String>> serviceUnavailableException(ServiceUnavailableException e) {
        Map<String, String> response = new HashMap<>();
        response.put("error", e.getMessage());
        response.put("status", HttpStatus.NOT_FOUND.toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
