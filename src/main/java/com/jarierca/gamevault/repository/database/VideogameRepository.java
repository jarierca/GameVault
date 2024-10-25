package com.jarierca.gamevault.repository.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.jarierca.gamevault.dto.database.ImageDTO;
import com.jarierca.gamevault.dto.database.VideogameDTO;
import com.jarierca.gamevault.dto.database.VideogameDetailDTO;
import com.jarierca.gamevault.entity.database.Image;
import com.jarierca.gamevault.entity.database.Videogame;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class VideogameRepository implements PanacheRepository<Videogame> {

	@PersistenceContext
	EntityManager entityManager;

	public VideogameDTO toDTO(Videogame videogame) {
		return new VideogameDTO(videogame.getId(), videogame.getTitle(), videogame.getReleaseDate());
	}

	public List<Videogame> listAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Videogame> cq = cb.createQuery(Videogame.class);
		Root<Videogame> rootEntry = cq.from(Videogame.class);
		CriteriaQuery<Videogame> all = cq.select(rootEntry);
		return entityManager.createQuery(all).getResultList();
	}

	public Videogame findById(Long id) {
		return entityManager.find(Videogame.class, id);
	}

	public VideogameDetailDTO findVideogameDTOById(Long id) {

		Videogame videogame = entityManager.find(Videogame.class, id);

		return new VideogameDetailDTO(videogame);
	}

	public List<VideogameDTO> findRandomGames(int limit) {
		List<Videogame> randomGames = find("ORDER BY RANDOM()").list();

		return randomGames.stream().limit(limit).map(this::toDTO).collect(Collectors.toList());
	}

	public List<VideogameDTO> findByField(String field, Long id, int page, int size) {
		String query = String.format("SELECT v FROM Videogame v " + "LEFT JOIN FETCH v.images i "
				+ "WHERE v.%s.id = :id AND (i.imageType IN :imageTypes OR i.id IS NULL)", field);

		List<Image.ImageType> imageTypes = Arrays.asList(Image.ImageType.COVER, Image.ImageType.BANNER);

		List<Videogame> videogames = entityManager.createQuery(query, Videogame.class).setParameter("id", id)
				.setParameter("imageTypes", imageTypes).setFirstResult(page * size).setMaxResults(size).getResultList();

		List<VideogameDTO> result = new ArrayList<>();
		for (Videogame videogame : videogames) {
			List<ImageDTO> imageDTOs = videogame.getImages().stream()
					.filter(image -> image.getImageType() == Image.ImageType.COVER
							|| image.getImageType() == Image.ImageType.BANNER)
					.map(image -> new ImageDTO(image.getId(), image.getName(), image.getAltName(), image.getUrl(),
							image.getImageType()))
					.collect(Collectors.toList());

			result.add(
					new VideogameDTO(videogame.getId(), videogame.getTitle(), videogame.getReleaseDate(), imageDTOs));
		}

		return result;
	}

	public long countByField(String field, Long id) {
		String query = String.format("SELECT COUNT(v) FROM Videogame v WHERE v.%s.id = :id", field);
		return entityManager.createQuery(query, Long.class).setParameter("id", id).getSingleResult();
	}

	public List<VideogameDTO> findByPlatformId(Long platformId) {
		String query = "SELECT new com.jarierca.gamevault.dto.database.VideogameDTO(v.id, v.title, v.releaseDate) "
				+ "FROM Videogame v WHERE v.platform.id = :platformId";

		return entityManager.createQuery(query, VideogameDTO.class).setParameter("platformId", platformId)
				.getResultList();
	}

	public List<VideogameDTO> findByDeveloperId(Long developerId) {
		String query = "SELECT new com.tu.paquete.VideogameDTO(v.id, v.title, v.releaseDate, v.images) "
				+ "FROM Videogame v WHERE v.developer.id = :developerId";

		return entityManager.createQuery(query, VideogameDTO.class).setParameter("developerId", developerId)
				.getResultList();
	}

	public List<VideogameDTO> findByPublisherId(Long publisherId) {
		String query = "SELECT new com.jarierca.gamevault.dto.database.VideogameDTO(v.id, v.title, v.releaseDate) "
				+ "FROM Videogame v WHERE v.publisher.id = :publisherId";

		return entityManager.createQuery(query, VideogameDTO.class).setParameter("publisherId", publisherId)
				.getResultList();
	}

	public List<VideogameDTO> findByGenreId(Long id, int page, int size) {
		String query = "SELECT new com.jarierca.gamevault.dto.database.VideogameDTO(v.id, v.title, v.releaseDate) "
				+ "FROM Videogame v  JOIN v.genres vg  WHERE vg.id = :id";

		return entityManager.createQuery(query, VideogameDTO.class).setParameter("id", id).setFirstResult(page * size)
				.setMaxResults(size).getResultList();
	}

	public long countByGenre(Long id) {
		String query = String.format("SELECT COUNT(v) FROM Videogame v  JOIN v.genres vg  WHERE vg.id = :id");
		return entityManager.createQuery(query, Long.class).setParameter("id", id).getSingleResult();
	}

	public List<VideogameDTO> findByTitle(String title) {
		String query = "SELECT new com.jarierca.gamevault.dto.database.VideogameDTO(v.id, v.title, v.releaseDate) "
				+ "FROM Videogame v WHERE LOWER(v.title) LIKE LOWER(:title)";

		return entityManager.createQuery(query, VideogameDTO.class).setParameter("title", "%" + title + "%")
				.getResultList();
	}

	@Transactional
	public void persist(Videogame videogame) {
		entityManager.persist(videogame);
	}

	@Transactional
	public boolean deleteById(Long id) {
		Videogame videogame = entityManager.find(Videogame.class, id);
		if (videogame != null) {
			entityManager.remove(videogame);
			return true;
		}
		return false;
	}
}
