package com.jeanbarcellos.demo.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.jeanbarcellos.demo.dtos.UserCreateRequest;

@Path("/users")
@Tag(name = "Manutenção de Usuários")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @POST
    public Response insert(@RequestBody UserCreateRequest request) {
        request.validate();

        return Response.ok("").build();
    }

}
