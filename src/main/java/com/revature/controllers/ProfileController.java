package com.revature.controllers;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.Authorized;
import com.revature.models.User;
import com.revature.services.UserService;


@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class ProfileController {
	
	@Autowired
	private UserService us;
	
//	@GetMapping 
//	public User getUserById(@RequestParam("id") int id) {
//		return us.getId(id);
//	}
//	
	@GetMapping 
	@Authorized
	public ResponseEntity<User> getUserById(@RequestParam("id") int id) {
		if(id <= 0) {
			return new ResponseEntity<User>(us.getId(id), HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(us.getId(id));
	}
	

	@PutMapping
	@Authorized
	public ResponseEntity<User> updateUser(@RequestBody User u) {
		if (u==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(us.update(u));
	}
	
}
