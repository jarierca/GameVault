package com.jarierca.gamevault.repository.database;

import com.jarierca.gamevault.entity.database.Publisher;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PublisherRepository implements PanacheRepository<Publisher> {
}