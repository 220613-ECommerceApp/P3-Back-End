package com.revature.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Tag;
import com.revature.repositories.TagRepository;

@Service
public class TagService {

	@Autowired
	private final TagRepository tagRepository;

	public TagService(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}

	public List<Tag> findAll() {
		return tagRepository.findAll();
	}

    // public List<Tag> tagSearch(String tagName) {
    //     return tagRepository.tagSearch(tagName);
    //}
}
