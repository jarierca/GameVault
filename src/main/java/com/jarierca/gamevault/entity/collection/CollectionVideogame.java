package com.jarierca.gamevault.entity.collection;

import java.util.Date;

import com.jarierca.gamevault.entity.database.Videogame;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "collection_videogame")
public class CollectionVideogame extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private GameCollection collection;

	@ManyToOne
	private Videogame videogame;

	private boolean digital;

	private String status;
	private boolean completed;

	@Column(name = "times_completed")
	private int timesCompleted;

	private int hoursPlayed;
	private int rating;
	private String notes;

	@Column(name = "date_added")
	private Date dateAdded;

	@Column(name = "physical_status")
	private String physicalStatus;

	@Column(name = "purchase_date")
	private Date purchaseDate;

	public CollectionVideogame() {
	}

	public CollectionVideogame(GameCollection collection, Videogame videogame) {
		super();
		this.collection = collection;
		this.videogame = videogame;
		this.dateAdded = new Date();
	}
	
	public CollectionVideogame(GameCollection collection, Videogame videogame, boolean digital, String status,
			boolean completed, int timesCompleted, int hoursPlayed, int rating, String notes, Date dateAdded,
			String physicalStatus, Date purchaseDate) {
		super();
		this.collection = collection;
		this.videogame = videogame;
		this.digital = digital;
		this.status = status;
		this.completed = completed;
		this.timesCompleted = timesCompleted;
		this.hoursPlayed = hoursPlayed;
		this.rating = rating;
		this.notes = notes;
		this.dateAdded = dateAdded;
		this.physicalStatus = physicalStatus;
		this.purchaseDate = purchaseDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GameCollection getCollection() {
		return collection;
	}

	public void setCollection(GameCollection collection) {
		this.collection = collection;
	}

	public Videogame getVideogame() {
		return videogame;
	}

	public void setVideogame(Videogame videogame) {
		this.videogame = videogame;
	}

	public boolean isDigital() {
		return digital;
	}

	public void setDigital(boolean digital) {
		this.digital = digital;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public int getTimesCompleted() {
		return timesCompleted;
	}

	public void setTimesCompleted(int timesCompleted) {
		this.timesCompleted = timesCompleted;
	}

	public int getHoursPlayed() {
		return hoursPlayed;
	}

	public void setHoursPlayed(int hoursPlayed) {
		this.hoursPlayed = hoursPlayed;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
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

}
