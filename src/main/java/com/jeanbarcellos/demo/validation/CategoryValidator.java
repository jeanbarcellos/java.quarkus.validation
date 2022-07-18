package com.jeanbarcellos.demo.validation;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.jeanbarcellos.demo.services.CategoryService;
import com.jeanbarcellos.demo.validation.annotation.CategoryCheck;

/**
 * Validador de Category
 *
 * @author Jean Silva de Barcellos
 *
 */
@ApplicationScoped
public class CategoryValidator implements ConstraintValidator<CategoryCheck, UUID> {

    private CategoryService service;

    public CategoryValidator(CategoryService service) {
        this.service = service;
    }

    @Override
    public boolean isValid(UUID value, ConstraintValidatorContext context) {
        return this.service.exists(value);
    }

}
