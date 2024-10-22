package com.jarierca.gamevault.dto.collection;

import java.sql.Timestamp;

public class VideogameCollectionDTO {
	public Long id;
	public String title;
	public Timestamp releaseDate;
	public String platformName;
	public String developerName;
	public String publisherName;
	public String genreNames;

	public VideogameCollectionDTO() {
	}

	public VideogameCollectionDTO(Long id, String title, Timestamp releaseDate, String platformName,
			String publisherName, String developerName, String genreNames) {
		this.id = id;
		this.title = title;
		this.releaseDate = releaseDate;
		this.platformName = platformName;
		this.publisherName = publisherName;
		this.developerName = developerName;
		this.genreNames = genreNames;
	}

}
