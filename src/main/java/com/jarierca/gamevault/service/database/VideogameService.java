package com.jarierca.gamevault.service.database;

import java.util.List;

import com.jarierca.gamevault.entity.database.Videogame;
import com.jarierca.gamevault.repository.database.VideogameRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class VideogameService {

	@Inject
	VideogameRepository videogameRepository;

	public List<Videogame> listAll() {
		return videogameRepository.listAll();
	}

	public Videogame findById(Long id) {
		return videogameRepository.findById(id);
	}

	public List<Videogame> findByPlatformId(Long platformId) {
		return videogameRepository.findByPlatformId(platformId);
	}

	public List<Videogame> findByGenreId(Long genreId) {
		return videogameRepository.findByGenreId(genreId);
	}

	public List<Videogame> searchByTitle(String title) {
		return videogameRepository.findByTitle(title);
	}

	@Transactional
	public void addVideogame(Videogame videogame) {
		videogameRepository.persist(videogame);
	}

	@Transactional
	public void updateVideogame(Videogame videogame) {
		videogameRepository.persist(videogame);
	}

	@Transactional
	public void deleteVideogame(Long id) {
		videogameRepository.deleteById(id);
	}

}