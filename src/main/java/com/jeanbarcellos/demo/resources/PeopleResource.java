package com.jeanbarcellos.demo.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.jeanbarcellos.core.Validator;
import com.jeanbarcellos.demo.dtos.PeopleRequest;

@Path("/people")
@Tag(name = "Manutenção de Pessoa")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PeopleResource {

    @POST
    public Response insert(@RequestBody PeopleRequest request) {
        Validator.validateWithThrowException(request);

        return Response.ok("").build();
    }

}
