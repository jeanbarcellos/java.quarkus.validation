package com.jeanbarcellos.demo.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.jeanbarcellos.core.exception.ValidationException;
import com.jeanbarcellos.demo.dtos.CategoryRequest;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@Tag("unit")
class CategoryServiceTest {

    @InjectMocks
    private CategoryService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void insert_entryInvalidRequest_shouldThrowsException() {

        // Arrange
        var request = new CategoryRequest();

        // Act && Assert
        var exception = assertThrows(ValidationException.class, () -> {
            this.service.insert(request);
        });

        assertEquals(2, exception.getErrors().size());
        assertThat(exception.getErrors(),
                CoreMatchers.hasItem(CategoryRequest.MSG_ERROR_NAME_NOT_NULL_OR_EMPTY));
        assertThat(exception.getErrors(),
                CoreMatchers.hasItem(CategoryRequest.MSG_ERROR_DESCRIPTION_NOT_NULL_OR_EMPTY));
    }

    @Test
    void insert_entryValidRequest_shouldReturnSucess() {

        // Arrange
        var name = "Esporte";
        var description = "Descrição do esporte";

        var request = new CategoryRequest();
        request.setName(name);
        request.setDescription(description);

        // Act
        var result = this.service.insert(request);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(name, result.getName());
        assertEquals(description, result.getDescription());
    }
}
