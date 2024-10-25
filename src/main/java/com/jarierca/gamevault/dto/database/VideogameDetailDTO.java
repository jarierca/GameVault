package com.jarierca.gamevault.dto.database;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.jarierca.gamevault.entity.database.Genre;
import com.jarierca.gamevault.entity.database.Videogame;

public class VideogameDetailDTO {
	public Long id;
	public String title;
	public Date releaseDate;
	public String alternativeNames;
	public String overview;
	public String platformName;
	public String developerName;
	public String publisherName;
	public String genreNames;
	public String gameType;
	public Integer maxPlayers;
	public String urlAlt;
	public String video;
	public List<ImageDTO> images;

	public VideogameDetailDTO(Videogame videogame) {
		this.id = videogame.getId();
		this.title = videogame.getTitle();
		this.releaseDate = videogame.getReleaseDate();
		this.alternativeNames = videogame.getAlternativeNames();
		this.overview = videogame.getOverview();
		this.platformName = videogame.getPlatform() != null ? videogame.getPlatform().getName() : "";
		this.publisherName = videogame.getPublisher() != null ? videogame.getPublisher().getName() : "";
		this.developerName = videogame.getDeveloper() != null ? videogame.getDeveloper().getName() : "";
		this.genreNames = videogame.getGenres() != null
				? videogame.getGenres().stream().map(Genre::getName).collect(Collectors.joining(", "))
				: "";
		this.gameType = videogame.getGameType();
		this.maxPlayers = videogame.getMaxPlayers();
		this.urlAlt = videogame.getUrlAlt();
		this.video = videogame.getVideo();
		this.images = videogame.getImages().stream()
				.map(x -> new ImageDTO(x.getId(), x.getName(), x.getAltName(), x.getUrl(), x.getImageType())).toList();
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

	public String getAlternativeNames() {
		return alternativeNames;
	}

	public void setAlternativeNames(String alternativeNames) {
		this.alternativeNames = alternativeNames;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
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

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public Integer getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(Integer maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public String getUrlAlt() {
		return urlAlt;
	}

	public void setUrlAlt(String urlAlt) {
		this.urlAlt = urlAlt;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public List<ImageDTO> getImages() {
		return images;
	}

	public void setImages(List<ImageDTO> images) {
		this.images = images;
	}

}
