package com.jeanbarcellos.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.jeanbarcellos.demo.services.TypeService.Types;

class TypeServiceTest {

    @InjectMocks
    private TypeService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void visibility_entryNoArgs_shouldReturnTreeTypes() {

        // Arrange && Act
        var result = this.service.visibility();

        // Arrange
        assertEquals(3, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());
        assertEquals(3, result.get(2).getId());
    }

    @Test
    void people_entryNoArgs_shouldReturnTwoTypes() {

        // Arrange && Act
        var result = this.service.people();

        // Arrange
        assertEquals(2, result.size());
        assertEquals(4, result.get(0).getId());
        assertEquals(5, result.get(1).getId());
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3 })
    void checkBy_entryVisibilityANdValidId_shouldReturnTrue(Integer id) {

        // Arrange
        var type = Types.VISIBILITY;

        // Act
        var result = this.service.checkBy(type, id);

        // Assert
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(ints = { 4, 5 })
    void checkBy_entryVisibilityANdInvalidId_shouldReturnFalse(Integer id) {

        // Arrange
        var type = Types.VISIBILITY;

        // Act
        var result = this.service.checkBy(type, id);

        // Assert
        assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(ints = { 4, 5 })
    void checkBy_entryPeopleAndValidId_shouldReturnTrue(Integer id) {

        // Arrange
        var type = Types.PEOPLE;

        // Act
        var result = this.service.checkBy(type, id);

        // Assert
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3 })
    void checkBy_entryPeopleAndInvalidId_shouldReturnFalse(Integer id) {

        // Arrange
        var type = Types.PEOPLE;

        // Act
        var result = this.service.checkBy(type, id);

        // Assert
        assertFalse(result);
    }

}
