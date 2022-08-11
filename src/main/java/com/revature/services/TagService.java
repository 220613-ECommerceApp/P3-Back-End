package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Tag;
import com.revature.repositories.TagRepository;

@Service
public class TagService {
	@Autowired
	private TagRepository tagRepository;

	public List<Tag> findAll() {
		return tagRepository.findAll();
	}

	// public List<Tag> tagSearch(String tagName) {
	// return tagRepository.tagSearch(tagName);
	// }
}
