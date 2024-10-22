package com.jarierca.gamevault.resource.database;

import java.util.List;

import com.jarierca.gamevault.dto.database.ImageDTO;
import com.jarierca.gamevault.entity.database.Developer;
import com.jarierca.gamevault.entity.database.Genre;
import com.jarierca.gamevault.entity.database.Platform;
import com.jarierca.gamevault.entity.database.Publisher;
import com.jarierca.gamevault.entity.database.Videogame;
import com.jarierca.gamevault.service.database.ImageService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/images")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ImageResource {

	@Inject
	ImageService imageService;

	@GET
	@Path("/videogames/{videogameId}/image/{imageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ImageDTO getImageByVideogameId(@PathParam("videogameId") Long videogameId,
			@PathParam("imageId") Long imageId) {
		return imageService.findImageByVideogameIdAndImageId(videogameId, imageId);
	}

	@GET
	@Path("/videogames/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ImageDTO> getImagesByVideogameId(@PathParam("id") Long videogameId) {
		return imageService.findImagesByField(Videogame.class.getSimpleName().toLowerCase(), videogameId);
	}

	@GET
	@Path("/platforms/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ImageDTO> getImagesByPlatformId(@PathParam("id") Long platformId) {
		return imageService.findImagesByField(Platform.class.getSimpleName().toLowerCase(), platformId);
	}

	@GET
	@Path("/developers/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ImageDTO> getImagesByDeveloperId(@PathParam("id") Long developerId) {
		return imageService.findImagesByField(Developer.class.getSimpleName().toLowerCase(), developerId);
	}

	@GET
	@Path("/publishers/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ImageDTO> getImagesByPublisherId(@PathParam("id") Long publisherId) {
		return imageService.findImagesByField(Publisher.class.getSimpleName().toLowerCase(), publisherId);
	}

	@GET
	@Path("/genres/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ImageDTO> getImagesByGenreId(@PathParam("id") Long genreId) {
		return imageService.findImagesByField(Genre.class.getSimpleName().toLowerCase(), genreId);
	}

}
