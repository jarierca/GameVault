package com.jarierca.gamevault.resource;

import java.util.List;

import com.jarierca.gamevault.entity.Player;
import com.jarierca.gamevault.service.PlayerService;

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

@Path("/players")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlayerResource {

    @Inject
    PlayerService playerService;

    @GET
    public List<Player> getAllPlayers() {
        return playerService.listAll();
    }

    @GET
    @Path("/{id}")
    public Player getPlayer(@PathParam("id") Long id) {
        return playerService.findById(id);
    }

    @POST
    public Response addPlayer(Player player) {
        playerService.addPlayer(player);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response updatePlayer(Player player) {
        playerService.updatePlayer(player);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePlayer(@PathParam("id") Long id) {
        playerService.deletePlayer(id);
        return Response.noContent().build();
    }
    
    @GET
    @Path("/{username}")
    public Player getPlayerByUsername(@PathParam("username") String username) {
        return playerService.getPlayerByUsername(username);
    }

    @PUT
    @Path("/{username}")
    public Response updatePlayer(@PathParam("username") String username, Player updatedPlayer) {
        Player existingPlayer = playerService.getPlayerByUsername(username);
        if (existingPlayer != null) {
            updatedPlayer.setId(existingPlayer.getId());
            playerService.updatePlayer(updatedPlayer);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{username}")
    public Response deletePlayer(@PathParam("username") String username) {
        Player playerToDelete = playerService.getPlayerByUsername(username);
        if (playerToDelete != null) {
            playerService.deletePlayer(playerToDelete.getId());
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
