package com.jeanbarcellos.core.exception;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.hamcrest.CoreMatchers;

public class Assertions {

    private Assertions() {
    }

    public static void assertValidationExceptionMessage(Exception exception, String message) {
        assertEquals(message, exception.getMessage());
    }

    public static void assertValidationExceptionErrorListSize(ValidationException exception, Integer size) {
        assertEquals(size, exception.getErrors().size());
    }

    public static void assertValidationExceptionErrorListContains(ValidationException exception, String... items) {
        assertThat(exception.getErrors(), CoreMatchers.hasItems(items));
    }

}
