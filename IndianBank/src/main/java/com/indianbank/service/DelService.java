package com.indianbank.service;

import org.springframework.stereotype.Service;

import com.indianbank.entity.DelAdd;
import com.indianbank.repository.DelAddRepository;

@Service
public class DelService {

	private DelAddRepository delAddRepository;

	public DelService(DelAddRepository delAddRepository) {
		super();
		this.delAddRepository = delAddRepository;
	}

	public Long saveDelAdd(DelAdd delAdd) {
		delAddRepository.save(delAdd);
		return delAdd.getId();
	}

	public void delete(Long id) {
		delAddRepository.deleteById(id);
	}

}
