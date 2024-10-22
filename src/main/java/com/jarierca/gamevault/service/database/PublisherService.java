package com.jarierca.gamevault.service.database;

import java.util.List;

import com.jarierca.gamevault.entity.database.Publisher;
import com.jarierca.gamevault.repository.database.PublisherRepository;
import com.jarierca.gamevault.repository.database.StatsRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PublisherService {

	@Inject
	PublisherRepository publisherRepository;

	@Inject
	StatsRepository statsRepository;

	public List<Publisher> listAll() {
		return publisherRepository.listAll();
	}
	
    public List<Publisher> findAll(int page, int size) {
        return publisherRepository.findAll(page, size);
    }

    public long count() {
        return publisherRepository.count();
    }

	public Publisher findById(Long id) {
		return publisherRepository.findById(id);
	}

	public long countPublishers() {
		return statsRepository.count(Publisher.class);
	}

	public List<Object[]> getTopPublishers(int limit) {
		return statsRepository.findTopEntities(Publisher.class, "publisher", limit);
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
