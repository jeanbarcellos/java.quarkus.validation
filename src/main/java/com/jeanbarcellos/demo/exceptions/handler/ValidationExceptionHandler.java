package com.jeanbarcellos.demo.exceptions.handler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.jeanbarcellos.core.dto.ErrorListResponse;
import com.jeanbarcellos.core.dto.ErrorResponse;
import com.jeanbarcellos.core.exception.ValidationException;

@Provider
public class ValidationExceptionHandler implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(ValidationException exception) {

        var response = exception.hasErrors()
                ? new ErrorListResponse(Response.Status.BAD_REQUEST.getStatusCode(), exception.getMessage(), exception.getErrors())
                : new ErrorResponse(Response.Status.BAD_REQUEST.getStatusCode(), exception.getMessage());

        return Response.status(response.getStatus())
                .entity(response)
                .build();
    }

}
