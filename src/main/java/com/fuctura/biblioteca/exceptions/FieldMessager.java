package com.fuctura.biblioteca.exceptions;

public class FieldMessager {
    private String fieldName;
    private String message;

    public FieldMessager(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;

    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getField() {
        return fieldName;
    }

    public String getDefaultMessage() {
        return message;
    }
}
