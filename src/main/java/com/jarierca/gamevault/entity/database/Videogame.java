package com.jarierca.gamevault.entity.database;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

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
	@Column(length = 5000)
	private String overview;
	private String alternativeNames;
	private String urlAlt;
	private String video;
	@ManyToMany
	@JoinTable(name = "videogame_genre", joinColumns = @JoinColumn(name = "videogame_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
	@JsonManagedReference
	public Set<Genre> genres;
	@ManyToOne
	private Developer developer;
	@ManyToOne
	private Publisher publisher;
	@OneToMany(mappedBy = "videogame", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Image> images;

	public Videogame() {
	}

	public Videogame(Long id, String title, Platform platform, Date releaseDate, String gameType, Integer maxPlayers,
			String overview, String alternativeNames, String urlAlt, String video, Set<Genre> genres,
			Developer developer, Publisher publisher) {
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
		this.genres = genres;
		this.developer = developer;
		this.publisher = publisher;
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

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenre(Set<Genre> genres) {
		this.genres = genres;
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
}
