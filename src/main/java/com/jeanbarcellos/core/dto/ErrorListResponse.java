package com.jeanbarcellos.core.dto;

import java.util.ArrayList;
import java.util.Collection;

import lombok.Getter;

@Getter
public class ErrorListResponse extends ErrorResponse {

    private Collection<String> errors = new ArrayList<>();

    public ErrorListResponse(String message, Collection<String> errors) {
        super(message);
        this.errors = errors;
    }

    public ErrorListResponse(Integer status, String message, Collection<String> errors) {
        super(status, message);
        this.errors = errors;
    }

    public boolean hasErrors() {
        return !this.errors.isEmpty();
    }
}
