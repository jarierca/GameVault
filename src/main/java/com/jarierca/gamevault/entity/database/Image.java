package com.jarierca.gamevault.entity.database;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Image extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String altName;
	private String url;
	private Double size;
	@Enumerated(EnumType.STRING)
	private ImageType imageType; // "COVER", "BANNER", "NORMAL"

	@ManyToOne
	@JoinColumn(name = "videogame_id")
	@JsonBackReference
	private Videogame videogame;

	@ManyToOne
	@JoinColumn(name = "platform_id")
	@JsonBackReference
	private Platform platform;

	@ManyToOne
	@JoinColumn(name = "developer_id")
	@JsonBackReference
	private Developer developer;

	@ManyToOne
	@JoinColumn(name = "publisher_id")
	@JsonBackReference
	private Publisher publisher;

	@ManyToOne
	@JoinColumn(name = "genre_id")
	@JsonBackReference
	private Genre genre;

	public enum ImageType {
		COVER, BANNER, NORMAL
	}
	
	public Image() {
	}

	public Image(Long id, String name, String altName, String url, Double size) {
		super();
		this.id = id;
		this.name = name;
		this.altName = altName;
		this.url = url;
		this.size = size;
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

	public String getAltName() {
		return altName;
	}

	public void setAltName(String altName) {
		this.altName = altName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public Videogame getVideogame() {
		return videogame;
	}

	public void setVideogame(Videogame videogame) {
		this.videogame = videogame;
	}

	public Platform getPlatform() {
		return platform;
	}

	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	public Developer getDeveloper() {
		return developer;
	}

	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public ImageType getImageType() {
		return imageType;
	}

	public void setImageType(ImageType imageType) {
		this.imageType = imageType;
	}

}