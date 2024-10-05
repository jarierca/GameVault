package com.jarierca.gamevault.service.database;

import java.util.List;

import com.jarierca.gamevault.entity.database.Platform;
import com.jarierca.gamevault.repository.database.PlatformRepository;
import com.jarierca.gamevault.repository.database.StatsRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PlatformService {

	@Inject
	PlatformRepository platformRepository;

	@Inject
	StatsRepository statsRepository;

	public List<Platform> listAll() {
		return platformRepository.listAll();
	}

	public Platform findById(Long id) {
		return platformRepository.findById(id);
	}

	public long countPlatforms() {
		return statsRepository.count(Platform.class);
	}

	public List<Object[]> getTopPlatforms(int limit) {
		return statsRepository.findTopEntities(Platform.class, "platform", limit);
	}

	@Transactional
	public void addPlatform(Platform platform) {
		platformRepository.persist(platform);
	}

	@Transactional
	public void updatePlatform(Platform platform) {
		platformRepository.persist(platform);
	}

	@Transactional
	public void deletePlatform(Long id) {
		platformRepository.deleteById(id);
	}
}
