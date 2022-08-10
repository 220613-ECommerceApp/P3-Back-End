package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Tag;
import com.revature.services.TagService;
import com.revature.annotations.Authorized;

@RestController
@RequestMapping("/api/tag")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:3000",
		"http://propanegaming.s3-website.us-east-2.amazonaws.com" }, allowCredentials = "true")
public class TagController {
	private final TagService tagService;

	public TagController(TagService tagService) {
		this.tagService = tagService;
	}

	@GetMapping
	public ResponseEntity<List<Tag>> getTags() {
		return ResponseEntity.ok(tagService.findAll());
	}

}
