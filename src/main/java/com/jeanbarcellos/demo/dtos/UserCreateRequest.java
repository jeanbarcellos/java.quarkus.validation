package com.jeanbarcellos.demo.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.jeanbarcellos.core.dto.ValidationRequestBase;
import com.jeanbarcellos.demo.validation.annotation.FieldMatch;

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
@FieldMatch.List({
    @FieldMatch(first = "password", second = "confirmPassword", message = UserCreateRequest.MSG_ERROR_PASSWORD_NOT_EQUALS),
    @FieldMatch(first = "email", second = "confirmEmail", message = UserCreateRequest.MSG_ERROR_EMAIL_NOT_EQUALS)
})
public class UserCreateRequest extends ValidationRequestBase {

    public static final String MSG_ERROR_PASSWORD_NOT_NULL_OR_EMPTY = "O campo 'password' não deve ser nulo ou estar vazio";
    public static final String MSG_ERROR_PASSWORD_SIZE = "O campo 'password' deve possuir tamanho entre {min} e {max}";

    public static final String MSG_ERROR_CONFIRM_PASSWORD_NOT_NULL_OR_EMPTY = "O campo 'confirmPassword' não deve ser nulo ou estar vazio";
    public static final String MSG_ERROR_CONFIRM_PASSWORD_SIZE = "O campo 'confirmPassword' deve possuir tamanho entre {min} e {max}";

    public static final String MSG_ERROR_EMAIL_NOT_NULL_OR_EMPTY = "O campo 'email' não deve ser nulo ou estar vazio";
    public static final String MSG_ERROR_EMAIL_INVALID = "O campo 'email' deve possuir um email válido";

    public static final String MSG_ERROR_CONFIRM_EMAIL_NOT_NULL_OR_EMPTY = "O campo 'confirmEmail' não deve ser nulo ou estar vazio";
    public static final String MSG_ERROR_CONFIRM_EMAIL_INVALID = "O campo 'confirmEmail' deve possuir um email válido";

    public static final String MSG_ERROR_PASSWORD_NOT_EQUALS = "Os campos de 'password' e 'confirmPassword' devem ser iguais";
    public static final String MSG_ERROR_EMAIL_NOT_EQUALS = "Os campos de 'password' e 'confirmEmail' devem ser iguais";

    @NotNull(message = MSG_ERROR_PASSWORD_NOT_NULL_OR_EMPTY)
    @Size(min = 8, max = 25, message = MSG_ERROR_PASSWORD_SIZE)
    private String password;

    @NotNull
    @NotNull(message = MSG_ERROR_CONFIRM_PASSWORD_NOT_NULL_OR_EMPTY)
    @Size(min = 8, max = 25, message = MSG_ERROR_CONFIRM_PASSWORD_SIZE)
    private String confirmPassword;

    @NotNull(message = MSG_ERROR_EMAIL_NOT_NULL_OR_EMPTY)
    @Email(message = MSG_ERROR_EMAIL_INVALID)
    private String email;

    @NotNull(message = MSG_ERROR_CONFIRM_EMAIL_NOT_NULL_OR_EMPTY)
    @Email(message = MSG_ERROR_CONFIRM_EMAIL_INVALID)
    private String confirmEmail;

}
