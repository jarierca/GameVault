package com.jarierca.gamevault.dto.communication;

public class LoginRequest {
	private String message;
	private Long playerId;

	private String username;
	private String password;

	public LoginRequest() {
	}

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