package com.jeanbarcellos.core;

import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.inject.spi.CDI;
import javax.validation.ConstraintViolation;

import com.jeanbarcellos.core.exception.ValidationException;

public class Validator {

    private Validator() {

    }

    private static javax.validation.Validator getHibernateValidator() {
        // Obter Valiudator default
        // ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        // return factory.getValidator();

        // Obtem o validator com injeção de dependencia
        return CDI.current().select(javax.validation.Validator.class).get();
    }

    public static <TModel> Set<ConstraintViolation<TModel>> validate(TModel model) {
        return getHibernateValidator().validate(model);
    }

    public static <TModel> void validateWithThrowException(TModel model) {
        Set<ConstraintViolation<TModel>> validateResult = Validator.validate(model);

        if (!validateResult.isEmpty()) {
            throw new ValidationException(
                    Constants.MSG_ERROR_VALIDATION,
                    validateResult.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList()));
        }
    }

}
