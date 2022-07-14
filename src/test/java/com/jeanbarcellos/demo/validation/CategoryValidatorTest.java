package com.jeanbarcellos.demo.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import javax.validation.ConstraintValidatorContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.jeanbarcellos.demo.services.CategoryService;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@Tag("unit")
class CategoryValidatorTest {

    @InjectMocks
    CategoryValidator validator;

    @Mock
    CategoryService service;

    @Mock
    ConstraintValidatorContext context;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void isValid_entryIdExistsAndContextNull_shouldReturnTrue() {

        // Setup
        Mockito.when(this.service.exists(Mockito.any(UUID.class))).thenReturn(true);

        // Arrange
        var value = UUID.randomUUID();

        // Act
        var actual = validator.isValid(value, null);

        // Assert
        assertTrue(actual);
    }

    @Test
    void isValid_entryIdNotExistsAndContextNull_shouldReturnFalse() {

        // Setup
        Mockito.when(this.service.exists(Mockito.any(UUID.class))).thenReturn(false);

        // Arrange
        var value = UUID.randomUUID();

        // Act
        var actual = validator.isValid(value, null);

        // Assert
        assertFalse(actual);
    }

}
