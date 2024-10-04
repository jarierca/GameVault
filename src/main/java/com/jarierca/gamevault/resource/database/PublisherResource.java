package com.jarierca.gamevault.resource.database;

import java.util.List;

import com.jarierca.gamevault.entity.database.Publisher;
import com.jarierca.gamevault.service.database.PublisherService;

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

@Path("/publishers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PublisherResource {

	@Inject
	PublisherService publisherService;

	@GET
	public List<Publisher> getAllPublishers() {
		return publisherService.listAll();
	}

	@GET
	@Path("/{id}")
	public Publisher getPublisher(@PathParam("id") Long id) {
		return publisherService.findById(id);
	}

	@POST
	public Response addPublisher(Publisher Publisher) {
		publisherService.addPublisher(Publisher);
		return Response.status(Response.Status.CREATED).build();
	}

	@PUT
	@Path("/{id}")
	public Response updatePublisher(Publisher Publisher) {
		publisherService.updatePublisher(Publisher);
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	public Response deletePublisher(@PathParam("id") Long id) {
		publisherService.deletePublisher(id);
		return Response.noContent().build();
	}
}
