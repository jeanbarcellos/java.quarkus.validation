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

import com.jeanbarcellos.demo.services.TypeService.Types;
import com.jeanbarcellos.demo.validation.TypeValidator;
import com.jeanbarcellos.demo.validation.annotation.TypeCheck.List;

/**
 * Annotation para verificar se o Type é válido.
 *
 * <p>
 * Suporte os seguintes tipos:
 * <ul>
 * <li>{@code Integer} (Código do tipo)</li>
 * </ul>
 *
 * @author Jean Silva de Barcellos
 *
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = TypeValidator.class)
@Repeatable(List.class)
@Documented
public @interface TypeCheck {

    String message() default "{com.jeanbarcellos.demo.validation.annotation.TypeCheck.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Types type();

    /**
     * Define várias restrições {@code @CategoryCheck} no mesmo elemento.
     *
     * @see CategoryCheck
     */
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        TypeCheck[] value();
    }
}
