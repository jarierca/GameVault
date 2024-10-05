package com.jarierca.gamevault.dto.database;

public class StatsDTO {
	public long totalVideogames;
	public long totalDevelopers;
	public long totalGenres;
	public long totalPublishers;
	public long totalPlatforms;

	public StatsDTO(long totalVideogames, long totalDevelopers, long totalGenres, long totalPublishers,
			long totalPlatforms) {
		this.totalVideogames = totalVideogames;
		this.totalDevelopers = totalDevelopers;
		this.totalGenres = totalGenres;
		this.totalPublishers = totalPublishers;
		this.totalPlatforms = totalPlatforms;
	}
}