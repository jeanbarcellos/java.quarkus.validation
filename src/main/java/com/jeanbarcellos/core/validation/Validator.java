package com.jeanbarcellos.core.validation;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;

import com.jeanbarcellos.core.Constants;
import com.jeanbarcellos.core.exception.ValidationException;
import com.jeanbarcellos.core.utils.ContainerUtils;

public class Validator {

    private Validator() {
    }

    public static <T> Set<ConstraintViolation<T>> validate(T model) {
        return getInstanceHibernateValidator().validate(model);
    }

    public static <T> void validateWithThrowException(T model) {
        Set<ConstraintViolation<T>> validateResult = Validator.validate(model);

        if (!validateResult.isEmpty()) {
            throw new ValidationException(
                    Constants.MSG_ERROR_VALIDATION,
                    validateResult.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList()));
        }
    }

    private static javax.validation.Validator getInstanceHibernateValidator() {
        return ContainerUtils.get(javax.validation.Validator.class);
    }

}
