package com.jarierca.gamevault.resource.database;

import java.util.List;

import com.jarierca.gamevault.entity.database.Videogame;
import com.jarierca.gamevault.service.database.VideogameService;

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
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/videogames")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VideogameResource {

	@Inject
	VideogameService videogameService;

	@GET
	public List<Videogame> getAllVideogames() {
		return videogameService.listAll();
	}

	@GET
    @Path("/{id}")
    public Response getVideogameById(@PathParam("id") Long id) {
        Videogame videogame = videogameService.findById(id);
        if (videogame != null) {
            return Response.ok(videogame).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

	@GET
	@Path("/platform/{platformId}")
	public List<Videogame> getVideogamesByPlatform(@PathParam("platformId") Long platformId) {
		return videogameService.findByPlatformId(platformId);
	}
	
	@GET
	@Path("/genre/{genreId}")
	public List<Videogame> getVideogamesByGenre(@PathParam("genreId") Long genreId) {
		return videogameService.findByGenreId(genreId);
	}
	
	@GET
	@Path("/search")
	public Response searchVideogamesByTitle(@QueryParam("title") String title) {
	    List<Videogame> results = videogameService.searchByTitle(title);
	    if (results.isEmpty()) {
	        return Response.status(Response.Status.NOT_FOUND).build();
//	        return Response.ok("No results found").build();
	    } else {
	        return Response.ok(results).build();
	    }
	}

	@RolesAllowed("admin")
	@POST
	@Path("/add")
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
