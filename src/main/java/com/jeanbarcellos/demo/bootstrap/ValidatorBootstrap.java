package com.jeanbarcellos.demo.bootstrap;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Validator;

import io.quarkus.runtime.Startup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Startup
@ApplicationScoped
public class ValidatorBootstrap {

    Validator validator;

    ValidatorBootstrap(Validator validator) {
        log.info(validator.toString());
        this.validator = validator;
    }
}