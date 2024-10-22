package com.jarierca.gamevault.repository.database;

import java.util.List;

import com.jarierca.gamevault.entity.database.Platform;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class PlatformRepository implements PanacheRepository<Platform> {
	
	@PersistenceContext
	EntityManager entityManager;

	public List<Platform> findAll(int page, int size) {
		return entityManager.createQuery("SELECT p FROM Platform p", Platform.class).setFirstResult(page * size)
				.setMaxResults(size).getResultList();
	}

	public long count() {
		return entityManager.createQuery("SELECT COUNT(p) FROM Platform p", Long.class).getSingleResult();
	}
}
