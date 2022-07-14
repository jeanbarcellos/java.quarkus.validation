package com.jeanbarcellos.demo.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.jeanbarcellos.core.exception.ValidationException;
import com.jeanbarcellos.demo.dtos.PostRequest;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@Tag("unit")
class PostServiceTest {

    @InjectMocks
    private PostService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void insert_entryInvalidRequest_shouldThrowsException() {

        // Arrange
        var request = new PostRequest();

        // Act && Assert
        var exception = assertThrows(ValidationException.class, () -> {
            this.service.insert(request);
        });

        assertEquals(4, exception.getErrors().size());
        assertThat(exception.getErrors(),
                CoreMatchers.hasItem(PostRequest.MSG_ERROR_TITLE_NOT_NULL_OR_EMPTY));
        assertThat(exception.getErrors(),
                CoreMatchers.hasItem(PostRequest.MSG_ERROR_TITLE_CATEGORY_ID_INVALID));
        assertThat(exception.getErrors(),
                CoreMatchers.hasItem(PostRequest.MSG_ERROR_TEXT_NOT_NULL_OR_EMPTY));
        assertThat(exception.getErrors(),
                CoreMatchers.hasItem(PostRequest.MSG_ERROR_AUTHOR_NOT_NULL));
    }

}
