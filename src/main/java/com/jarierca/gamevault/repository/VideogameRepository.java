package com.jarierca.gamevault.repository;

import java.util.List;

import com.jarierca.gamevault.entity.Videogame;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class VideogameRepository {

	@PersistenceContext
	EntityManager entityManager;

	public List<Videogame> listAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Videogame> cq = cb.createQuery(Videogame.class);
		Root<Videogame> rootEntry = cq.from(Videogame.class);
		CriteriaQuery<Videogame> all = cq.select(rootEntry);
		return entityManager.createQuery(all).getResultList();
	}

	public Videogame findById(Long id) {
		return entityManager.find(Videogame.class, id);
	}

	@Transactional
	public void persist(Videogame videogame) {
		entityManager.persist(videogame);
	}

	@Transactional
	public void deleteById(Long id) {
		Videogame reference = entityManager.getReference(Videogame.class, id);
		entityManager.remove(reference);
	}

	public List<Videogame> findByPlayerId(String playerId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Videogame> cq = cb.createQuery(Videogame.class);
		Root<Videogame> root = cq.from(Videogame.class);
		cq.select(root).where(cb.equal(root.get("player").get("id"), playerId));
		return entityManager.createQuery(cq).getResultList();
	}
}
