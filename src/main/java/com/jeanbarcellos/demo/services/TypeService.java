package com.jeanbarcellos.demo.services;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.commons.lang3.reflect.MethodUtils;

import com.fasterxml.jackson.annotation.JsonValue;
import com.jeanbarcellos.demo.dtos.TypeResponse;
import com.jeanbarcellos.demo.repositories.TypeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class TypeService {

    @Inject
    private TypeRepository repository;

    public enum Types {
        VISIBILITY("visibility", 1),
        PEOPLE("people", 2);

        String methodName;
        Integer codigo;

        private Types(String methodName, Integer codigo) {
            this.methodName = methodName;
            this.codigo = codigo;
        }

        @JsonValue
        String getValue() {
            return this.methodName;
        }

        @JsonValue
        Integer getCodigo() {
            return this.codigo;
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

    public List<TypeResponse> list() {
        return TypeResponse.of(this.repository.listAll());
    }

    public boolean exists(Integer id) {
        return this.repository.existsById(id);
    }

    public List<TypeResponse> visibility() {
        return TypeResponse.of(this.repository.findByParent(Types.VISIBILITY.getCodigo()));
    }

    public List<TypeResponse> people() {
        return TypeResponse.of(this.repository.findByParent(Types.PEOPLE.getCodigo()));
    }

}
