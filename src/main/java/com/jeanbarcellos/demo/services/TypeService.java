package com.jeanbarcellos.demo.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import org.apache.commons.lang3.reflect.MethodUtils;

import com.fasterxml.jackson.annotation.JsonValue;
import com.jeanbarcellos.demo.dtos.TypeResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class TypeService {

    public enum Types {
        VISIBILITY("visibility"),
        PEOPLE("people");

        String methodName;

        private Types(String methodName) {
            this.methodName = methodName;
        }

        @JsonValue
        String getValue() {
            return this.methodName;
        }

    }

    @SuppressWarnings("unchecked")
    public boolean checkBy(Types type, Integer id) {
        log.info(String.format("Instânicia: %s", this));
        log.info(String.format("Verificando a existencia do método '%s' e id '%s'", type, id));

        try {
            String methodName = type.getValue();

            List<TypeResponse> list = (List<TypeResponse>) MethodUtils.invokeMethod(this, methodName);

            Optional<TypeResponse> result = list
                    .stream()
                    .parallel()
                    .filter(t -> t.getId().equals(id))
                    .findAny();

            return result.isPresent();
        } catch (Exception e) {
            log.error("Méthodo inexistente", e);
        }

        return false;
    }

    public List<TypeResponse> visibility() {
        return Arrays.asList(
                new TypeResponse(1, "Público"),
                new TypeResponse(2, "Privado"),
                new TypeResponse(3, "Não listado"));
    }

    public List<TypeResponse> people() {
        return Arrays.asList(
                new TypeResponse(4, "Física"),
                new TypeResponse(5, "Jurídica"));
    }

}
