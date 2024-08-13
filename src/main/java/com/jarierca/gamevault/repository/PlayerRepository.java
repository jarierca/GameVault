package com.jarierca.gamevault.repository;

import com.jarierca.gamevault.entity.Player;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlayerRepository implements PanacheRepository<Player> {

    public Player findByUsername(String username) {
        return find("username", username).firstResult();
    }

}
