package com.jeanbarcellos.demo.services;

import static com.jeanbarcellos.core.exception.Assertions.assertValidationExceptionErrorListContains;
import static com.jeanbarcellos.core.exception.Assertions.assertValidationExceptionErrorListSize;
import static com.jeanbarcellos.core.exception.Assertions.assertValidationExceptionMessage;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.github.javafaker.Faker;
import com.jeanbarcellos.core.Constants;
import com.jeanbarcellos.core.exception.ValidationException;
import com.jeanbarcellos.core.utils.DocUtils;
import com.jeanbarcellos.demo.dtos.PeopleRequest;
import com.jeanbarcellos.demo.dtos.PostRequest;
import com.jeanbarcellos.demo.services.TypeService.Types;

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

    // Usar o injectmock do quarkus para mockar a instancia do container
    @InjectMock
    private TypeService typeService;

    private Faker faker = new Faker(new Locale("pt-BR"));

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

        assertValidationExceptionMessage(exception, Constants.MSG_ERROR_VALIDATION);
        assertValidationExceptionErrorListSize(exception, 5);
        assertValidationExceptionErrorListContains(exception,
                PostRequest.MSG_ERROR_TITLE_NOT_NULL_OR_EMPTY,
                PostRequest.MSG_ERROR_CATEGORY_ID_INVALID,
                PostRequest.MSG_ERROR_TEXT_NOT_NULL_OR_EMPTY,
                PostRequest.MSG_ERROR_AUTHOR_NOT_NULL,
                PostRequest.MSG_ERROR_VISIBILITY_INVALID);
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

        assertValidationExceptionMessage(exception, Constants.MSG_ERROR_VALIDATION);
        assertValidationExceptionErrorListSize(exception, 3);
        assertValidationExceptionErrorListContains(exception,
                PostRequest.MSG_ERROR_CATEGORY_ID_INVALID,
                PostRequest.MSG_ERROR_AUTHOR_NOT_NULL);
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

        assertValidationExceptionMessage(exception, Constants.MSG_ERROR_VALIDATION);
        assertValidationExceptionErrorListSize(exception, 2);
        assertValidationExceptionErrorListContains(exception,
                PostRequest.MSG_ERROR_AUTHOR_NOT_NULL,
                PostRequest.MSG_ERROR_VISIBILITY_INVALID);
    }

    @Test
    void insert_entryValidRequest_shouldThrowsExceptionWithOneError() {

        // Arrange
        UUID categoryId = UUID.randomUUID();
        Integer peopleType = 4;
        Integer postVisibility = 1;

        var author = new PeopleRequest();
        author.setName(faker.name().fullName());
        author.setEmail(faker.internet().emailAddress());
        author.setIdentificationNumber(DocUtils.generateCPF());
        author.setType(peopleType);

        var request = new PostRequest();
        request.setTitle("text asdsadas");
        request.setText("text asdsadas");
        request.setCategoryId(categoryId);
        request.setAuthor(author);
        request.setVisibility(postVisibility);

        setupCategoryService_exists_returnTrue(categoryId);
        setupTypeService_checkBy_returnTrue(Types.PEOPLE, peopleType);
        setupTypeService_checkBy_returnTrue(Types.VISIBILITY, postVisibility);

        // Act
        var retorno = this.service.insert(request);

        // Assert
        assertTrue(retorno);
    }

    // Setups personalizados

    private void setupCategoryService_exists_returnTrue(UUID id) {
        Mockito.when(this.categoryService.exists(id)).thenReturn(true);
    }

    private void setupTypeService_checkBy_returnTrue(Types type, Integer id) {
        Mockito.when(this.typeService.checkBy(type, id)).thenReturn(true);
    }

}
