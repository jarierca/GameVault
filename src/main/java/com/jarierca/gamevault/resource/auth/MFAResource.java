package com.jarierca.gamevault.resource.auth;

import com.jarierca.gamevault.entity.collection.Player;
import com.jarierca.gamevault.service.auth.AuthService;
import com.jarierca.gamevault.service.auth.OTPService;
import com.jarierca.gamevault.service.collection.PlayerService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/mfa")
@Consumes("application/json")
@Produces("application/json")
public class MFAResource {

	@Inject
	OTPService otpService;

	@Inject
	PlayerService playerService;

	@Inject
	AuthService authService;

	@POST
	@Path("/enable-otp")
	public Response enableOtp() {
		Long playerId = authService.getAuthenticatedUserId();

		Player player = playerService.findById(playerId);
		if (player == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Player not found").build();
		}

		if (player.isOtpEnabled()) {
			return Response.status(Response.Status.NOT_FOUND).entity("OTP is already actived.").build();
		}

		try {
			String secretKey = otpService.generateSecretKey();
			player.setOtpSecret(secretKey);
			player.setOtpEnabled(false);
			playerService.updatePlayer(player);

			String uri = otpService.generateQrCodeDataUrl(player.getUsername(), secretKey);

			return Response.ok(uri).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error enabling OTP").build();
		}
	}

	@POST
	@Path("/verify-otp/{otp}")
	public Response verifyOtp(@PathParam("otp") String otp) {
		Long playerId = authService.getAuthenticatedUserId();

		Player player = playerService.findById(playerId);
		if (player == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Player not found").build();
		}

		try {
			boolean isValid = otpService.verifyOTP(player.getOtpSecret(), otp);
			if (isValid) {
				player.setOtpEnabled(true);
				playerService.updatePlayer(player);

				return Response.ok("OTP verified successfully").build();
			} else {
				return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid OTP").build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Error verifying OTP: " + e.getMessage()).build();
		}
	}
}
