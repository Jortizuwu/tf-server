package com.typefigth.credentials_api.infrastructure.exceptions;

import com.typefigth.credentials_api.infrastructure.utils.Constants;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandlerAdvice {


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(BadCredentialsException e) {
        Map<String, String> response = new HashMap<>();
        response.put(Constants.ERROR, e.getMessage());
        response.put(Constants.STATUS, HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(DuplicatedValueException.class)
    public ResponseEntity<Map<String, String>> duplicatedValueException(DuplicatedValueException e) {
        Map<String, String> response = new HashMap<>();
        response.put(Constants.ERROR, e.getMessage());
        response.put(Constants.STATUS, HttpStatus.CONFLICT.toString());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        Map<String, Object> response = new HashMap<>();

        String errorMessage = e.getRootCause() != null ? e.getRootCause().getMessage() : e.getMessage();

        String[] duplicateFields = extractDuplicateFields(errorMessage);

        response.put("error", "Data integrity violation");
        response.put("status", HttpStatus.CONFLICT.toString());
        response.put("duplicateFields", duplicateFields);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleHttpMessageNotReadableException() {
        Map<String, String> response = new HashMap<>();
        response.put(Constants.ERROR, "malformed json");
        response.put(Constants.STATUS, HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((ObjectError error) -> {
            String fieldName = ((org.springframework.validation.FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    private String[] extractDuplicateFields(String errorMessage) {
        if (errorMessage.contains("nickname")) {
            return new String[]{"nickname"};
        } else if (errorMessage.contains("email")) {
            return new String[]{"email"};
        } else {
            return new String[]{};
        }
    }
}
