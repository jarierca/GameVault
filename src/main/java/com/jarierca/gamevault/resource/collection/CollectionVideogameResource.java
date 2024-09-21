package com.jarierca.gamevault.resource.collection;

import java.util.List;
import java.util.Map;

import com.jarierca.gamevault.entity.collection.CollectionVideogame;
import com.jarierca.gamevault.service.collection.CollectionVideogameService;

import jakarta.annotation.security.RolesAllowed;
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

@Path("/collection-videogames")
@RolesAllowed({"user", "admin"}) 
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CollectionVideogameResource {

	@Inject
	CollectionVideogameService collectionVideogameService;

	@GET
	public Response getAllCollectionVideogames() {
		List<CollectionVideogame> collectionVideogames = collectionVideogameService.findAll();
		return Response.ok(collectionVideogames).build();
	}

	@GET
	@Path("/{id}")
	public Response getCollectionVideogame(@PathParam("id") Long id) {
		CollectionVideogame collectionVideogame = collectionVideogameService.findById(id);
		return collectionVideogame != null ? Response.ok(collectionVideogame).build()
				: Response.status(Response.Status.NOT_FOUND).build();
	}

	@POST
	@Path("/{collectionId}/add-game")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addGameToCollection(@PathParam("collectionId") Long collectionId, Map<String, Long> body) {
		Long gameId = body.get("gameId");
		CollectionVideogame addedGame = collectionVideogameService.addGameToCollection(collectionId, gameId);
		return addedGame != null ? Response.ok(addedGame).build()
				: Response.status(Response.Status.BAD_REQUEST).build();
	}

	@GET
	@Path("{collectionId}/videogames")
	public Response getCollectionVideogameByCollectionId(@PathParam("collectionId") Long collectionId) {
		List<CollectionVideogame> games = collectionVideogameService.getCollectionVideogameByCollectionId(collectionId);
		return Response.ok(games).build();
	}

	@PUT
	@Path("/{id}")
	public Response updateCollectionVideogame(@PathParam("id") Long id, CollectionVideogame collectionVideogame) {
		collectionVideogame.setId(id);
		CollectionVideogame updated = collectionVideogameService.update(collectionVideogame);
		return updated != null ? Response.ok(updated).build() : Response.status(Response.Status.NOT_FOUND).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteCollectionVideogame(@PathParam("id") Long id) {
		boolean deleted = collectionVideogameService.delete(id);
		return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
	}
}
