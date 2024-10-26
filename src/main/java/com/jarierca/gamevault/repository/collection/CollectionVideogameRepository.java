package com.jarierca.gamevault.repository.collection;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jarierca.gamevault.dto.collection.VideogameCollectionDetailDTO;
import com.jarierca.gamevault.dto.collection.VideogameCollectionViewDTO;
import com.jarierca.gamevault.dto.database.ImageDTO;
import com.jarierca.gamevault.entity.collection.CollectionVideogame;
import com.jarierca.gamevault.entity.database.Image;

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
	public List<VideogameCollectionViewDTO> findByPlayerIdAndGameCollectionId(Long playerId, Long gameCollectionId) {
	    String query = "SELECT cv.id AS id, v.title AS title, v.releaseDate AS releaseDate, "
	            + "pl.name AS platformName, pu.name AS publisherName, "
	            + "d.name AS developerName, COALESCE(STRING_AGG(g.name, ', '), '') AS genreNames, "
	            + "i.id AS imageId, i.name AS imageName, i.altName AS imageAltName, i.url AS imageUrl, "
	            + "CAST(i.imageType AS string) AS imageType "
	            + "FROM CollectionVideogame cv "
	            + "LEFT JOIN cv.videogame v "
	            + "LEFT JOIN v.publisher pu "
	            + "LEFT JOIN v.platform pl "
	            + "LEFT JOIN v.developer d "
	            + "LEFT JOIN v.genres g "
	            + "LEFT JOIN v.images i "
	            + "JOIN cv.collection gc "
	            + "JOIN gc.player p "
	            + "WHERE p.id = :playerId "
	            + "AND gc.id = :gamecollectionId "
	            + "GROUP BY cv.id, v.title, v.releaseDate, pl.name, pu.name, d.name, i.id";

		List<Object[]> results = entityManager.createQuery(query).setParameter("playerId", playerId)
				.setParameter("gamecollectionId", gameCollectionId).getResultList();

		Map<Long, VideogameCollectionViewDTO> dtoMap = new HashMap<>();

		for (Object[] result : results) {
			Long id = (Long) result[0];
			String title = (String) result[1];
			Date releaseDate = (Date) result[2];
			String platformName = (String) result[3];
			String publisherName = (String) result[4];
			String developerName = (String) result[5];
			String genreNames = (String) result[6];

			List<ImageDTO> images = dtoMap.computeIfAbsent(id, k -> new VideogameCollectionViewDTO(id, title,
					releaseDate, platformName, publisherName, developerName, genreNames, new ArrayList<>()))
					.getImages();

			if (result[7] != null) {
				Long imageId = (Long) result[7];
				String imageName = (String) result[8];
				String imageAltName = (String) result[9];
				String imageUrl = (String) result[10];
				String imageType = (String) result[11];

				images.add(
						new ImageDTO(imageId, imageName, imageAltName, imageUrl, Image.ImageType.valueOf(imageType)));
			}
		}

	    return new ArrayList<>(dtoMap.values());
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
