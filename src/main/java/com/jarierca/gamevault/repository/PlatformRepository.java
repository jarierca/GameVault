package com.jarierca.gamevault.repository;

import com.jarierca.gamevault.entity.Platform;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlatformRepository implements PanacheRepository<Platform> {
    // Métodos de consulta personalizados, si es necesario
}
