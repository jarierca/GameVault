package com.jarierca.gamevault.dto.collection;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.jarierca.gamevault.dto.database.ImageDTO;
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
	public List<ImageDTO> images;

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

	public VideogameCollectionViewDTO(Long id, String title, Date releaseDate, String platformName,
			String publisherName, String developerName, String genreNames, List<ImageDTO> images) {
		this.id = id;
		this.title = title;
		this.releaseDate = releaseDate;
		this.platformName = platformName;
		this.publisherName = publisherName;
		this.developerName = developerName;
		this.genreNames = genreNames;
		this.images = images;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getDeveloperName() {
		return developerName;
	}

	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getGenreNames() {
		return genreNames;
	}

	public void setGenreNames(String genreNames) {
		this.genreNames = genreNames;
	}

	public List<ImageDTO> getImages() {
		return images;
	}

	public void setImages(List<ImageDTO> images) {
		this.images = images;
	}

}
