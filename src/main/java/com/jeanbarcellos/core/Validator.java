package com.jeanbarcellos.core;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import com.jeanbarcellos.core.exception.ValidationException;

public class Validator {

    private Validator() {

    }

    public static <TModel> Set<ConstraintViolation<TModel>> validate(TModel model) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        javax.validation.Validator validator = factory.getValidator();

        return validator.validate(model);
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
