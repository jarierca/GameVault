package com.jarierca.gamevault.entity;

import java.util.Date;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Publisher extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String description;

	@ManyToOne
	private Videogame videoGame;

	private Date date;

	public Publisher() {
	}

	public Publisher(Long id, String name, String description, Videogame videoGame, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.videoGame = videoGame;
		this.date = date;
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

	public Videogame getVideoGame() {
		return videoGame;
	}

	public void setVideoGame(Videogame videoGame) {
		this.videoGame = videoGame;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}