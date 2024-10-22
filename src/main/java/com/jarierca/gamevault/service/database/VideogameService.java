package com.jarierca.gamevault.service.database;

import java.util.List;

import com.jarierca.gamevault.dto.database.VideogameDTO;
import com.jarierca.gamevault.entity.database.Videogame;
import com.jarierca.gamevault.repository.database.StatsRepository;
import com.jarierca.gamevault.repository.database.VideogameRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class VideogameService {

	@Inject
	VideogameRepository videogameRepository;

	@Inject
	StatsRepository statsRepository;

	public List<Videogame> listAll() {
		return videogameRepository.listAll();
	}

	public Videogame findById(Long id) {
		return videogameRepository.findById(id);
	}

	public long countVideogames() {
		return statsRepository.count(Videogame.class);
	}

	public List<VideogameDTO> getRandomGames(int limit) {
		return videogameRepository.findRandomGames(limit);
	}

	public List<VideogameDTO> findByField(String field, Long id, int page, int size) {
		return videogameRepository.findByField(field, id, page, size);
	}

	public long countByField(String field, Long platformId) {
		return videogameRepository.countByField(field, platformId);
	}

	public List<VideogameDTO> findByGenreId(Long id, int page, int size) {
		return videogameRepository.findByGenreId(id, page, size);
	}
	
	public long countByGenre(Long platformId) {
		return videogameRepository.countByGenre(platformId);
	}

	public List<VideogameDTO> searchByTitle(String title) {
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
