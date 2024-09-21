package com.jarierca.gamevault.service.database;

import java.util.List;

import com.jarierca.gamevault.entity.database.Developer;
import com.jarierca.gamevault.repository.database.DeveloperRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DeveloperService {

    @Inject
    DeveloperRepository developerRepository;

    public List<Developer> listAll() {
        return developerRepository.listAll();
    }

    public Developer findById(Long id) {
        return developerRepository.findById(id);
    }

    @Transactional
    public void addDeveloper(Developer developer) {
        developerRepository.persist(developer);
    }

    @Transactional
    public void updateDeveloper(Developer developer) {
        developerRepository.persist(developer);
    }

    @Transactional
    public void deleteDeveloper(Long id) {
        developerRepository.deleteById(id);
    }
}

