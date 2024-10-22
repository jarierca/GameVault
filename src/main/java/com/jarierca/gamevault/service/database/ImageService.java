package com.jarierca.gamevault.service.database;

import java.util.List;

import com.jarierca.gamevault.dto.database.ImageDTO;
import com.jarierca.gamevault.repository.database.ImageRepository;
import com.jarierca.gamevault.repository.database.StatsRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ImageService {

	@Inject
	ImageRepository imageRepository;

	@Inject
	StatsRepository statsRepository;

	public List<ImageDTO> findImagesByField(String field, Long id) {
		return imageRepository.findImagesByField(field, id);
	}

	public ImageDTO findImageByVideogameIdAndImageId(Long videogameId, Long imageId) {
		return imageRepository.findImageByVideogameIdAndImageId(videogameId, imageId);
	}

}
