package com.jarierca.gamevault.resource.collection;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import com.jarierca.gamevault.entity.collection.GameCollection;
import com.jarierca.gamevault.service.collection.GameCollectionService;

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

@Path("/my-collections")
@RolesAllowed({ "user", "admin" })
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GameCollectionResource {

	@Inject
	JsonWebToken jwt;

	@Inject
	GameCollectionService collectionService;

	@GET
	public Response getAllCollectionsByPlayerId() {

		List<GameCollection> collections = collectionService.findAllCollectionsByPlayerId();

		return Response.ok(collections).build();
	}

	@GET
	@Path("/{id}")
	public Response getCollection(@PathParam("id") Long collectionId) {
		GameCollection collection = collectionService.findByPlayerIdAndCollectionId(collectionId);

		return collection != null ? Response.ok(collection).build()
				: Response.status(Response.Status.NOT_FOUND).build();
	}

	@POST
	public Response createCollection(GameCollection collection) {
		GameCollection created = collectionService.create(collection);

		return Response.status(Response.Status.CREATED).entity(created).build();
	}

	@PUT
	@Path("/{id}")
	public Response updateCollection(@PathParam("id") Long collectionId, GameCollection updatedCollection) {
		if (updatedCollection == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}

		try {
			collectionService.validatePlayerCollection(collectionId);
		} catch (IllegalArgumentException e) {
			return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
		}

		GameCollection updated = collectionService.update(collectionId, updatedCollection);

		return updated != null ? Response.ok(updated).build() : Response.status(Response.Status.NOT_FOUND).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteCollection(@PathParam("id") Long collectionId) {

		try {
			collectionService.validatePlayerCollection(collectionId);
		} catch (IllegalArgumentException e) {
			return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
		}

		boolean deleted = collectionService.delete(collectionId);

		if (deleted) {
			return Response.noContent().build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
}
