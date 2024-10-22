package com.jarierca.gamevault.dto.database;

public class ImageDTO {
	public Long id;
	public String name;
	public String altName;
	public String url;
	public Double size;
	public String imageType;

	public ImageDTO(Long id, String name, String altName, String url, Double size, String imageType) {
		this.id = id;
		this.name = name;
		this.altName = altName;
		this.url = url;
		this.size = size;
		this.imageType = imageType;
	}
}
