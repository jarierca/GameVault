package com.jarierca.gamevault.entity.database;

import java.util.Date;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Videogame extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	@ManyToOne
	private Platform platform;
	private Date releaseDate;
	private String gameType;
	private Integer maxPlayers;
	private String overview;
	private String alternativeNames;
	private String urlAlt;
	private String video;
	@ManyToOne
	private Genre genre;
	@ManyToOne
	private Developer developer;
	@ManyToOne
	private Publisher publisher;
	@ManyToOne
	private Images images;

	public Videogame() {
	}

	public Videogame(Long id, String title, Platform platform, Date releaseDate, String gameType, Integer maxPlayers,
			String overview, String alternativeNames, String urlAlt, String video, Genre genre, Developer developer,
			Publisher publisher, Images images) {
		super();
		this.id = id;
		this.title = title;
		this.platform = platform;
		this.releaseDate = releaseDate;
		this.gameType = gameType;
		this.maxPlayers = maxPlayers;
		this.overview = overview;
		this.alternativeNames = alternativeNames;
		this.urlAlt = urlAlt;
		this.video = video;
		this.genre = genre;
		this.developer = developer;
		this.publisher = publisher;
		this.images = images;
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

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
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

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getAlternativeNames() {
		return alternativeNames;
	}

	public void setAlternativeNames(String alternativeNames) {
		this.alternativeNames = alternativeNames;
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

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Developer getDeveloper() {
		return developer;
	}

	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Images getImages() {
		return images;
	}

	public void setImages(Images images) {
		this.images = images;
	}

}
