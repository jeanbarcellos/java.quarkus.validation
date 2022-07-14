package com.jeanbarcellos.core;

import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.inject.spi.CDI;
import javax.validation.ConstraintViolation;

import com.jeanbarcellos.core.exception.ValidationException;

public class Validator {

    private Validator() {

    }

    public static <TModel> Set<ConstraintViolation<TModel>> validate(TModel model) {
        return getInstanceHibernateValidator().validate(model);
    }

    public static <TModel> void validateWithThrowException(TModel model) {
        Set<ConstraintViolation<TModel>> validateResult = Validator.validate(model);

        if (!validateResult.isEmpty()) {
            throw new ValidationException(
                    Constants.MSG_ERROR_VALIDATION,
                    validateResult.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList()));
        }
    }

    private static javax.validation.Validator getInstanceHibernateValidator() {
        // ValidatorFactory validatorFactory =
        // Validation.byProvider(HibernateValidator.class)
        // .configure()
        // .buildValidatorFactory();
        // return validatorFactory.getValidator();

        // Obter Valiudator default
        // ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        // return factory.getValidator();

        return CDI.current().select(javax.validation.Validator.class).get();
    }

}
