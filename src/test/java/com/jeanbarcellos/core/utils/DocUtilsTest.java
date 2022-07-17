package com.jeanbarcellos.core.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void isCPF_inputNull_returnFalse() {

        // Arrange
        String numero = null;

        // Act
        var result = DocUtils.isCPF(numero);

        // Assert
        assertFalse(result);
    }

    @Test
    void isCPF_inputEmptyString_returnFalse() {

        // Arrange
        String numero = "";

        // Act
        var result = DocUtils.isCPF(numero);

        // Assert
        assertFalse(result);
    }

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
        var result = DocUtils.generateCPF();

        // assert
        assertNotNull(result);
        assertEquals(11, result.length());
    }

    @Test
    void generateCPF_entryArgTrue_shouldGenerateCpfValidAndFormated() {

        // Arrange && Act
        boolean comMascara = true;
        var result = DocUtils.generateCPF(comMascara);

        // assert
        assertNotNull(result);
        assertEquals(14, result.length());
    }

    @Test
    void formatCPF_inputString11Chars_shouldReturnStringFromated() {

        // Arrange
        String cpf = "00123456789";
        String cpfFormated = "001.234.567-89";

        // Arrange && Act
        String result = DocUtils.formatCPF(cpf);

        // assert
        assertEquals(cpfFormated, result);
    }

    @Test
    void formatCPF_inputString3Chars_shouldReturnStringFromated() {

        // Arrange
        String cpf = "189";
        String cpfFormated = "000.000.001-89";

        // Arrange && Act
        String result = DocUtils.formatCPF(cpf);

        // assert
        assertEquals(cpfFormated, result);
    }

    /* ****************************************************************** */

    @Test
    void isCNPJ_inputNull_returnFalse() {

        // Arrange
        String numero = null;

        // Act
        var result = DocUtils.isCNPJ(numero);

        // Assert
        assertFalse(result);
    }

    @Test
    void isCNPJ_inputEmptyString_returnFalse() {

        // Arrange
        String numero = "";

        // Act
        var result = DocUtils.isCNPJ(numero);

        // Assert
        assertFalse(result);
    }

    @Test
    void isCNPJ_entryCnpjInvalid_shouldReturnFalse() {

        // Arrange
        var cpf = "12345678900";

        // Act
        var result = DocUtils.isCNPJ(cpf);

        // assert
        assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "00000000000000", "11111111111111", "22222222222222", "33333333333333", "44444444444444", "55555555555555",
            "66666666666666", "77777777777777", "88888888888888", "99999999999999", "1234567890134", "ASDASDASDASASD",
            "123ASD123AS13S"
    })
    void isCNPJ_entryCnpjInvalids_shouldReturnFalse(String cnpj) {

        // Arrange && Act
        var result = DocUtils.isCNPJ(cnpj);

        // assert
        assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "02804097000110", "46313752000103", "46552618000165", "06559405000140"
    })
    void isCNPJ_entryCnpjValid_shouldReturnTrue(String cnpj) {

        // Arrange && Act
        var result = DocUtils.isCNPJ(cnpj);

        // assert
        assertTrue(result);
    }

    @Test
    void generateCNPJ_entryNoArgs_shouldGenerateCnpjValid() {

        // Arrange && Act
        var result = DocUtils.generateCNPJ();

        // assert
        assertNotNull(result);
        assertEquals(14, result.length());
    }

    @Test
    void generateCNPJ_entryArgTrue_shouldGenerateCnpjValidAndFormated() {

        // Arrange && Act
        boolean comMascara = true;
        var result = DocUtils.generateCNPJ(comMascara);

        // assert
        assertNotNull(result);
        assertEquals(18, result.length());
    }

    @Test
    void formatCNPJ_inputString14Chars_shouldReturnStringFromated() {

        // Arrange
        String cnpj = "2804097000110";
        String cnpjFormated = "02.804.097/0001-10";

        // Arrange && Act
        String result = DocUtils.formatCNPJ(cnpj);

        // assert
        assertEquals(cnpjFormated, result);
    }

    @Test
    void formatCNPJ_inputString18Chars_shouldReturnStringFromated() {

        // Arrange
        String cnpj = "191";
        String cnpjFormated = "00.000.000/0001-91";

        // Arrange && Act
        String result = DocUtils.formatCNPJ(cnpj);

        // assert
        assertEquals(cnpjFormated, result);
    }

    /* ****************************************************************** */

    @Test
    void isCPForCNPJ_inputNull_returnFalse() {

        // Arrange
        String numero = null;

        // Act
        var result = DocUtils.isCPForCNPJ(numero);

        // Assert
        assertFalse(result);
    }

    @Test
    void isCPForCNPJ_inputEmptyString_returnFalse() {

        // Arrange
        String numero = "";

        // Act
        var result = DocUtils.isCPForCNPJ(numero);

        // Assert
        assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "49118537006", "57565900052", "01531188060", "96035245498",
            "02804097000110", "46313752000103", "46552618000165", "06559405000140",
    })
    void isCPForCNPJ_inputStringValids_returnTrue(String numero) {

        // Arrange && Act
        var result = DocUtils.isCPForCNPJ(numero);

        // Assert
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "77777777", "88888888888", "99999999999", "1234567891", "ASDASDASDAS", "123ASD123AS",
            "66666666666666", "77777777777777", "88888888888888", "99999999999999", "1234567890134", "ASDASDASDASASD"
    })
    void isCPForCNPJ_inputStringInvalids_returnFalse(String numero) {

        // Arrange && Act
        var result = DocUtils.isCPForCNPJ(numero);

        // Assert
        assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(longs = {
            77777777L, 88888888888L, 99999999999L, 1234567891,
            66666666666666L, 77777777777777L, 88888888888888L, 99999999999999L, 1234567890134L
    })
    void isCPForCNPJ_inputLongInvalids_returnFalse(Long numero) {

        // Arrange && Act
        var result = DocUtils.isCPForCNPJ(numero);

        // Assert
        assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(longs = {
            1531188060L, 191L, 2804097000110L
    })
    void isCPForCNPJ_inputLongValids_returnFalse(Long numero) {

        // Arrange && Act
        var result = DocUtils.isCPForCNPJ(numero);

        // Assert
        assertTrue(result);
    }
}
