package com.jarierca.gamevault.resource.database;

import java.util.List;

import com.jarierca.gamevault.dto.communication.PageResponse;
import com.jarierca.gamevault.dto.database.GenreDTO;
import com.jarierca.gamevault.entity.database.Genre;
import com.jarierca.gamevault.service.database.GenreService;

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

@Path("/genres")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GenreResource {

	@Inject
	GenreService genreService;

	@GET
	public PageResponse<GenreDTO> getAllGenres(@QueryParam("page") int page, @QueryParam("size") int size) {
		List<GenreDTO> genres = genreService.findAll(page, size);
		long totalElements = genreService.count();
		int totalPages = (int) Math.ceil((double) totalElements / size);

		return new PageResponse<>(genres, totalPages, totalElements);
	}

	@GET
	@Path("/{id}")
	public Genre getGenre(@PathParam("id") Long id) {
		return genreService.findById(id);
	}

	@POST
	public Response addGenre(Genre genre) {
		genreService.addGenre(genre);
		return Response.status(Response.Status.CREATED).build();
	}

	@PUT
	@Path("/{id}")
	public Response updateGenre(Genre genre) {
		genreService.updateGenre(genre);
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteGenre(@PathParam("id") Long id) {
		genreService.deleteGenre(id);
		return Response.noContent().build();
	}
}
