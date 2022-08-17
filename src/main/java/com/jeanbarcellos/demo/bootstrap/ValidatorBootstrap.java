package com.jeanbarcellos.demo.bootstrap;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Validator;

import io.quarkus.runtime.Startup;
import lombok.extern.slf4j.Slf4j;

/**
 * Starter para o Validator para injetar o Validator na inicialização do App
 *
 * É necessário o starter para deixar a instância disponível para validação
 * rápida
 */
@Slf4j
@Startup
@ApplicationScoped
public class ValidatorBootstrap {

    Validator validator;

    ValidatorBootstrap(Validator validator) {
        log.info(ValidatorBootstrap.class.getName());
        log.info("Inject instance: " + validator.toString());
        this.validator = validator;
    }
}