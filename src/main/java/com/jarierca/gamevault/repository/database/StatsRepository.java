package com.jarierca.gamevault.repository.database;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class StatsRepository {

	@Inject
	EntityManager em;

	public <T> long count(Class<T> entityClass) {
		String query = String.format("SELECT COUNT(e) FROM %s e", entityClass.getSimpleName());
		return em.createQuery(query, Long.class).getSingleResult();
	}

	public <T> List<Object[]> findTopEntities(Class<T> entityClass, String entityField, int limit) {
		String entityName = entityClass.getSimpleName();
		String query = String.format(
				"SELECT e.id, e.name, COUNT(v) FROM %s e JOIN Videogame v ON v.%s.id = e.id GROUP BY e.id, e.name ORDER BY COUNT(v) DESC",
				entityName, entityField);

		return em.createQuery(query, Object[].class).setMaxResults(limit).getResultList();
	}

	public List<Object[]> findTopVideogamesGenre(int limit) {
		String query = "SELECT g.id, g.name, COUNT(v) " 
				+ "From Videogame v "
	            + "JOIN v.genres g "
				+ "GROUP BY g.id , g.name " 
				+ "ORDER BY COUNT(v) DESC ";

		return em.createQuery(query, Object[].class).setMaxResults(limit).getResultList();
	}

}