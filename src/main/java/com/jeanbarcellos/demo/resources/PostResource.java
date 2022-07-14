package com.jeanbarcellos.demo.resources;

import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.jeanbarcellos.core.Constants;
import com.jeanbarcellos.core.exception.ValidationException;
import com.jeanbarcellos.demo.dtos.PostRequest;

@Path("/posts")
@Tag(name = "Manutenção de Posts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostResource {

    @Inject
    Validator validator;

    @POST
    public Response insert(@RequestBody PostRequest request) {
        request.validate();

        return Response.ok("").build();
    }

    protected void validate(PostRequest request) {
        Set<ConstraintViolation<PostRequest>> validateResult = validator.validate(request);

        if (!validateResult.isEmpty()) {
            throw new ValidationException(
                    Constants.MSG_ERROR_VALIDATION,
                    validateResult.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList()));
        }

    }

}
