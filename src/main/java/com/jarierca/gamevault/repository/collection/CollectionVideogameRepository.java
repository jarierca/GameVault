package com.jarierca.gamevault.repository.collection;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import com.jarierca.gamevault.dto.collection.VideogameCollectionDTO;
import com.jarierca.gamevault.entity.collection.CollectionVideogame;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class CollectionVideogameRepository implements PanacheRepository<CollectionVideogame> {

	@PersistenceContext
	EntityManager entityManager;

	public List<CollectionVideogame> findByGameCollectionId(Long gameCollectionId) {
		return find("collection.id", gameCollectionId).list();
	}

	// TODO Optimize
	public List<VideogameCollectionDTO> findByPlayerIdAndGameCollectionId(Long playerId, Long gamecollectionId) {
	    String query = "SELECT "
	            + "cv.id AS id, v.title AS title, v.releaseDate AS releaseDate, "
	            + "pl.name AS platformName, pu.name AS publisherName, "
	            + "d.name AS developerName, COALESCE(STRING_AGG(g.name, ', '), '') AS genreNames "
	            + "FROM CollectionVideogame cv "
	            + "LEFT JOIN cv.videogame v "
	            + "LEFT JOIN v.publisher pu "
	            + "LEFT JOIN v.platform pl "
	            + "LEFT JOIN v.developer d "
	            + "LEFT JOIN v.genres g "
	            + "JOIN cv.collection gc "
	            + "JOIN gc.player p "
	            + "WHERE p.id = :playerId "
	            + "AND gc.id = :gamecollectionId "
	            + "GROUP BY cv.id, v.title, v.releaseDate, pl.name, pu.name, d.name";

		TypedQuery<Object[]> typedQuery = entityManager.createQuery(query, Object[].class)
				.setParameter("playerId", playerId).setParameter("gamecollectionId", gamecollectionId);

		return typedQuery.getResultList().stream()
				.map(row -> new VideogameCollectionDTO(
						(Long) row[0], // cv.id
						(String) row[1], // v.title
						(Timestamp) row[2], // v.releaseDate
						(String) row[3], // pl.name
						(String) row[4], // pu.name
						(String) row[5], // d.name
						(String) row[6] // genreNames
				)).collect(Collectors.toList());
	}

	public CollectionVideogame findByPlayerIdAndCollectionVideogameId(Long playerId, Long collectionVideogameId) {
		return find("collection.player.id = ?1 AND id = ?2", playerId, collectionVideogameId).firstResult();
	}
}
