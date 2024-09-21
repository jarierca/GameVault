package com.jarierca.gamevault.entity.collection;

import java.util.Date;

import com.jarierca.gamevault.entity.database.Player;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class GameCollection extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String description;
	private boolean favorite;
	private Date createdDate;

	@ManyToOne
	private Player player;

	public GameCollection() {
	}

	public GameCollection(GameCollection collection, Player player) {
		super();
		this.name = collection.getName();
		this.description = collection.getDescription();
		this.createdDate = new Date();
		this.player = player;
	}

	public GameCollection(String name, boolean favorite, String description, Date createdDate, Player player) {
		super();
		this.name = name;
		this.description = description;
		this.favorite = favorite;
		this.createdDate = createdDate;
		this.player = player;
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

	public boolean getFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}