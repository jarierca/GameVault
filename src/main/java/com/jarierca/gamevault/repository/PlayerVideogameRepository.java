package com.jarierca.gamevault.repository;

import java.util.List;

import com.jarierca.gamevault.entity.PlayerVideogame;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@ApplicationScoped
public class PlayerVideogameRepository implements PanacheRepository<PlayerVideogame> {
    
	@PersistenceContext
	EntityManager entityManager;
	
	public List<PlayerVideogame> findByPlayerId(String playerId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PlayerVideogame> cq = cb.createQuery(PlayerVideogame.class);
		Root<PlayerVideogame> root = cq.from(PlayerVideogame.class);
		cq.select(root).where(cb.equal(root.get("player").get("id"), playerId));
		return entityManager.createQuery(cq).getResultList();
	}
}
