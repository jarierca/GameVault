package com.jarierca.gamevault.entity.collection.dto;

import com.jarierca.gamevault.entity.collection.Player;

public class AccountPlayerDTO {

	private String currentPassword;
	private Player player;
	

	public AccountPlayerDTO() {
		super();
	}

	public AccountPlayerDTO(Player player, String currentPassword) {
		super();
		this.player = player;
		this.currentPassword = currentPassword;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

}
