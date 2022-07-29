package com.jeanbarcellos.demo.resources;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.jeanbarcellos.demo.dtos.ExistsResponse;
import com.jeanbarcellos.demo.services.TypeService;

@Path("/types")
@Tag(name = "Manutenção de Tipos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class TypeResource {

    @Inject
    protected TypeService service;

    @GET
    public Response list() {
        return Response.ok(this.service.list()).build();
    }

    @GET
    @Path("{id}")
    public Response exists(@PathParam("id") Integer id) {
        return Response.ok(ExistsResponse.of(this.service.exists(id))).build();
    }

    @GET
    @Path("/visibility")
    public Response visibility() {
        return Response.ok(this.service.visibility()).build();
    }

    @GET
    @Path("/people")
    public Response people() {
        return Response.ok(this.service.people()).build();
    }

}
