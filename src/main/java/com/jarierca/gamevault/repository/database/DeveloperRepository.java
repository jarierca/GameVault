package com.jarierca.gamevault.repository.database;

import java.util.List;

import com.jarierca.gamevault.entity.database.Developer;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class DeveloperRepository implements PanacheRepository<Developer> {

	@PersistenceContext
	EntityManager entityManager;

	public List<Developer> findAll(int page, int size) {
		return entityManager.createQuery("SELECT p FROM Developer p", Developer.class).setFirstResult(page * size)
				.setMaxResults(size).getResultList();
	}

	public long count() {
		return entityManager.createQuery("SELECT COUNT(p) FROM Developer p", Long.class).getSingleResult();
	}
}
