package com.jeanbarcellos.demo.resources;

import java.util.UUID;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.jeanbarcellos.demo.dtos.CategoryRequest;
import com.jeanbarcellos.demo.dtos.ExistsResponse;
import com.jeanbarcellos.demo.services.CategoryService;

@Path("/categories")
@Tag(name = "Manutenção de Categorias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class CategoryResource {

    @Inject
    private CategoryService service;

    @GET
    public Response list() {
        return Response.ok(this.service.list()).build();
    }

    @GET
    @Path("{id}")
    public Response exists(@PathParam("id") UUID id) {
        return Response.ok(ExistsResponse.of(this.service.exists(id))).build();
    }

    @POST
    public Response insert(@RequestBody CategoryRequest request) {
        return Response.ok(this.service.insert(request)).build();
    }

}
