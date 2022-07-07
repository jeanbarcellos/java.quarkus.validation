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
import com.jeanbarcellos.demo.dtos.PostRequest;

@Path("/posts")
@Tag(name = "Manutenção de Posts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostResource {

    @POST
    public Response insert(@RequestBody PostRequest request) {
        Validator.validateWithThrowException(request);

        return Response.ok("").build();
    }

}
