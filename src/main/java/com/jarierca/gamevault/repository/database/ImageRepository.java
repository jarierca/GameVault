package com.jarierca.gamevault.repository.database;

import java.util.List;

import com.jarierca.gamevault.dto.database.ImageDTO;
import com.jarierca.gamevault.entity.database.Videogame;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class ImageRepository implements PanacheRepository<Videogame> {

	@PersistenceContext
	EntityManager entityManager;

	public List<ImageDTO> findImagesByField(String field, Long id) {
		String query = String.format(
				"SELECT new com.jarierca.gamevault.dto.database.ImageDTO(i.id, i.name, i.altName, i.url, i.size, i.imageType) "
						+ "FROM Image i WHERE i.%s.id = :id",
				field);

		return entityManager.createQuery(query, ImageDTO.class).setParameter("id", id).getResultList();
	}

	public ImageDTO findImageByVideogameIdAndImageId(Long videogameId, Long imageId) {
		String query = "SELECT new com.jarierca.gamevault.dto.database.ImageDTO(i.id, i.name, i.altName, i.url, i.size, i.imageType) "
				+ "FROM Image i WHERE i.id = :imageId AND i.videogame.id = :videogameId";

		try {
			return entityManager.createQuery(query, ImageDTO.class).setParameter("imageId", imageId)
					.setParameter("videogameId", videogameId).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
