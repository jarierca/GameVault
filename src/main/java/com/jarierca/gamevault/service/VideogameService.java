package com.jarierca.gamevault.service;

import java.util.List;

import com.jarierca.gamevault.entity.Videogame;
import com.jarierca.gamevault.repository.VideogameRepository;

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
