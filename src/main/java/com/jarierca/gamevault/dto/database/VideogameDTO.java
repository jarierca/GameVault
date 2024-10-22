package com.jarierca.gamevault.dto.database;

import java.util.Date;
import java.util.List;

import com.jarierca.gamevault.entity.database.Image;

public class VideogameDTO {
	public Long id;
	public String title;
	public Date releaseDate;
	public List<Image> images;

	public VideogameDTO(Long id, String title, Date releaseDate) {
		this.id = id;
		this.title = title;
		this.releaseDate = releaseDate;
	}

	public VideogameDTO(Long id, String title, Date releaseDate, List<Image> images) {
		this.id = id;
		this.title = title;
		this.releaseDate = releaseDate;
		this.images = images;
	}
}
