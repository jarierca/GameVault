package com.jarierca.gamevault.repository;

import com.jarierca.gamevault.entity.Genre;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GenreRepository implements PanacheRepository<Genre> {
    // Métodos de consulta personalizados, si es necesario
}
