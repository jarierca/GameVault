package com.jarierca.gamevault.resource.database;

import java.util.List;

import com.jarierca.gamevault.dto.communication.PageResponse;
import com.jarierca.gamevault.entity.database.Platform;
import com.jarierca.gamevault.entity.database.Publisher;
import com.jarierca.gamevault.service.database.PlatformService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/platforms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlatformResource {

    @Inject
    PlatformService platformService;

	@GET
	public PageResponse<Platform> getAllPlatforms(@QueryParam("page") int page, @QueryParam("size") int size) {
		List<Platform> platforms = platformService.findAll(page, size);
		long totalElements = platformService.count();
		int totalPages = (int) Math.ceil((double) totalElements / size);

		return new PageResponse<>(platforms, totalPages, totalElements);
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
