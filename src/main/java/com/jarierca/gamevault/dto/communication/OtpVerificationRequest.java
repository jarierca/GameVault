package com.jarierca.gamevault.dto.communication;

public class OtpVerificationRequest {
	private Long playerId;
	private String username;
	private String password;
	private String otp;

	public OtpVerificationRequest() {
	}

	public OtpVerificationRequest(Long playerId, String username, String password, String otp) {
		this.playerId = playerId;
		this.username = username;
		this.password = password;
		this.otp = otp;
	}

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