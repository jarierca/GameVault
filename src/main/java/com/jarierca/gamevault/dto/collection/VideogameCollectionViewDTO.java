package com.jarierca.gamevault.dto.collection;

import java.util.Date;
import java.util.stream.Collectors;

import com.jarierca.gamevault.entity.collection.CollectionVideogame;
import com.jarierca.gamevault.entity.database.Genre;

public class VideogameCollectionViewDTO {
	public Long id;
	public String title;
	public Date releaseDate;
	public String platformName;
	public String developerName;
	public String publisherName;
	public String genreNames;

	public VideogameCollectionViewDTO() {
	}

	public VideogameCollectionViewDTO(Long id, String title, Date releaseDate, String platformName,
			String publisherName, String developerName, String genreNames) {
		this.id = id;
		this.title = title;
		this.releaseDate = releaseDate;
		this.platformName = platformName;
		this.publisherName = publisherName;
		this.developerName = developerName;
		this.genreNames = genreNames;
	}

	public VideogameCollectionViewDTO(CollectionVideogame createdCollectGame) {
		this.id = createdCollectGame.getId();
		this.title = createdCollectGame.getVideogame().getTitle();
		this.releaseDate = createdCollectGame.getVideogame().getReleaseDate();
		this.platformName = createdCollectGame.getVideogame().getPlatform() != null
				? createdCollectGame.getVideogame().getPlatform().getName()
				: "";
		this.publisherName = createdCollectGame.getVideogame().getPublisher() != null
				? createdCollectGame.getVideogame().getPublisher().getName()
				: "";
		this.developerName = createdCollectGame.getVideogame().getDeveloper() != null
				? createdCollectGame.getVideogame().getDeveloper().getName()
				: "";
		this.genreNames = createdCollectGame.getVideogame().getGenres() != null ? createdCollectGame.getVideogame()
				.getGenres().stream().map(Genre::getName).collect(Collectors.joining(", ")) : "";
	}

}
