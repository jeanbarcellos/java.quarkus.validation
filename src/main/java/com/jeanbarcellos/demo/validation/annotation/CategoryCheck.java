package com.jeanbarcellos.demo.validation.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.jeanbarcellos.demo.validation.CategoryValidator;
import com.jeanbarcellos.demo.validation.annotation.CategoryCheck.List;

/**
 * Annotation para verificar se o ID de {@code com.jeanbarcellos.demo.domain.Category} é válido.
 *
 * <p>
 * Suporte os seguints tipos:
 * <ul>
 * <li>{@code UUID} (Identificador único universal )</li>
 * </ul>
 *
 * @author Jean Silva de Barcellos
 *
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = CategoryValidator.class)
@Repeatable(List.class)
@Documented
public @interface CategoryCheck {

    String message() default "{com.jeanbarcellos.demo.validation.annotation.CategoryCheck.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Define várias restrições {@code @CategoryCheck} no mesmo elemento.
     *
     * @see CategoryCheck
     */
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        CategoryCheck[] value();
    }
}
