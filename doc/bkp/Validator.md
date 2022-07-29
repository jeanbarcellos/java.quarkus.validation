adadsada

```java
package com.jeanbarcellos.core;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.inject.spi.CDI;
import javax.validation.ConstraintViolation;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.AggregateResourceBundleLocator;

import com.jeanbarcellos.core.exception.ValidationException;

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

        // var bundleNames = Arrays.asList("messages", "ValidationMessages");
        // var interpolator = new ResourceBundleMessageInterpolator(new AggregateResourceBundleLocator(bundleNames), true);

        // javax.validation.Validation.byProvider(HibernateValidator.class)
        //         .configure()
        //         .messageInterpolator(interpolator)
        //         .buildValidatorFactory()
        //         .getValidator();

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
```
