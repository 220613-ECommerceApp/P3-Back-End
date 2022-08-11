package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Tag;
import com.revature.services.TagService;

@RestController
@RequestMapping("/api/tag")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:3000",
		"http://propanegaming.s3-website.us-east-2.amazonaws.com" }, allowCredentials = "true")
public class TagController {

	@Autowired
	private TagService tagService;

	@GetMapping
	public ResponseEntity<List<Tag>> getTags() {
		return ResponseEntity.ok(tagService.findAll());
	}

}
