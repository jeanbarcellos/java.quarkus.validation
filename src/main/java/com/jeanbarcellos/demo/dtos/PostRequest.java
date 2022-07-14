package com.jeanbarcellos.demo.dtos;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jeanbarcellos.core.dto.ValidationRequestBase;
import com.jeanbarcellos.demo.validation.CategoryCheck;

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
public class PostRequest extends ValidationRequestBase {

    public static final String MSG_ERROR_TITLE_NOT_NULL_OR_EMPTY = "O campo 'title' não deve ser nulo ou estar vazio";
    public static final String MSG_ERROR_TITLE_SIZE = "O campo 'text' deve possuir tamanho entre 4 e 128";

    public static final String MSG_ERROR_TITLE_CATEGORY_ID_INVALID = "O campo 'categoryId' possui valor inválido";

    public static final String MSG_ERROR_TEXT_NOT_NULL_OR_EMPTY = "O campo 'text' não deve ser nulo ou estar vazio";

    public static final String MSG_ERROR_AUTHOR_NOT_NULL = "O campo 'author' não deve ser nulo";

    @JsonIgnore
    private UUID id;

    @CategoryCheck(message = MSG_ERROR_TITLE_CATEGORY_ID_INVALID)
    private UUID categoryId;

    @NotEmpty(message = MSG_ERROR_TITLE_NOT_NULL_OR_EMPTY)
    @Size(min = 4, max = 128, message = MSG_ERROR_TITLE_SIZE)
    private String title;

    @NotEmpty(message = MSG_ERROR_TEXT_NOT_NULL_OR_EMPTY)
    private String text;

    @Valid
    @NotNull(message = MSG_ERROR_AUTHOR_NOT_NULL)
    private PeopleRequest author;

}
