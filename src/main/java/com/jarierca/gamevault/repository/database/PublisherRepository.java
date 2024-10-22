package com.jarierca.gamevault.repository.database;

import java.util.List;

import com.jarierca.gamevault.entity.database.Publisher;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class PublisherRepository implements PanacheRepository<Publisher> {

	@PersistenceContext
	EntityManager entityManager;

	public List<Publisher> findAll(int page, int size) {
		return entityManager.createQuery("SELECT p FROM Publisher p", Publisher.class).setFirstResult(page * size)
				.setMaxResults(size).getResultList();
	}

	public long count() {
		return entityManager.createQuery("SELECT COUNT(p) FROM Publisher p", Long.class).getSingleResult();
	}
}
