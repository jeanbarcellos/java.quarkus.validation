package com.jeanbarcellos.demo.dtos;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jeanbarcellos.core.dto.ValidationRequestBase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequest extends ValidationRequestBase {

    public static final String MSG_ERROR_NAME_NOT_NULL_OR_EMPTY = "O campo 'name' não deve ser nulo ou estar vazio";
    public static final String MSG_ERROR_NAME_SIZE = "O campo 'name' deve possuir tamanho entre 4 e 128";

    public static final String MSG_ERROR_DESCRIPTION_NOT_NULL_OR_EMPTY = "O campo 'description' não deve ser nulo ou estar vazio";
    public static final String MSG_ERROR_DESCRIPTION_SIZE = "O campo 'description' deve possuir tamanho máximo de 255";

    @JsonIgnore
    private UUID id;

    @NotEmpty(message = MSG_ERROR_NAME_NOT_NULL_OR_EMPTY)
    @Size(min = 4, max = 128, message = MSG_ERROR_NAME_SIZE)
    private String name;

    @NotEmpty(message = MSG_ERROR_DESCRIPTION_NOT_NULL_OR_EMPTY)
    @Size(max = 255, message = MSG_ERROR_DESCRIPTION_SIZE)
    private String description;

}
