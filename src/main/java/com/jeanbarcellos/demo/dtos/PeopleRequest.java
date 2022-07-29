package com.jeanbarcellos.demo.dtos;

import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jeanbarcellos.core.dto.ValidationRequestBase;
import com.jeanbarcellos.demo.services.TypeService.Types;
import com.jeanbarcellos.demo.validation.annotation.TypeCheck;

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
public class PeopleRequest extends ValidationRequestBase {

    @JsonIgnore
    private UUID id;

    @NotEmpty(message = "{PeopleRequest.name.notNullOrEmpty}")
    @Size(min = 4, max = 128, message = "{PeopleRequest.name.size}")
    private String name;

    @NotEmpty(message = "{PeopleRequest.email.notNullOrEmpty}")
    @Email(message = "{PeopleRequest.email.invalid}")
    private String email;

    @NotEmpty(message = "{PeopleRequest.cpf.notNullOrEmpty}")
    @CPF(message = "{PeopleRequest.cpf.invalid}")
    private String identificationNumber;

    @TypeCheck(type = Types.PEOPLE, message = "{PeopleRequest.visibility.invalid}")
    private Integer type;
}
