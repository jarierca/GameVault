package com.jarierca.gamevault.repository.collection;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.jarierca.gamevault.dto.collection.VideogameCollectionDetailDTO;
import com.jarierca.gamevault.dto.collection.VideogameCollectionViewDTO;
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
	public List<VideogameCollectionViewDTO> findByPlayerIdAndGameCollectionId(Long playerId, Long gamecollectionId) {
	    String query = "SELECT cv.id AS id, v.title AS title, v.releaseDate AS releaseDate, "
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
				.map(row -> new VideogameCollectionViewDTO(
						(Long) row[0], // cv.id
						(String) row[1], // v.title
						(Date) row[2], // v.releaseDate
						(String) row[3], // pl.name
						(String) row[4], // pu.name
						(String) row[5], // d.name
						(String) row[6] // genreNames
				)).collect(Collectors.toList());
	}

	public VideogameCollectionDetailDTO findByPlayerIdAndCollectionVideogameId(Long playerId, Long videogameCollectionId) {
		String query = "SELECT cv.id, v.title, v.overview, v.releaseDate, pl.name, d.name, pu.name, "
                + "COALESCE(STRING_AGG(g.name, ', '), ''), cv.dateAdded, cv.completed, cv.timesCompleted, "
                + "cv.hoursPlayed, cv.rating, cv.digital, cv.physicalStatus, "
                + "cv.purchaseDate, cv.status, cv.notes "
                + "FROM CollectionVideogame cv "
	            + "LEFT JOIN cv.videogame v "
	            + "LEFT JOIN v.publisher pu "
	            + "LEFT JOIN v.platform pl "
	            + "LEFT JOIN v.developer d "
	            + "LEFT JOIN v.genres g "
	            + "JOIN cv.collection gc "
	            + "JOIN gc.player p "
	            + "WHERE cv.id = :videogameCollectionId "
	            + "AND p.id = :playerId "
                + "GROUP BY cv.id, v.title, v.overview, v.releaseDate, pl.name, d.name, pu.name, "
                + "cv.dateAdded, cv.completed, cv.timesCompleted, cv.hoursPlayed, cv.rating, "
                + "cv.digital, cv.physicalStatus, cv.purchaseDate, cv.status, cv.notes";

	    TypedQuery<Object[]> typedQuery = entityManager.createQuery(query, Object[].class)
	            .setParameter("videogameCollectionId", videogameCollectionId)
	            .setParameter("playerId", playerId);

		return typedQuery.getResultList().stream().map(row -> new VideogameCollectionDetailDTO((Long) row[0], // cv.id
				(String) row[1], // v.title
				(String) row[2], // v.overview
				(Date) row[3], // v.releaseDate
				(String) row[4], // platformName
				(String) row[5], // developerName
				(String) row[6], // publisherName
				(String) row[7], // genreNames
				(Date) row[8], // cv.dateAdded
				(Boolean) row[9], // cv.completed
				(Integer) row[10], // cv.timesCompleted
				(Integer) row[11], // cv.hoursPlayed
				(Float) row[12], // cv.rating
				(Boolean) row[13], // cv.digital
				(String) row[14], // cv.physicalStatus
				(Date) row[15], // cv.purchaseDate
				(String) row[16], // cv.status
				(String) row[17] // cv.notes
		)).findFirst().orElse(null);
	}

}
