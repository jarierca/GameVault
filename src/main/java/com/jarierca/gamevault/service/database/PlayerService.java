package com.jarierca.gamevault.service.database;

import java.util.List;

import com.jarierca.gamevault.entity.database.Player;
import com.jarierca.gamevault.repository.database.PlayerRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PlayerService {

	@Inject
	PlayerRepository playerRepository;

	public List<Player> listAll() {
		return playerRepository.listAll();
	}

	public Player findById(Long id) {
		return playerRepository.findById(id);
	}
	
	public Player getPlayerByUsername(String username) {
		return Player.find("username", username).firstResult();
	}

	@Transactional
	public void addPlayer(Player player) {
		playerRepository.persist(player);
	}

	@Transactional
	public void updatePlayer(Player player) {
		playerRepository.getEntityManager().merge(player);
	}

	@Transactional
	public void deletePlayer(Long id) {
		playerRepository.deleteById(id);
	}

	@Transactional
	public void deletePlayerByUsername(String username) {
		Player player = getPlayerByUsername(username);
		if (player != null) {
			deletePlayer(player.getId());
		}
	}
}
