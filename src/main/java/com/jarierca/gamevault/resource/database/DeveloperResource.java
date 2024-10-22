package com.jarierca.gamevault.resource.database;

import java.util.List;

import com.jarierca.gamevault.dto.communication.PageResponse;
import com.jarierca.gamevault.entity.database.Developer;
import com.jarierca.gamevault.service.database.DeveloperService;

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

@Path("/developers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DeveloperResource {

	@Inject
	DeveloperService developerService;

	@GET
	public PageResponse<Developer> getAllDevelopers(@QueryParam("page") int page, @QueryParam("size") int size) {
		List<Developer> developers = developerService.findAll(page, size);
		long totalElements = developerService.count();
		int totalPages = (int) Math.ceil((double) totalElements / size);

		return new PageResponse<>(developers, totalPages, totalElements);
	}

	@GET
	@Path("/{id}")
	public Developer getDeveloper(@PathParam("id") Long id) {
		return developerService.findById(id);
	}

	@POST
	public Response addDeveloper(Developer developer) {
		developerService.addDeveloper(developer);
		return Response.status(Response.Status.CREATED).build();
	}

	@PUT
	@Path("/{id}")
	public Response updateDeveloper(Developer developer) {
		developerService.updateDeveloper(developer);
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteDeveloper(@PathParam("id") Long id) {
		developerService.deleteDeveloper(id);
		return Response.noContent().build();
	}
}
