package com.jarierca.gamevault.resource.database;

import java.util.List;

import com.jarierca.gamevault.dto.database.StatsDTO;
import com.jarierca.gamevault.dto.database.VideogameDTO;
import com.jarierca.gamevault.service.database.DeveloperService;
import com.jarierca.gamevault.service.database.GenreService;
import com.jarierca.gamevault.service.database.PlatformService;
import com.jarierca.gamevault.service.database.PublisherService;
import com.jarierca.gamevault.service.database.VideogameService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StatisticsResource {

	@Inject
	VideogameService videogameService;

	@Inject
	PlatformService platformService;

	@Inject
	DeveloperService developerService;

	@Inject
	GenreService genreService;

	@Inject
	PublisherService publisherService;

	@GET
	@Path("/stats")
	public Response getStats() {
		StatsDTO stats = new StatsDTO(videogameService.countVideogames(), developerService.countDevelopers(),
				genreService.countGenres(), publisherService.countPublishers(), platformService.countPlatforms());
		return Response.ok(stats).build();
	}

	@GET
	@Path("/games/random")
	public Response getRandomGames(@QueryParam("limit") int limit) {
		List<VideogameDTO> randomGames = videogameService.getRandomGames(limit);
		return Response.ok(randomGames).build();
	}

	@GET
	@Path("/platforms/top")
	public Response getTopPlatforms(@QueryParam("limit") int limit) {
		return Response.ok(platformService.getTopPlatforms(limit)).build();
	}

	@GET
	@Path("/publishers/top")
	public Response getTopPublishers(@QueryParam("limit") int limit) {
		return Response.ok(publisherService.getTopPublishers(limit)).build();
	}

	@GET
	@Path("/developers/top")
	public Response getTopDevelopers(@QueryParam("limit") int limit) {
		return Response.ok(developerService.getTopDevelopers(limit)).build();
	}

	@GET
	@Path("/genres/top")
	public Response getTopGenres(@QueryParam("limit") int limit) {
		return Response.ok(genreService.getTopGenres(limit)).build();
	}
}
