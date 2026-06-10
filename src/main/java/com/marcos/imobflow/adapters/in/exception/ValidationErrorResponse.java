package com.marcos.imobflow.adapters.in.exception;

import java.util.List;

public class ValidationErrorResponse {

    private final int status;
    private final String message;
    private final List<String> errors;

    public ValidationErrorResponse(int status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }
}