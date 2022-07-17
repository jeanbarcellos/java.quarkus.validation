package com.jeanbarcellos.core.utils;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;

/**
 * Utiliário com Documentos do BR
 *
 * @author Jean Silva de Barcellos
 */
public class DocUtils {

    private static final String ZERO = "0";

    private static final Integer CPF_LENGHT = 11;
    private static final String CPF_REGEX = "(\\d{3})(\\d{3})(\\d{3})(\\d{2})";
    private static final String CPF_REPLACEMENT = "$1.$2.$3-$4";

    private static final Integer CNPJ_LENGHT = 14;
    private static final String CNPJ_REGEX = "(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})";
    private static final String CNPJ_REPLACEMENT = "$1.$2.$3/$4-$5";

    private DocUtils() {

    }

    public static String generateCPF() {
        return generateCPF(false);
    }

    public static String generateCPF(boolean comMascara) {
        int n = 9;
        int n1 = random(n);
        int n2 = random(n);
        int n3 = random(n);
        int n4 = random(n);
        int n5 = random(n);
        int n6 = random(n);
        int n7 = random(n);
        int n8 = random(n);
        int n9 = random(n);
        int d1 = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10;

        d1 = 11 - (mod(d1, 11));

        if (d1 >= 10)
            d1 = 0;

        int d2 = d1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11;

        d2 = 11 - (mod(d2, 11));

        if (d2 >= 10)
            d2 = 0;

        var resultado = StringUtils.EMPTY + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + d1 + d2;

        return comMascara ? formatCPF(resultado) : resultado;
    }

    public static boolean isCPF(String cpf) {
        if (StringUtils.isNullOrEmpty(cpf))
            return false;

        cpf = StringUtils.onlyNumbers(cpf);

        var numerosInvalidos = Arrays.asList("00000000000", "11111111111", "22222222222", "33333333333", "44444444444",
                "55555555555", "66666666666", "77777777777", "88888888888", "99999999999");

        if (numerosInvalidos.contains(cpf) || (cpf.length() != CPF_LENGHT))
            return false;

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do cpf em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else
                dig11 = (char) (r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
                return (true);
            else
                return (false);
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public static String formatCPF(String cpf) {
        cpf = StringUtils.leftPad(cpf, CPF_LENGHT, ZERO);
        return StringUtils.replaceAll(CPF_REGEX, CPF_REPLACEMENT, cpf);
    }

    /* ****************************************************************** */

    public static String generateCNPJ() {
        return generateCNPJ(false);
    }

    public static String generateCNPJ(boolean comMascara) {
        int n = 9;
        int n1 = random(n);
        int n2 = random(n);
        int n3 = random(n);
        int n4 = random(n);
        int n5 = random(n);
        int n6 = random(n);
        int n7 = random(n);
        int n8 = random(n);
        int n9 = 0;
        int n10 = 0;
        int n11 = 0;
        int n12 = 1;
        int d1 = n12 * 2 + n11 * 3 + n10 * 4 + n9 * 5 + n8 * 6 + n7 * 7 + n6 * 8 + n5 * 9 + n4 * 2 + n3 * 3 + n2 * 4
                + n1 * 5;

        d1 = 11 - (mod(d1, 11));

        if (d1 >= 10)
            d1 = 0;

        int d2 = d1 * 2 + n12 * 3 + n11 * 4 + n10 * 5 + n9 * 6 + n8 * 7 + n7 * 8 + n6 * 9 + n5 * 2 + n4 * 3 + n3 * 4
                + n2 * 5 + n1 * 6;

        d2 = 11 - (mod(d2, 11));

        if (d2 >= 10)
            d2 = 0;

        var resultado = StringUtils.EMPTY + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + n10 + n11 + n12 + d1 + d2;

        return comMascara ? formatCNPJ(resultado) : resultado;
    }

    public static boolean isCNPJ(String cnpj) {
        if (StringUtils.isNullOrEmpty(cnpj))
            return false;

        cnpj = StringUtils.onlyNumbers(cnpj);

        var numerosInvalidos = Arrays.asList("00000000000000", "11111111111111", "22222222222222", "33333333333333",
                "44444444444444", "55555555555555", "66666666666666", "77777777777777", "88888888888888",
                "99999999999999");

        // considera-se erro cnpj's formados por uma sequencia de numeros iguais
        if (numerosInvalidos.contains(cnpj) || (cnpj.length() != CNPJ_LENGHT))
            return false;

        char dig13, dig14;
        int sm, i, r, num, peso;

        // "try" - protege o código para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
                // converte o i-ésimo caractere do cnpj em um número:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posição de '0' na tabela ASCII)
                num = (int) (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig13 = '0';
            else
                dig13 = (char) ((11 - r) + 48);

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig14 = '0';
            else
                dig14 = (char) ((11 - r) + 48);

            // Verifica se os dígitos calculados conferem com os dígitos informados.
            if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13)))
                return (true);
            else
                return (false);
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public static String formatCNPJ(String cnpj) {
        cnpj = StringUtils.leftPad(cnpj, CNPJ_LENGHT, ZERO);
        return cnpj.replaceAll(CNPJ_REGEX, CNPJ_REPLACEMENT);
    }

    /* ****************************************************************** */

    public static boolean isCPForCNPJ(String number) {
        if (StringUtils.isNullOrEmpty(number)) {
            return false;
        }

        number = StringUtils.onlyNumbers(number);

        return isCPF(StringUtils.leftPad(number, CPF_LENGHT, ZERO))
                || isCNPJ(StringUtils.leftPad(number, CNPJ_LENGHT, ZERO));
    }

    public static boolean isCPForCNPJ(Long number) {
        return isCPForCNPJ(number.toString());
    }

    /* ****************************************************************** */

    private static int random(int n) {
        return new Random().nextInt(n);
    }

    private static int mod(int dividendo, int divisor) {
        return (int) Math.round(dividendo - (Math.floor(dividendo / divisor) * divisor));
    }

}