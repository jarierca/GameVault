package com.jarierca.gamevault.repository;

import java.util.List;

import com.jarierca.gamevault.entity.Videogame;

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

	public List<Videogame> findByPlatformId(Long platformId) {
		return list("platform.id", platformId);
	}
	
	public List<Videogame> findByGenreId(Long genreId) {
		return list("genre.id", genreId);
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
