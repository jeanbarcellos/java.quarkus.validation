package com.jeanbarcellos.core.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class ValidationException extends RuntimeException {

    private Collection<String> errors = new ArrayList<>();

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Collection<String> errors) {
        super(message);
        this.errors = errors;
    }

    public ValidationException(String message, String... errors) {
        super(message);
        this.errors.addAll(Arrays.asList(errors));
    }

    public Collection<String> getErrors() {
        return this.errors;
    }

    public boolean hasErrors() {
        return !this.errors.isEmpty();
    }
}
