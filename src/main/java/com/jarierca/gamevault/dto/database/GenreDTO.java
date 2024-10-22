package com.jarierca.gamevault.dto.database;

import java.util.List;

import com.jarierca.gamevault.entity.database.Image;

public class GenreDTO {
	private Long id;
	private String name;
	private List<Image> images;

	public GenreDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

}
