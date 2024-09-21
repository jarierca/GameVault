package com.jarierca.gamevault.resource.collection;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import com.jarierca.gamevault.entity.collection.GameCollection;
import com.jarierca.gamevault.service.collection.GameCollectionService;

import io.smallrye.jwt.build.JwtException;
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
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@Path("/my-collections")
@RolesAllowed({"user", "admin"}) 
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GameCollectionResource {

	@Inject
	JsonWebToken jwt;

	@Inject
	GameCollectionService collectionService;

	@GET
	public Response getAllCollectionsByPlayerId(@Context SecurityContext ctx) {
		String playerId = jwt.getSubject();

		if (playerId == null) {
			throw new JwtException("JWT not found");
		}

		List<GameCollection> collections = collectionService.findAllCollectionsByPlayerId(Long.parseLong(playerId));
		return Response.ok(collections).build();
	}

	@GET
	@Path("/{id}")
	public Response getCollection(@PathParam("id") Long id) {
		GameCollection collection = collectionService.findById(id);
		return collection != null ? Response.ok(collection).build()
				: Response.status(Response.Status.NOT_FOUND).build();
	}

	@POST
	public Response createCollection(@Context SecurityContext ctx, GameCollection collection) {
		String playerId = jwt.getSubject();

		if (playerId == null) {
			throw new JwtException("JWT not found");
		}

		GameCollection created = collectionService.create(collection, Long.parseLong(playerId));

		return Response.status(Response.Status.CREATED).entity(created).build();
	}

	@PUT
	@Path("/{id}")
	public Response updateCollection(@PathParam("id") Long id, GameCollection updatedCollection) {
		GameCollection updated = collectionService.update(id, updatedCollection);
		return updated != null ? Response.ok(updated).build() : Response.status(Response.Status.NOT_FOUND).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteCollection(@PathParam("id") Long id) {
		boolean deleted = collectionService.delete(id);
		if (deleted) {
			return Response.noContent().build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
}
