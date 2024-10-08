package com.jarierca.gamevault.entity.collection;

import java.time.LocalDateTime;

import com.jarierca.gamevault.service.auth.PasswordService;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Player extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;
	private String email;
	private String password;
	private String role;
	private String otpSecret;
	@Column(nullable = false, columnDefinition = "boolean default false")
	private Boolean otpEnabled = false;

	private Integer failedLoginAttempts;
	private LocalDateTime lastLoginAttempt;

	public Player() {
	}

	public Player(String username, String email, String password, String role) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getOtpSecret() {
		return otpSecret;
	}

	public void setOtpSecret(String otpSecret) {
		this.otpSecret = otpSecret;
	}

	public boolean isOtpEnabled() {
		return otpEnabled;
	}

	public void setOtpEnabled(boolean otpEnabled) {
		this.otpEnabled = otpEnabled;
	}

	public Integer getFailedLoginAttempts() {
		return failedLoginAttempts;
	}

	public void setFailedLoginAttempts(Integer failedLoginAttempts) {
		this.failedLoginAttempts = failedLoginAttempts;
	}

	public LocalDateTime getLastLoginAttempt() {
		return lastLoginAttempt;
	}

	public void setLastLoginAttempt(LocalDateTime lastLoginAttempt) {
		this.lastLoginAttempt = lastLoginAttempt;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Player player = (Player) o;

		return id != null ? id.equals(player.id) : player.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	public void copyProperties(Player existingPlayer, Player player) {

		if (!player.getEmail().isBlank() && !player.getEmail().equals(existingPlayer.getEmail())) {
			existingPlayer.setEmail(player.getEmail());
		}

		if (!player.getPassword().isBlank()) {
			PasswordService passwordService = new PasswordService();
			String hashedPassword = passwordService.hashPassword(player.getPassword());
			existingPlayer.setPassword(hashedPassword);
		}
	}
}
