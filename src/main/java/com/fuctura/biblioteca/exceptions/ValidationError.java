package com.fuctura.biblioteca.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private List<FieldMessager> errors = new ArrayList<>();

    public ValidationError(LocalDateTime timestamp, Integer status, String message, String path) {
        super(timestamp, status, message, path);
    }

    public List<FieldMessager> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        this.errors.add(new FieldMessager(fieldName, message));
    }

}
