package com.jeanbarcellos.demo.domain;

import java.util.UUID;

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
public class Person {

    private UUID id;

    private String name;

    private String identificationNumber; // CPF/CNPJ

    private String email;

}
