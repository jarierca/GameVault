package com.jarierca.gamevault.repository;

import com.jarierca.gamevault.entity.Developer;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeveloperRepository implements PanacheRepository<Developer> {
    // Métodos de consulta personalizados, si es necesario
}
