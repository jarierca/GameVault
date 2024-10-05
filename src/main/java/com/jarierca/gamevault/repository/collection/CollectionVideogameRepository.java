package com.jarierca.gamevault.repository.collection;

import java.util.List;

import com.jarierca.gamevault.entity.collection.CollectionVideogame;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class CollectionVideogameRepository implements PanacheRepository<CollectionVideogame> {

	@PersistenceContext
	EntityManager entityManager;

	public List<CollectionVideogame> findByGameCollectionId(Long gameCollectionId) {
		return find("collection.id", gameCollectionId).list();
	}

	public List<CollectionVideogame> findByPlayerIdAndGameCollectionId(Long playerId, Long collectionId) {
		return find("collection.player.id = ?1 AND collection.id = ?2", playerId, collectionId).list();
	}

	public CollectionVideogame findByPlayerIdAndCollectionVideogameId(Long playerId, Long collectionVideogameId) {
		return find("collection.player.id = ?1 AND id = ?2", playerId, collectionVideogameId).firstResult();
	}
}
