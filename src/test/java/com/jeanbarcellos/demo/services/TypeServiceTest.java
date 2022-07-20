package com.jeanbarcellos.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.jeanbarcellos.demo.domain.Type;
import com.jeanbarcellos.demo.repositories.TypeRepository;
import com.jeanbarcellos.demo.services.TypeService.Types;

class TypeServiceTest {

    @InjectMocks
    private TypeService service;

    @Mock
    private TypeRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void visibility_entryNoArgs_shouldReturnTreeTypes() {

        // Setup
        Mockito.when(this.repository.findByParent(Mockito.anyInt())).thenReturn(this.createVisibilityTypes());

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

        // Setup
        Mockito.when(this.repository.findByParent(Mockito.anyInt())).thenReturn(this.createPeopleTypes());

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

        // Setup
        Mockito.when(this.repository.findByParent(Mockito.anyInt())).thenReturn(this.createVisibilityTypes());

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

        // Setup
        Mockito.when(this.repository.findByParent(Mockito.anyInt())).thenReturn(Arrays.asList());

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

        // Setup
        Mockito.when(this.repository.findByParent(Mockito.anyInt())).thenReturn(this.createPeopleTypes());

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

        // Setup
        Mockito.when(this.repository.findByParent(Mockito.anyInt())).thenReturn(Arrays.asList());

        // Arrange
        var type = Types.PEOPLE;

        // Act
        var result = this.service.checkBy(type, id);

        // Assert
        assertFalse(result);
    }

    private List<Type> createVisibilityTypes() {
        return Arrays.asList(
                new Type(1, 1, "Público"),
                new Type(2, 1, "Privado"),
                new Type(3, 1, "Não listado"));
    }

    private List<Type> createPeopleTypes() {
        return Arrays.asList(
                new Type(4, 2, "Física"),
                new Type(5, 2, "Jurídica"));
    }

}
