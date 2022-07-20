package com.jeanbarcellos.demo.validation;

import javax.enterprise.context.Dependent;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.jeanbarcellos.demo.services.TypeService;
import com.jeanbarcellos.demo.services.TypeService.Types;
import com.jeanbarcellos.demo.validation.annotation.TypeCheck;

/**
 * Validador de Tipos
 *
 * @author Jean Silva de Barcellos
 *
 */
@Dependent
public class TypeValidator implements ConstraintValidator<TypeCheck, Integer> {

    private TypeService service;

    private Types type;

    public TypeValidator(TypeService service) {
        this.service = service;
    }

    @Override
    public void initialize(TypeCheck parameters) {
        this.type = parameters.type();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return this.service.checkBy(type, value);
    }

}
