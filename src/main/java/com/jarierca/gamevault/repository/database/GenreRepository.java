package com.jarierca.gamevault.repository.database;

import java.util.List;

import com.jarierca.gamevault.dto.database.GenreDTO;
import com.jarierca.gamevault.entity.database.Genre;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class GenreRepository implements PanacheRepository<Genre> {

	@PersistenceContext
	EntityManager entityManager;

	public List<GenreDTO> findAll(int page, int size) {
		return entityManager
				.createQuery("SELECT new com.jarierca.gamevault.dto.database.GenreDTO(g.id, g.name) FROM Genre g",
						GenreDTO.class)
				.setFirstResult(page * size).setMaxResults(size).getResultList();
	}

	public long count() {
		return entityManager.createQuery("SELECT COUNT(p) FROM Genre p", Long.class).getSingleResult();
	}
}
