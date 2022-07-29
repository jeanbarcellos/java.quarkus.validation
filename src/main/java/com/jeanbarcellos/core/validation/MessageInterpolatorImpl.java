package com.jeanbarcellos.core.validation;

import java.util.Arrays;
import java.util.Locale;

import javax.enterprise.context.ApplicationScoped;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.AggregateResourceBundleLocator;

@ApplicationScoped
public class MessageInterpolatorImpl
        implements javax.validation.MessageInterpolator {

    ResourceBundleMessageInterpolator interpolator;

    public MessageInterpolatorImpl() {
        var bundleNames = Arrays.asList("messages", "ValidationMessages");
        boolean cachingEnabled = false;

        this.interpolator = new ResourceBundleMessageInterpolator(
                new AggregateResourceBundleLocator(bundleNames),
                cachingEnabled);
    }

    @Override
    public String interpolate(String messageTemplate, Context context) {
        return this.interpolator.interpolate(messageTemplate, context);
    }

    @Override
    public String interpolate(String messageTemplate, Context context, Locale locale) {
        return this.interpolator.interpolate(messageTemplate, context, locale);
    }

}
