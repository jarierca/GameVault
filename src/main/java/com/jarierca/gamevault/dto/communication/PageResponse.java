package com.jarierca.gamevault.dto.communication;

import java.util.List;

public class PageResponse<T> {
	private List<T> content;
	private int totalPages;
	private long totalElements;

	public PageResponse() {
	}

	public PageResponse(List<T> content, int totalPages, long totalElements) {
		this.content = content;
		this.totalPages = totalPages;
		this.totalElements = totalElements;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

}
