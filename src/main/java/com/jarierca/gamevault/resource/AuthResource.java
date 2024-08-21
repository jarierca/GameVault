package com.jarierca.gamevault.resource;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jarierca.gamevault.entity.Player;
import com.jarierca.gamevault.repository.PlayerRepository;
import com.jarierca.gamevault.service.PasswordService;

import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/auth")
public class AuthResource {

	@Inject
	PlayerRepository playerRepository;

	@Inject
	PasswordService passwordService;

	private static final Logger LOG = LoggerFactory.getLogger(AuthResource.class);

	@POST
	@Path("/login")
	@Consumes("application/json")
	public Response login(Player playerCredentials) {
		Player player = playerRepository.findByUsername(playerCredentials.getUsername());
		if (player != null && passwordService.checkPassword(playerCredentials.getPassword(), player.getPassword())) {
			Set<String> roles = new HashSet<>();
			roles.add(player.getRole());

			String token = Jwt.issuer("dev-gamevault").upn(playerCredentials.getUsername()).groups(roles)
					.subject(String.valueOf(player.getId())).expiresIn(Duration.ofHours(1)).sign();

			LOG.info("JWT Token generated for user: {}", playerCredentials.getUsername());

			return Response.ok(new TokenResponse(token)).build();
		}
		return Response.status(Response.Status.UNAUTHORIZED).build();
	}

	@POST
	@Path("/register")
	@Consumes("application/json")
	public Response register(Player playerCredentials) {
		if (playerRepository.findByUsername(playerCredentials.getUsername()) != null) {
			return Response.status(Response.Status.CONFLICT).entity("Username already exists").build();
		}

		String hashedPassword = passwordService.hashPassword(playerCredentials.getPassword());
		Player newPlayer = playerCredentials;
		newPlayer.setPassword(hashedPassword);

		playerRepository.save(newPlayer);

		LOG.info("New player registered with username: {}", playerCredentials.getUsername());

		return Response.status(Response.Status.CREATED).build();
	}

	public static class TokenResponse {
		private String token;

		public TokenResponse(String token) {
			this.token = token;
		}

		public String getToken() {
			return token;
		}
	}
}
