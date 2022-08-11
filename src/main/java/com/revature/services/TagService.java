package com.revature.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.models.Tag;
import com.revature.repositories.TagRepository;

@Service
public class TagService {
	private final TagRepository tagRepository;

	public TagService(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}

	public List<Tag> findAll() {
		return tagRepository.findAll();
	}

	// public List<Tag> tagSearch(String tagName) {
	// return tagRepository.tagSearch(tagName);
	// }
}
