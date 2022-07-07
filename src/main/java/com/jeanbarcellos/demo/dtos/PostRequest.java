package com.jeanbarcellos.demo.dtos;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class PostRequest {

    public static final String MSG_ERROR_TITLE_NOT_NULL_OR_EMPTY = "O campo 'title' não deve ser nulo ou estar vazio";
    public static final String MSG_ERROR_TITLE_SIZE = "O campo 'text' deve possuir tamanho deve ser entre 4 e 128";

    public static final String MSG_ERROR_TEXT_NOT_NULL_OR_EMPTY = "O campo 'text' não deve ser nulo ou estar vazio";

    public static final String MSG_ERROR_AUTHOR_NOT_NULL = "O campo 'author' não deve ser nulo";

    @JsonIgnore
    private UUID id;

    @NotEmpty(message = MSG_ERROR_TITLE_NOT_NULL_OR_EMPTY)
    @Size(min = 4, max = 128, message = MSG_ERROR_TITLE_SIZE)
    private String title;

    @NotEmpty(message = MSG_ERROR_TEXT_NOT_NULL_OR_EMPTY)
    private String text;

    @Valid
    @NotNull(message = MSG_ERROR_AUTHOR_NOT_NULL)
    private PeopleRequest author;

}
