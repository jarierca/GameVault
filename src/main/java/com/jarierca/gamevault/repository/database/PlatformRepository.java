package com.jarierca.gamevault.repository.database;

import com.jarierca.gamevault.entity.database.Platform;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlatformRepository implements PanacheRepository<Platform> {
    // Métodos de consulta personalizados, si es necesario
}
