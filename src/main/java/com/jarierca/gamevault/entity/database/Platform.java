package com.jarierca.gamevault.entity.database;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Platform extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String code;
	private String description;
	private Date releasedDate;
	@OneToMany(mappedBy = "platform", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Image> images;

	public Platform() {
	}

	public Platform(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Platform(String name, String code, String description, Date releasedDate) {
		super();
		this.name = name;
		this.code = code;
		this.description = description;
		this.releasedDate = releasedDate;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getReleasedDate() {
		return releasedDate;
	}

	public void setReleasedDate(Date releasedDate) {
		this.releasedDate = releasedDate;
	}

}