package com.jarierca.gamevault.dto.database;

import java.util.Date;

public class VideogameDTO {
	public Long id;
	public String title;
	public Date releaseDate;

	public VideogameDTO(Long id, String title, Date releaseDate) {
		this.id = id;
		this.title = title;
		this.releaseDate = releaseDate;
	}
}
