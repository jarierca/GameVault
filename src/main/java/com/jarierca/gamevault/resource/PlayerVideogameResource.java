package com.jarierca.gamevault.resource;

import java.util.List;

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
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/player-videogames")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlayerVideogameResource {

    @Inject
    PlayerVideogameService playerVideogameService;

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
}
