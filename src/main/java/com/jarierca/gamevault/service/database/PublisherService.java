package com.jarierca.gamevault.service.database;

import java.util.List;

import com.jarierca.gamevault.entity.database.Publisher;
import com.jarierca.gamevault.repository.database.PublisherRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PublisherService {

	@Inject
	PublisherRepository publisherRepository;

	public List<Publisher> listAll() {
		return publisherRepository.listAll();
	}

	public Publisher findById(Long id) {
		return publisherRepository.findById(id);
	}

	@Transactional
	public void addPublisher(Publisher publisher) {
		publisherRepository.persist(publisher);
	}

	@Transactional
	public void updatePublisher(Publisher publisher) {
		publisherRepository.persist(publisher);
	}

	@Transactional
	public void deletePublisher(Long id) {
		publisherRepository.deleteById(id);
	}
}
