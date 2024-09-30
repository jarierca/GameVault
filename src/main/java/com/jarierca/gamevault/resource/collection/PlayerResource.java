package com.jarierca.gamevault.resource.collection;

import java.util.List;

import com.jarierca.gamevault.entity.collection.Player;
import com.jarierca.gamevault.entity.collection.dto.AccountPlayerDTO;
import com.jarierca.gamevault.service.auth.AuthService;
import com.jarierca.gamevault.service.auth.PasswordService;
import com.jarierca.gamevault.service.collection.PlayerService;

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

@Path("/players")
@RolesAllowed({ "user", "admin" })
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlayerResource {

	@Inject
	PlayerService playerService;

	@Inject
	AuthService authService;

	@Inject
	PasswordService passwordService;

	@GET
	public List<Player> getAllPlayers() {
		return playerService.listAll();
	}

	@GET
	@Path("/{id}")
	public Player getPlayer(@PathParam("id") Long id) {
		Long playerId = authService.getAuthenticatedUserId();
		if (id.equals(playerId)) {
			return playerService.findById(id);
		}
		return null;
	}

	@POST
	public Response addPlayer(Player player) {
		playerService.addPlayer(player);
		return Response.status(Response.Status.CREATED).build();
	}

	@PUT
	@Path("/{id}")
	public Response updatePlayer(AccountPlayerDTO playerDTO) {
		Player player = playerDTO.getPlayer();
		Long playerId = authService.getAuthenticatedUserId();
		if (player.getId().equals(playerId)) {

			Player existingPlayer = playerService.findById(playerId);

			if (!passwordService.checkPassword(playerDTO.getCurrentPassword(), existingPlayer.getPassword())) {
				return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid current password").build();
			}

			player.copyProperties(existingPlayer, player);
			playerService.updatePlayer(existingPlayer);

			return Response.ok().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deletePlayer(AccountPlayerDTO playerDTO) {
		Long playerId = authService.getAuthenticatedUserId();
		
		if (playerDTO.getPlayer().getId().equals(playerId)) {
			Player existingPlayer = playerService.findById(playerId);
			
			if (!passwordService.checkPassword(playerDTO.getCurrentPassword(), existingPlayer.getPassword())) {
				return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid current password").build();
			}

			playerService.deletePlayer(playerId);

			return Response.noContent().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@GET
	@Path("/u/{username}")
	public Player getPlayerByUsername(@PathParam("username") String username) {
		return playerService.getPlayerByUsername(username);
	}

	@PUT
	@Path("/u/{username}")
	public Response updatePlayer(@PathParam("username") String username, Player updatedPlayer) {
		Long playerId = authService.getAuthenticatedUserId();

		Player existingPlayer = playerService.getPlayerByUsername(username);

		if (existingPlayer != null && existingPlayer.getId().equals(playerId)) {
			updatedPlayer.setId(existingPlayer.getId());

			playerService.updatePlayer(updatedPlayer);

			return Response.ok().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@DELETE
	@Path("/u/{username}")
	public Response deletePlayer(@PathParam("username") String username) {
		Long playerId = authService.getAuthenticatedUserId();

		Player playerToDelete = playerService.getPlayerByUsername(username);

		if (playerToDelete != null && playerToDelete.getId().equals(playerId)) {
			playerService.deletePlayer(playerToDelete.getId());

			return Response.noContent().build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
}
