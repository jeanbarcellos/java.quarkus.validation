package com.jeanbarcellos.core.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class DocUtilsTest {

    @Test
    void isCPF_entryCpfInvalid_shouldReturnFalse() {

        // Arrange
        var cpf = "12345678900";

        // Act
        var result = DocUtils.isCPF(cpf);

        // assert
        assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "00000000000", "11111111111", "22222222222", "33333333333", "44444444444", "55555555555", "66666666666",
            "77777777777", "88888888888", "99999999999", "1234567891", "ASDASDASDAS", "123ASD123AS"
    })
    void isCPF_entryCpfInvalids_shouldReturnFalse(String cpf) {

        // Arrange && Act
        var result = DocUtils.isCPF(cpf);

        // assert
        assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "49118537006", "57565900052", "01531188060", "96035245498"
    })
    void isCPF_entryCpfValid_shouldReturnTrue(String cpf) {

        // Arrange && Act
        var result = DocUtils.isCPF(cpf);

        // assert
        assertTrue(result);
    }

    @Test
    void generateCPF_entryNoArgs_shouldGenerateCpfValid() {

        // Arrange && Act
        var result = DocUtils.generateCNPJ();

        // assert
        assertNotNull(result);
    }

}
