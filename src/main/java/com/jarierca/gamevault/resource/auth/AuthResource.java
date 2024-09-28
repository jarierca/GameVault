package com.jarierca.gamevault.resource.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jarierca.gamevault.entity.collection.Player;
import com.jarierca.gamevault.repository.collection.PlayerRepository;
import com.jarierca.gamevault.service.auth.AuthService;
import com.jarierca.gamevault.service.auth.OTPService;
import com.jarierca.gamevault.service.auth.PasswordService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/auth")
public class AuthResource {

	@Inject
	PlayerRepository playerRepository;

	@Inject
	PasswordService passwordService;

	@Inject
	AuthService authService;

	@Inject
	OTPService otpService;

	private static final Logger LOG = LoggerFactory.getLogger(AuthResource.class);

	@POST
	@Path("/login")
	@Consumes("application/json")
	public Response login(Player playerCredentials) {
		Player player = playerRepository.findByUsername(playerCredentials.getUsername());
		if (player != null && passwordService.checkPassword(playerCredentials.getPassword(), player.getPassword())) {
			if (player.isOtpEnabled()) {
				return Response.ok(new LoginResponse("OTP_REQUIRED", player.getId())).build();
			}

			return generateTokenResponse(player);
		}
		return Response.status(Response.Status.UNAUTHORIZED).build();
	}

	@POST
	@Path("/login-otp")
	@Consumes("application/json")
	@Produces("application/json")
	public Response verifyOtp(OtpVerificationRequest request) {
		Player player = playerRepository.findById(request.getPlayerId());
		if (player == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Player not found").build();
		}

		try {
			boolean isValidOtp = otpService.verifyOTP(player.getOtpSecret(), request.getOtp());
			if (!isValidOtp) {
				return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid OTP").build();
			}

			return Response.ok(generateTokenResponse(player).getEntity()).build();

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error validating OTP").build();
		}
	}

	private Response generateTokenResponse(Player player) {
		String token = authService.generateToken(player.getUsername(), player.getId(), player.getRole());

		LOG.info("JWT Token generated for user: {}", player.getUsername());
		LOG.info("Generated JWT Token: {}", token);

		return Response.ok(new TokenResponse(token)).build();
	}

	@POST
	@Path("/register")
	@Consumes("application/json")
	@Produces("application/json")
	public Response register(Player playerCredentials) {
		if (playerRepository.findByUsername(playerCredentials.getUsername()) != null) {
			return Response.status(Response.Status.CONFLICT).entity("Username already exists").build();
		}

		String hashedPassword = passwordService.hashPassword(playerCredentials.getPassword());
		playerCredentials.setPassword(hashedPassword);

		playerRepository.save(playerCredentials);

		LOG.info("New player registered with username: {}", playerCredentials.getUsername());

		return Response.status(Response.Status.CREATED).entity(playerCredentials.getId()).build();
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

	public static class LoginResponse {
		private String message;
		private Long playerId;

		public LoginResponse(String message, Long playerId) {
			this.message = message;
			this.playerId = playerId;
		}

		public String getMessage() {
			return message;
		}

		public Long getPlayerId() {
			return playerId;
		}
	}

	public static class OtpVerificationRequest {
		private Long playerId;
		private String otp;

		public Long getPlayerId() {
			return playerId;
		}

		public void setPlayerId(Long playerId) {
			this.playerId = playerId;
		}

		public String getOtp() {
			return otp;
		}

		public void setOtp(String otp) {
			this.otp = otp;
		}
	}
}
