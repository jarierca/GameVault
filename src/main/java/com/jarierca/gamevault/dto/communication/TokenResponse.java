package com.jarierca.gamevault.dto.communication;

public class TokenResponse {
	private String token;
	private String refreshToken;

	public TokenResponse() {
	}

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