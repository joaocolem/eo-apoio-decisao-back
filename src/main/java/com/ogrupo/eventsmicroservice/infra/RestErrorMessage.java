package com.ogrupo.eventsmicroservice.infra;

import org.springframework.http.HttpStatus;

public class RestErrorMessage {
    private HttpStatus status;
    private String message;

    // Construtor com parâmetros
    public RestErrorMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    // Construtor sem parâmetros
    public RestErrorMessage() {
    }

    // Getters e Setters
    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}