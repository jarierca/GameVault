package com.jarierca.gamevault.dto.collection;

import java.sql.Timestamp;
import java.util.Date;

//@SqlResultSetMapping(name = "VideogameCollectionDetailMapping", classes = @ConstructorResult(targetClass = VideogameCollectionDetailDTO.class, columns = {
//		@ColumnResult(name = "id", type = Long.class), @ColumnResult(name = "title", type = String.class),
//		@ColumnResult(name = "overview", type = String.class),
//		@ColumnResult(name = "releaseDate", type = Timestamp.class),
//		@ColumnResult(name = "platformName", type = String.class),
//		@ColumnResult(name = "developerName", type = String.class),
//		@ColumnResult(name = "publisherName", type = String.class),
//		@ColumnResult(name = "genreNames", type = String.class),
//		@ColumnResult(name = "dateAdded", type = Timestamp.class),
//		@ColumnResult(name = "completed", type = Boolean.class),
//		@ColumnResult(name = "timesCompleted", type = Integer.class),
//		@ColumnResult(name = "hoursPlayed", type = Integer.class), @ColumnResult(name = "rating", type = Integer.class),
//		@ColumnResult(name = "digital", type = Boolean.class),
//		@ColumnResult(name = "physicalStatus", type = String.class),
//		@ColumnResult(name = "purchaseDate", type = Timestamp.class),
//		@ColumnResult(name = "status", type = String.class), @ColumnResult(name = "notes", type = String.class) }))
public class VideogameCollectionDetailDTO {
	public Long id;

	// Videogame
	public String title;
	public String overview;
	public Date releaseDate;
	public String platformName;
	public String developerName;
	public String publisherName;
	public String genreNames;

	// Collection Videogame
	public Date dateAdded;
	public Boolean completed;
	public Integer timesCompleted;
	public Integer hoursPlayed;
	public Float rating;
	public Boolean digital;
	public String physicalStatus;
	public Date purchaseDate;
	public String status;
	public String notes;

	public VideogameCollectionDetailDTO() {
	}

	public VideogameCollectionDetailDTO(Long id, String title, String overview, Date releaseDate,
			String platformName, String developerName, String publisherName, String genreNames, Date dateAdded,
			Boolean completed, Integer timesCompleted, Integer hoursPlayed, Float rating, Boolean digital,
			String physicalStatus, Date purchaseDate, String status, String notes) {
		this.id = id;
		this.title = title;
		this.overview = overview;
		this.releaseDate = releaseDate;
		this.platformName = platformName;
		this.developerName = developerName;
		this.publisherName = publisherName;
		this.genreNames = genreNames;
		this.dateAdded = dateAdded;
		this.completed = completed;
		this.timesCompleted = timesCompleted;
		this.hoursPlayed = hoursPlayed;
		this.rating = rating;
		this.digital = digital;
		this.physicalStatus = physicalStatus;
		this.purchaseDate = purchaseDate;
		this.status = status;
		this.notes = notes;
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

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getDeveloperName() {
		return developerName;
	}

	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getGenreNames() {
		return genreNames;
	}

	public void setGenreNames(String genreNames) {
		this.genreNames = genreNames;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Integer getTimesCompleted() {
		return timesCompleted;
	}

	public void setTimesCompleted(Integer timesCompleted) {
		this.timesCompleted = timesCompleted;
	}

	public Integer getHoursPlayed() {
		return hoursPlayed;
	}

	public void setHoursPlayed(Integer hoursPlayed) {
		this.hoursPlayed = hoursPlayed;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public Boolean getDigital() {
		return digital;
	}

	public void setDigital(Boolean digital) {
		this.digital = digital;
	}

	public String getPhysicalStatus() {
		return physicalStatus;
	}

	public void setPhysicalStatus(String physicalStatus) {
		this.physicalStatus = physicalStatus;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
