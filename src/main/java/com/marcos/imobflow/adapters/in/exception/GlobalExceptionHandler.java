package com.marcos.imobflow.adapters.in.exception;

import com.marcos.imobflow.application.exception.ImovelNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        ValidationErrorResponse response = new ValidationErrorResponse(
                400,
                "Dados inválidos",
                errors
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ImovelNotFoundException.class)
    public ResponseEntity<SimpleErrorResponse> handleImovelNotFound(ImovelNotFoundException ex) {
        SimpleErrorResponse response = new SimpleErrorResponse(
                404,
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}