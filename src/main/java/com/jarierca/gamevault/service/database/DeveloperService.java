package com.jarierca.gamevault.service.database;

import java.util.List;

import com.jarierca.gamevault.entity.database.Developer;
import com.jarierca.gamevault.repository.database.DeveloperRepository;
import com.jarierca.gamevault.repository.database.StatsRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DeveloperService {

	@Inject
	DeveloperRepository developerRepository;

	@Inject
	StatsRepository statsRepository;

	public List<Developer> listAll() {
		return developerRepository.listAll();
	}

	public Developer findById(Long id) {
		return developerRepository.findById(id);
	}

	public long countDevelopers() {
		return statsRepository.count(Developer.class);
	}

	public List<Object[]> getTopDevelopers(int limit) {
		return statsRepository.findTopEntities(Developer.class, "developer", limit);
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
