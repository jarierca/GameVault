package com.jarierca.gamevault.resource.auth;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jarierca.gamevault.dto.collection.AuthTokens;
import com.jarierca.gamevault.entity.collection.Player;
import com.jarierca.gamevault.repository.collection.PlayerRepository;
import com.jarierca.gamevault.service.auth.AuthService;
import com.jarierca.gamevault.service.auth.OTPService;
import com.jarierca.gamevault.service.auth.PasswordService;
import com.jarierca.gamevault.service.collection.PlayerService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
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

	@Inject
	PlayerService playerService;

	private static final Logger LOG = LoggerFactory.getLogger(AuthResource.class);

	private static final int MAX_ATTEMPTS = 3;
	private static final long BLOCK_TIME = 5; // Minutes

	@POST
	@Path("/login")
	@Consumes("application/json")
	public Response login(LoginRequest request) {
		Player player = playerRepository.findByUsername(request.getUsername());
		if (player == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Player not found").build();
		}

		if (player.getFailedLoginAttempts() != null && player.getFailedLoginAttempts() >= MAX_ATTEMPTS) {
			LocalDateTime lastAttempt = player.getLastLoginAttempt();
			LocalDateTime blockEndTime = lastAttempt.plusMinutes(BLOCK_TIME);
			if (LocalDateTime.now().isBefore(blockEndTime)) {
				return Response.status(Response.Status.FORBIDDEN)
						.entity("Account is temporarily locked. Try again after " + BLOCK_TIME + " minutes.").build();
			} else {
				player.setFailedLoginAttempts(0);
			}
		}

		if (!passwordService.checkPassword(request.getPassword(), player.getPassword())) {
			player.setFailedLoginAttempts(player.getFailedLoginAttempts() + 1);
			player.setLastLoginAttempt(LocalDateTime.now());
			playerRepository.save(player);
			return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid credentials").build();
		}

		if (player.isOtpEnabled()) {
			return Response.ok(new LoginResponse("OTP_REQUIRED", player.getId())).build();
		}

		player.setFailedLoginAttempts(0);
		player.setLastLoginAttempt(LocalDateTime.now());
		playerRepository.save(player);

		return Response.ok(generateTokenResponse(player).getEntity()).build();
	}

	@POST
	@Path("/login-otp")
	@Consumes("application/json")
	@Produces("application/json")
	public Response verifyOtp(OtpVerificationRequest request) {
		Player player = playerRepository.findByUsername(request.getUsername());
		if (player == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Player not found").build();
		}

		if (player.getFailedLoginAttempts() != null && player.getFailedLoginAttempts() >= MAX_ATTEMPTS) {
			LocalDateTime lastAttempt = player.getLastLoginAttempt();
			LocalDateTime blockEndTime = lastAttempt.plusMinutes(BLOCK_TIME);
			if (LocalDateTime.now().isBefore(blockEndTime)) {
				return Response.status(Response.Status.FORBIDDEN)
						.entity("Account is temporarily locked. Try again after " + BLOCK_TIME + " minutes.").build();
			} else {
				player.setFailedLoginAttempts(0);
			}
		}

		try {
			boolean isValidOtp = otpService.verifyOTP(player.getOtpSecret(), request.getOtp());
			if (!isValidOtp) {
				player.setFailedLoginAttempts(player.getFailedLoginAttempts() + 1);
				player.setLastLoginAttempt(LocalDateTime.now());
				playerRepository.save(player);
				return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid OTP").build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error validating OTP").build();
		}

		player.setFailedLoginAttempts(0);
		player.setLastLoginAttempt(LocalDateTime.now());
		playerRepository.save(player);

		return Response.ok(generateTokenResponse(player).getEntity()).build();
	}

	@PUT
	@Path("/disabled-otp")
	public Response disabledOTP(OtpVerificationRequest request) {
		Long playerId = authService.getAuthenticatedUserId();
		Player existingPlayer = playerService.findById(playerId);

		if (!passwordService.checkPassword(request.getPassword(), existingPlayer.getPassword())) {
			return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid current password").build();
		}

		try {
			boolean isValidOtp = otpService.verifyOTP(existingPlayer.getOtpSecret(), request.getOtp());
			if (isValidOtp) {
				existingPlayer.setOtpEnabled(false);
				existingPlayer.setOtpSecret("");
				playerService.updatePlayer(existingPlayer);
				return Response.ok().build();
			} else {
				return Response.status(Response.Status.UNAUTHORIZED).entity("Error validating OTP").build();
			}

		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error validating OTP").build();
		}
	}

	private Response generateTokenResponse(Player player) {
		AuthTokens authTokens = authService.generateTokens(player.getUsername(), player.getId(), player.getRole());

		LOG.info("JWT Token generated for user: {}", player.getUsername());
		LOG.info("Generated JWT Access Token: {}", authTokens.getAccessToken());
		LOG.info("Generated JWT Refresh Token: {}", authTokens.getRefreshToken());

		return Response.ok(new TokenResponse(authTokens.getAccessToken(), authTokens.getRefreshToken())).build();
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

	@POST
	@Path("/refresh")
	public Response refreshToken(RefreshTokenRequest request) {
		String refreshToken = request.getRefreshToken();

		Player player = playerRepository.findById(authService.getAuthenticatedUserId());
		if (player == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Player not found").build();
		}

		String newAccessToken = authService.generateToken(player.getUsername(), player.getId(), player.getRole());

		return Response.ok(new TokenResponse(newAccessToken, refreshToken)).build();
	}

	public static class TokenResponse {
		private String token;
		private String refreshToken;

		public TokenResponse(String token, String refreshToken) {
			this.token = token;
			this.refreshToken = refreshToken;
		}

		public String getToken() {
			return token;
		}

		public String getRefreshToken() {
			return refreshToken;
		}
	}

	public static class RefreshTokenRequest {
		private String refreshToken;

		public RefreshTokenRequest(String refreshToken) {
			this.refreshToken = refreshToken;
		}

		public String getRefreshToken() {
			return refreshToken;
		}
	}

	public static class LoginRequest {
		private String message;
		private Long playerId;

		private String username;
		private String password;

		public LoginRequest(String message, Long playerId) {
			this.message = message;
			this.playerId = playerId;
		}

		public String getMessage() {
			return message;
		}

		public Long getPlayerId() {
			return playerId;
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
		private String username;
		private String password;
		private String otp;

		public Long getPlayerId() {
			return playerId;
		}

		public void setPlayerId(Long playerId) {
			this.playerId = playerId;
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

		public String getOtp() {
			return otp;
		}

		public void setOtp(String otp) {
			this.otp = otp;
		}

	}
}
