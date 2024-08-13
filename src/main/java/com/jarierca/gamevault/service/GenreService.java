package com.jarierca.gamevault.service;

import java.util.List;

import com.jarierca.gamevault.entity.Genre;
import com.jarierca.gamevault.repository.GenreRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class GenreService {

    @Inject
    GenreRepository genreRepository;

    public List<Genre> listAll() {
        return genreRepository.listAll();
    }

    public Genre findById(Long id) {
        return genreRepository.findById(id);
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
