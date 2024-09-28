package com.jarierca.gamevault.repository.collection;

import java.util.List;

import com.jarierca.gamevault.entity.collection.GameCollection;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GameCollectionRepository implements PanacheRepository<GameCollection> {

	public List<GameCollection> findAllCollectionsByPlayerId(Long playerId) {
		return find("player.id", playerId).list();
	}

	public GameCollection findByPlayerIdAndCollectionId(Long playerId, Long collectionId) {
		return find("player.id = ?1 AND id = ?2", playerId, collectionId).firstResult();
	}
}
