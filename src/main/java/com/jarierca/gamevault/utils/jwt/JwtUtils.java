package com.jarierca.gamevault.utils.jwt;

import java.time.Duration;
import java.util.Set;

import org.eclipse.microprofile.jwt.JsonWebToken;

import io.smallrye.jwt.auth.principal.JWTParser;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class JwtUtils {

	@Inject
	JWTParser jwtParser;

	// Generate new token
	public String generateToken(String username, Long userId, Set<String> roles, String issuer, Duration duration) {
		return Jwt.issuer(issuer).upn(username).subject(String.valueOf(userId)).groups(roles).expiresIn(duration)
				.sign();
	}

	// Check a valid token
	public boolean validateToken(String token) {
		try {
			JsonWebToken jwt = jwtParser.parse(token);
			return jwt.getExpirationTime() > (System.currentTimeMillis() / 1000);
		} catch (Exception e) {
			throw new JwtException("Token validation failed: " + e.getMessage(), e);
		}
	}

	// Get token user ID
	public String getUserIdFromToken(String token) {
		try {
			JsonWebToken jwt = jwtParser.parse(token);
			return jwt.getSubject();
		} catch (Exception e) {
			throw new JwtException("Failed to get user ID from token: " + e.getMessage(), e);
		}
	}

	// Get all token roles
	public Set<String> getRolesFromToken(String token) {
		try {
			JsonWebToken jwt = jwtParser.parse(token);
			return jwt.getGroups();
		} catch (Exception e) {
			throw new JwtException("Failed to get roles from token: " + e.getMessage(), e);
		}
	}

	// Check if the token has expired
	public boolean isTokenExpired(String token) {
		try {
			JsonWebToken jwt = jwtParser.parse(token);
			return jwt.getExpirationTime() <= (System.currentTimeMillis() / 1000);
		} catch (Exception e) {
			throw new JwtException("Failed to check token expiration: " + e.getMessage(), e);
		}
	}
}
