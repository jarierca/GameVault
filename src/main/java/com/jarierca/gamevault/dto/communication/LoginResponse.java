package com.jarierca.gamevault.dto.communication;

public class LoginResponse {
	private String message;
	private Long playerId;

	public LoginResponse() {
	}

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