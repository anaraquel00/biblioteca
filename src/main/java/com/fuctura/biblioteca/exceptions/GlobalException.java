package com.fuctura.biblioteca.exceptions;

import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import static org.springframework.validation.BindingResultUtils.getBindingResult;


@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e,
                                                                 HttpServletRequest resquest) {
        StandardError se = new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), e.getMessage(), resquest.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(se);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException e,
                                                                         HttpServletRequest resquest) {
        StandardError se = new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), e.getMessage(), resquest.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(se);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValidException(MethodArgumentNotValidException e,
                                                                 HttpServletRequest resquest) {
        ValidationError ve = new ValidationError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Erro de validação", resquest.getRequestURI());

        for (FieldError obj : e.getBindingResult().getFieldErrors()) {
            ve.addError(obj.getField(), obj.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ve);
    }

}