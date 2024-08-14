package com.jarierca.gamevault.resource;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jarierca.gamevault.entity.Videogame;
import com.jarierca.gamevault.service.VideogameService;

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

@Path("/videogames")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VideogameResource {

	@Inject
	VideogameService videogameService;

	private static final Logger LOG = LoggerFactory.getLogger(VideogameResource.class);

	@GET
	public List<Videogame> getAllVideogames() {
		return videogameService.listAll();
	}

	@GET
	@Path("/{id}")
	public Videogame getVideogame(@PathParam("id") Long id) {
		return videogameService.findById(id);
	}

	@POST
	public Response addVideogame(Videogame videogame) {
		videogameService.addVideogame(videogame);
		return Response.status(Response.Status.CREATED).build();
	}

	@PUT
	@Path("/{id}")
	public Response updateVideogame(Videogame videogame) {
		videogameService.updateVideogame(videogame);
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteVideogame(@PathParam("id") Long id) {
		videogameService.deleteVideogame(id);
		return Response.noContent().build();
	}
}
