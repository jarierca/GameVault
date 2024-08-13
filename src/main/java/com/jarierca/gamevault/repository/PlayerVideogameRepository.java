package com.jarierca.gamevault.repository;

import com.jarierca.gamevault.entity.PlayerVideogame;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlayerVideogameRepository implements PanacheRepository<PlayerVideogame> {
    // Métodos de consulta personalizados, si es necesario
}
