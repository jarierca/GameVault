package com.jarierca.gamevault.resource;

import java.util.List;

import com.jarierca.gamevault.entity.Platform;
import com.jarierca.gamevault.service.PlatformService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/platforms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlatformResource {

    @Inject
    PlatformService platformService;

    @GET
    public List<Platform> getAllPlatforms() {
        return platformService.listAll();
    }

    @GET
    @Path("/{id}")
    public Platform getPlatform(@PathParam("id") Long id) {
        return platformService.findById(id);
    }

    @POST
    public Response addPlatform(Platform platform) {
        platformService.addPlatform(platform);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response updatePlatform(Platform platform) {
        platformService.updatePlatform(platform);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePlatform(@PathParam("id") Long id) {
        platformService.deletePlatform(id);
        return Response.noContent().build();
    }
}
