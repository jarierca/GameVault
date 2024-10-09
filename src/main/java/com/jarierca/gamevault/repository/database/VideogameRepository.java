package com.jarierca.gamevault.repository.database;

import java.util.List;
import java.util.stream.Collectors;

import com.jarierca.gamevault.dto.database.VideogameDTO;
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

	public List<VideogameDTO> findRandomGames(int limit) {
		List<Videogame> randomGames = find("ORDER BY RANDOM()").list();

		return randomGames.stream().limit(limit).map(this::toDTO).collect(Collectors.toList());
	}

	public List<Videogame> findByPlatformId(Long platformId) {
		return list("platform.id", platformId);
	}

	public List<Videogame> findByPublisherId(Long publisherId) {
		return list("publisher.id", publisherId);
	}
	
	public List<Videogame> findByGenreId(Long genreId) {
		return list("genre.id", genreId);
	}
	
	public List<Videogame> findByDeveloperId(Long developerId) {
		return list("developer.id", developerId);
	}

	public List<Videogame> findByTitle(String title) {
		return find("LOWER(title) LIKE LOWER(?1)", "%" + title + "%").list();
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
