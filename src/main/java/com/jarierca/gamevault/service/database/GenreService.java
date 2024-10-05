package com.jarierca.gamevault.service.database;

import java.util.List;

import com.jarierca.gamevault.entity.database.Genre;
import com.jarierca.gamevault.repository.database.GenreRepository;
import com.jarierca.gamevault.repository.database.StatsRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class GenreService {

	@Inject
	GenreRepository genreRepository;

	@Inject
	StatsRepository statsRepository;

	public List<Genre> listAll() {
		return genreRepository.listAll();
	}

	public Genre findById(Long id) {
		return genreRepository.findById(id);
	}

	public long countGenres() {
		return statsRepository.count(Genre.class);
	}

	public List<Object[]> getTopGenres(int limit) {
		return statsRepository.findTopEntities(Genre.class, "genre", limit);
	}

	@Transactional
	public void addGenre(Genre genre) {
		genreRepository.persist(genre);
	}

	@Transactional
	public void updateGenre(Genre genre) {
		genreRepository.persist(genre);
	}

	@Transactional
	public void deleteGenre(Long id) {
		genreRepository.deleteById(id);
	}
}
