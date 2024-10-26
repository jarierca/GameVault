package com.jarierca.gamevault.dto.collection;

import java.util.Date;

public class GameCollection {
	private Long id;

	private String name;
	private String description;
	private boolean favorite;
	private Date createdDate;

	public GameCollection(Long id, String name, String description, boolean favorite, Date createdDate) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.favorite = favorite;
		this.createdDate = createdDate;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}