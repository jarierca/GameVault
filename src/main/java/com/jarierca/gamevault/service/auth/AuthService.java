package com.jarierca.gamevault.service.auth;

import java.time.Duration;
import java.util.Set;

import org.eclipse.microprofile.jwt.JsonWebToken;

import com.jarierca.gamevault.dto.collection.AuthTokens;

import io.smallrye.jwt.auth.principal.JWTParser;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;

@ApplicationScoped
public class AuthService {

	@Context
	SecurityContext securityContext;

	@Inject
	JWTParser jwtParser;

	public String getAuthenticatedUser() {
		return securityContext.getUserPrincipal().getName();
	}

	public boolean isUserAuthenticated(String username) {
		return getAuthenticatedUser().equals(username);
	}

	public Long getAuthenticatedUserId() {
		JsonWebToken jwt = (JsonWebToken) securityContext.getUserPrincipal();
		return Long.valueOf(jwt.getSubject());
	}

	// Generate new token
	public String generateToken(String username, Long playerId, String role) {
		Set<String> roles = Set.of(role);

		return Jwt.issuer("dev-gamevault").upn(username).groups(roles).subject(String.valueOf(playerId))
				.claim("playerId", playerId).expiresIn(Duration.ofHours(1)).sign();
	}

	public AuthTokens generateTokens(String username, Long playerId, String role) {
		String accessToken = Jwt.issuer("dev-gamevault").upn(username).groups(Set.of(role))
				.subject(String.valueOf(playerId)).claim("playerId", playerId).expiresIn(Duration.ofHours(1)).sign();

		String refreshToken = Jwt.issuer("dev-gamevault").upn(username).expiresIn(Duration.ofDays(30)).sign();

		return new AuthTokens(accessToken, refreshToken);
	}

	// Validate a token
	public boolean validateToken(String token) {
		if (token == null) {
			token = getTokenFromSecurityContext();
		}
		try {
			JsonWebToken jwt = jwtParser.parse(token);
			return jwt.getExpirationTime() > (System.currentTimeMillis() / 1000);
		} catch (Exception e) {
			throw new JwtException("Token validation failed: " + e.getMessage(), e);
		}
	}

	// Get user ID from token
	public String getUserIdFromToken(String token) {
		if (token == null) {
			token = getTokenFromSecurityContext();
		}
		try {
			JsonWebToken jwt = jwtParser.parse(token);
			return jwt.getSubject();
		} catch (Exception e) {
			throw new JwtException("Failed to get user ID from token: " + e.getMessage(), e);
		}
	}

	// Get roles from token
	public Set<String> getRolesFromToken(String token) {
		if (token == null) {
			token = getTokenFromSecurityContext();
		}
		try {
			JsonWebToken jwt = jwtParser.parse(token);
			return jwt.getGroups();
		} catch (Exception e) {
			throw new JwtException("Failed to get roles from token: " + e.getMessage(), e);
		}
	}

	// Check if the token has expired
	public boolean isTokenExpired(String token) {
		if (token == null) {
			token = getTokenFromSecurityContext();
		}
		try {
			JsonWebToken jwt = jwtParser.parse(token);
			return jwt.getExpirationTime() <= (System.currentTimeMillis() / 1000);
		} catch (Exception e) {
			throw new JwtException("Failed to check token expiration: " + e.getMessage(), e);
		}
	}

	private String getTokenFromSecurityContext() {
		JsonWebToken jwt = (JsonWebToken) securityContext.getUserPrincipal();
		return jwt != null ? jwt.getRawToken() : null;
	}
}
