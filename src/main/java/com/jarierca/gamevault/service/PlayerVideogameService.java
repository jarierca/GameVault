package com.jarierca.gamevault.service;

import java.util.List;

import com.jarierca.gamevault.entity.PlayerVideogame;
import com.jarierca.gamevault.repository.PlayerVideogameRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PlayerVideogameService {

    @Inject
    PlayerVideogameRepository playerVideogameRepository;

    public List<PlayerVideogame> listAll() {
        return playerVideogameRepository.listAll();
    }

    public PlayerVideogame findById(Long id) {
        return playerVideogameRepository.findById(id);
    }

    @Transactional
    public void addPlayerVideogame(PlayerVideogame playerVideogame) {
        playerVideogameRepository.persist(playerVideogame);
    }

    @Transactional
    public void updatePlayerVideogame(PlayerVideogame playerVideogame) {
        playerVideogameRepository.persist(playerVideogame);
    }

    @Transactional
    public void deletePlayerVideogame(Long id) {
        playerVideogameRepository.deleteById(id);
    }
}
