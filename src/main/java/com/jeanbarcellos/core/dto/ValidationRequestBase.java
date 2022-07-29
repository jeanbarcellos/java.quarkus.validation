package com.jeanbarcellos.core.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jeanbarcellos.core.Constants;
import com.jeanbarcellos.core.exception.ValidationException;
import com.jeanbarcellos.core.validation.Validator;

public abstract class ValidationRequestBase extends RequestBase {

    @JsonIgnore
    private Set<ConstraintViolation<ValidationRequestBase>> validationResult = new HashSet<>();

    public Set<ConstraintViolation<ValidationRequestBase>> getValidationResult() {
        return validationResult;
    }

    @JsonIgnore
    public boolean isValid() {
        this.validationResult = Validator.validate(this);

        return this.validationResult.isEmpty();
    }

    public void validate() {
        if (!this.isValid()) {
            throw new ValidationException(
                    Constants.MSG_ERROR_VALIDATION,
                    validationResult.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList()));
        }
    }
}
