package com.jarierca.gamevault.service.collection;

import java.util.List;
import java.util.Optional;

import com.jarierca.gamevault.entity.collection.GameCollection;
import com.jarierca.gamevault.entity.collection.Player;
import com.jarierca.gamevault.repository.collection.GameCollectionRepository;
import com.jarierca.gamevault.service.auth.AuthService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class GameCollectionService {

	@Inject
	GameCollectionRepository collectionRepository;

	@Inject
	PlayerService playerService;

	@Inject
	AuthService authService;

	public List<GameCollection> findAll() {
		return collectionRepository.listAll();
	}

	public List<GameCollection> findAllCollectionsByPlayerId() {
		Long playerId = authService.getAuthenticatedUserId();

		return collectionRepository.findAllCollectionsByPlayerId(playerId);
	}

	public GameCollection findById(Long id) {
		return collectionRepository.findById(id);
	}

	public GameCollection findByPlayerIdAndCollectionId(Long collectionId) {
		Long playerId = authService.getAuthenticatedUserId();

		return collectionRepository.findByPlayerIdAndCollectionId(playerId, collectionId);
	}

	public void validatePlayerCollection(Long collectionId) {
		Long playerId = authService.getAuthenticatedUserId();

		GameCollection existingCollection = collectionRepository.findByPlayerIdAndCollectionId(playerId, collectionId);

		if (existingCollection == null) {
			throw new IllegalArgumentException("Yo can't modify this collection.");
		}
	}

	@Transactional
	public GameCollection create(GameCollection collection) {
		Long playerId = authService.getAuthenticatedUserId();

		Player player = playerService.findById(playerId);

		Optional<GameCollection> existingCollection = collectionRepository.find("name", collection.getName())
				.firstResultOptional();

		if (existingCollection.isPresent()) {
			throw new IllegalArgumentException("A collection with the name already exists: " + collection.getName());
		}

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
