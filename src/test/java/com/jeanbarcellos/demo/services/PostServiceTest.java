package com.jeanbarcellos.demo.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;
import java.util.UUID;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.github.javafaker.Faker;
import com.jeanbarcellos.core.exception.ValidationException;
import com.jeanbarcellos.core.utils.DocUtils;
import com.jeanbarcellos.demo.dtos.PeopleRequest;
import com.jeanbarcellos.demo.dtos.PostRequest;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

@QuarkusTest
@Tag("unit")
class PostServiceTest {

    @InjectMocks
    private PostService service;

    // Usar o injectmock do quarkus para mockar a instancia do container
    @InjectMock
    private CategoryService categoryService;

    Faker faker = new Faker(new Locale("pt-BR"));

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
                CoreMatchers.hasItem(PostRequest.MSG_ERROR_CATEGORY_ID_INVALID));
        assertThat(exception.getErrors(),
                CoreMatchers.hasItem(PostRequest.MSG_ERROR_TEXT_NOT_NULL_OR_EMPTY));
        assertThat(exception.getErrors(),
                CoreMatchers.hasItem(PostRequest.MSG_ERROR_AUTHOR_NOT_NULL));
    }

    @Test
    void insert_entryInvalidRequestWithCategoryIdInvalid_shouldThrowsExceptionTwoErrors() {

        // Arrange
        var request = new PostRequest();
        request.setTitle("text asdsadas");
        request.setText("text asdsadas");
        request.setCategoryId(UUID.randomUUID());

        // Act && Assert
        var exception = assertThrows(ValidationException.class, () -> {
            this.service.insert(request);
        });

        assertEquals(2, exception.getErrors().size());
        assertThat(exception.getErrors(),
                CoreMatchers.hasItem(PostRequest.MSG_ERROR_CATEGORY_ID_INVALID));
        assertThat(exception.getErrors(),
                CoreMatchers.hasItem(PostRequest.MSG_ERROR_AUTHOR_NOT_NULL));
    }

    @Test
    void insert_entryRequestWithoutAuthor_shouldThrowsExceptionWithOneError() {

        // Arrange
        UUID categoryId = UUID.randomUUID();

        var request = new PostRequest();
        request.setTitle("text asdsadas");
        request.setText("text asdsadas");
        request.setCategoryId(categoryId);

        setupCategoryService_exists_returnTrue(categoryId);

        // Act && Assert
        var exception = assertThrows(ValidationException.class, () -> {
            this.service.insert(request);
        });

        assertEquals(1, exception.getErrors().size());
        assertThat(exception.getErrors(),
                CoreMatchers.hasItem(PostRequest.MSG_ERROR_AUTHOR_NOT_NULL));
    }

    @Test
    void insert_entryValidRequest_shouldThrowsExceptionWithOneError() {

        // Arrange
        UUID categoryId = UUID.randomUUID();

        var author = new PeopleRequest();
        author.setName(faker.name().fullName());
        author.setEmail(faker.internet().emailAddress());
        author.setIdentificationNumber(DocUtils.generateCPF());

        var request = new PostRequest();
        request.setTitle("text asdsadas");
        request.setText("text asdsadas");
        request.setCategoryId(categoryId);
        request.setAuthor(author);

        setupCategoryService_exists_returnTrue(categoryId);

        // Act
        var retorno = this.service.insert(request);

        // Assert
        assertTrue(retorno);
    }

    private void setupCategoryService_exists_returnTrue(UUID id) {
        Mockito.when(this.categoryService.exists(id)).thenReturn(true);
    }

}
