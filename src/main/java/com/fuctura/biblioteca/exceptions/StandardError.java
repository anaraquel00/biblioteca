package com.fuctura.biblioteca.exceptions;

import java.time.LocalDateTime;

public class StandardError {
    private LocalDateTime timestamp;
    private Integer status;
    private String message;
    private String path;

    public StandardError(LocalDateTime timestamp, Integer status, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }


    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
