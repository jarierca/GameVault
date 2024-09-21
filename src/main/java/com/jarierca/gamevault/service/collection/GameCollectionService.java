package com.jarierca.gamevault.service.collection;

import java.util.List;

import com.jarierca.gamevault.entity.collection.GameCollection;
import com.jarierca.gamevault.entity.database.Player;
import com.jarierca.gamevault.repository.collection.GameCollectionRepository;
import com.jarierca.gamevault.service.database.PlayerService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class GameCollectionService {

	@Inject
	GameCollectionRepository collectionRepository;
	
	@Inject
	PlayerService playerService;

	public List<GameCollection> findAll() {
		return collectionRepository.listAll();
	}
	
	public List<GameCollection> findAllCollectionsByPlayerId(Long playerId) {
        return collectionRepository.findAllCollectionsByPlayerId(playerId);
    }

	public GameCollection findById(Long id) {
		return collectionRepository.findById(id);
	}

	@Transactional
	public GameCollection create(GameCollection collection, Long playerId) {
		
		Player player = playerService.findById(playerId);
		
		collectionRepository.persist(new GameCollection(collection, player));
		return collection;
	}

	@Transactional
	public GameCollection update(Long id, GameCollection updatedCollection) {
		GameCollection existingCollection = collectionRepository.findById(id);
		if (existingCollection != null) {
			existingCollection.setName(updatedCollection.getName());
			existingCollection.setDescription(updatedCollection.getDescription());
			return existingCollection;
		}
		return null;
	}

	@Transactional
	public boolean delete(Long id) {
		return collectionRepository.deleteById(id);
	}
}
