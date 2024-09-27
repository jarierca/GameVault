package com.jarierca.gamevault.service.collection;

import java.util.Collections;
import java.util.List;

import com.jarierca.gamevault.entity.collection.CollectionVideogame;
import com.jarierca.gamevault.entity.collection.GameCollection;
import com.jarierca.gamevault.entity.database.Videogame;
import com.jarierca.gamevault.repository.collection.CollectionVideogameRepository;
import com.jarierca.gamevault.repository.collection.GameCollectionRepository;
import com.jarierca.gamevault.repository.database.VideogameRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CollectionVideogameService {

	@Inject
	CollectionVideogameRepository collectionVideogameRepository;
	@Inject
	VideogameRepository videogameService;
	@Inject
	GameCollectionRepository gameCollectionService;

	public List<CollectionVideogame> findAll() {
		return collectionVideogameRepository.listAll();
	}

	public CollectionVideogame findById(Long id) {
		return collectionVideogameRepository.findById(id);
	}

	public List<CollectionVideogame> getCollectionVideogameByCollectionId(Long gamecollectionId) {
		List<CollectionVideogame> collectionVideogames = collectionVideogameRepository
				.findByGameCollectionId(gamecollectionId);

		if (collectionVideogames != null) {
			return collectionVideogames;
		}
		
		return Collections.emptyList();
	}

	@Transactional
	public CollectionVideogame create(CollectionVideogame collectionVideogame) {
		collectionVideogameRepository.persist(collectionVideogame);
		return collectionVideogame;
	}

	public CollectionVideogame addGameToCollection(Long collectionId, Long gameId) {
		if (collectionId == null) {
			throw new IllegalArgumentException("Collection ID cannot be null.");
		}

		if (gameId == null) {
			throw new IllegalArgumentException("Game ID cannot be null.");
		}

		GameCollection gameCollection = gameCollectionService.findById(collectionId);
		if (gameCollection == null) {
			throw new IllegalArgumentException("No GameCollection found with ID: " + collectionId);
		}

		Videogame game = videogameService.findById(gameId);
		if (game == null) {
			throw new IllegalArgumentException("No Videogame found with ID: " + gameId);
		}

		return create(new CollectionVideogame(gameCollection, game));
	}

	@Transactional
	public CollectionVideogame update(CollectionVideogame collectionVideogame) {
		if (collectionVideogameRepository.isPersistent(collectionVideogame)) {
			return collectionVideogameRepository.getEntityManager().merge(collectionVideogame);
		}
		return null;
	}
	
	@Transactional
	public CollectionVideogame update(Long id, CollectionVideogame updatedCollectionVideogame) {
		CollectionVideogame existingCollectionVideogame = collectionVideogameRepository.findById(id);
		if (existingCollectionVideogame != null) {
			
			existingCollectionVideogame.copyFrom(updatedCollectionVideogame);

			return existingCollectionVideogame;
		}
		return null;
	}

	@Transactional
	public boolean delete(Long id) {
		return collectionVideogameRepository.deleteById(id);
	}
}
