package com.jarierca.gamevault.repository.collection;

import java.util.List;

import com.jarierca.gamevault.entity.collection.CollectionVideogame;
import com.jarierca.gamevault.entity.database.Videogame;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CollectionVideogameRepository implements PanacheRepository<CollectionVideogame> {

	@PersistenceContext
	EntityManager entityManager;

	public List<CollectionVideogame> findByGameCollectionId(Long gameCollectionId) {
        return find("collection.id", gameCollectionId).list();
    }

	@Transactional
	public void persist(Videogame videogame) {
		entityManager.persist(videogame);
	}
}
