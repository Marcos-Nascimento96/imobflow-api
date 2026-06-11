package com.marcos.imobflow.adapters.in.exception;

public class SimpleErrorResponse {

    private final int status;
    private final String message;

    public SimpleErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}