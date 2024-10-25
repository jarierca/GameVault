package com.jarierca.gamevault.dto.database;

import com.jarierca.gamevault.entity.database.Image.ImageType;

public class ImageDTO {
	public Long id;
	public String name;
	public String altName;
	public String url;
	public Double size;
	public ImageType imageType;

	public ImageDTO(Long id, String name, String altName, String url, ImageType imageType) {
		this.id = id;
		this.name = name;
		this.altName = altName;
		this.url = url;
		this.imageType = imageType;
	}

}
