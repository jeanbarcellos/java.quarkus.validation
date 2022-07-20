package com.jeanbarcellos.demo.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import org.apache.commons.lang3.reflect.MethodUtils;

import com.fasterxml.jackson.annotation.JsonValue;
import com.jeanbarcellos.demo.dtos.TypeResponse;

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

    public boolean checkBy(Types type, Integer id) {

        String methodName = type.getValue();

        List<TypeResponse> list = invokeMethod(this, methodName);

        Optional<TypeResponse> result = list
                .stream()
                .parallel()
                .filter(t -> t.getId().equals(id))
                .findAny();

        return result.isPresent();
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

    private static <T> T invokeMethod(Object o, String methodName) {
        try {
            return (T) MethodUtils.invokeMethod(o, methodName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

// List<TypeResponse> list = MethodUtils.invokeMethod(this, methodName);
// List<TypeResponse> list = this.visibility();
