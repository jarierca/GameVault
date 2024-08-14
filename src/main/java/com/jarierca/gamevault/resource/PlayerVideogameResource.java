package com.jarierca.gamevault.resource;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jarierca.gamevault.entity.PlayerVideogame;
import com.jarierca.gamevault.service.PlayerVideogameService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@Path("/player-videogames")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlayerVideogameResource {

	@Inject
	PlayerVideogameService playerVideogameService;

	@Inject
	JsonWebToken jwt;

	private static final Logger LOG = LoggerFactory.getLogger(VideogameResource.class);

	@GET
	public List<PlayerVideogame> getAllPlayerVideogames() {
		return playerVideogameService.listAll();
	}

	@GET
	@Path("/{id}")
	public PlayerVideogame getPlayerVideogame(@PathParam("id") Long id) {
		return playerVideogameService.findById(id);
	}

	@POST
	public Response addPlayerVideogame(PlayerVideogame playerVideogame) {
		playerVideogameService.addPlayerVideogame(playerVideogame);
		return Response.status(Response.Status.CREATED).build();
	}

	@PUT
	@Path("/{id}")
	public Response updatePlayerVideogame(PlayerVideogame playerVideogame) {
		playerVideogameService.updatePlayerVideogame(playerVideogame);
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	public Response deletePlayerVideogame(@PathParam("id") Long id) {
		playerVideogameService.deletePlayerVideogame(id);
		return Response.noContent().build();
	}

	@GET
	@Path("/mygames")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PlayerVideogame> getAllVideogamesByPlayer(@Context SecurityContext ctx) {
		LOG.info("Raw JWT Token: {}", jwt.getRawToken());

		String playerId = jwt.getClaim("sub");
		if (playerId != null) {
			return playerVideogameService.listByPlayerId(playerId.toString());
		} else {
			throw new RuntimeException("Player ID (sub) not found in JWT token");
		}
	}
}
