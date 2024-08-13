package com.jarierca.gamevault.resource;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jarierca.gamevault.entity.Player;
import com.jarierca.gamevault.repository.PlayerRepository;

import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/auth")
public class AuthResource {

	@Inject
	PlayerRepository playerRepository;

	private static final Logger LOG = LoggerFactory.getLogger(VideogameResource.class);

	@POST
	@Path("/login")
	public Response login(UserCredentials credentials) {
		Player player = playerRepository.findByUsername(credentials.getUsername());
		if (player != null && player.getPassword().equals(credentials.getPassword())) {
			Set<String> roles = new HashSet<>();
			roles.add(player.getRole());

			String token = Jwt.issuer("your-issuer").upn(credentials.getUsername()).groups(roles)
					.subject(String.valueOf(player.getId())).expiresIn(Duration.ofHours(1)).sign();

			LOG.info("JWT Token: {}", token);

			return Response.ok(new TokenResponse(token)).build();
		}
		return Response.status(Response.Status.UNAUTHORIZED).build();
	}

	public static class UserCredentials {
		private String username;
		private String password;

		public UserCredentials() {
		}

		public UserCredentials(String username, String password) {
			this.username = username;
			this.password = password;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
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
