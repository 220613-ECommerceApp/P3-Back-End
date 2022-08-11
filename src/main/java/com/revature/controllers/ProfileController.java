package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.Authorized;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.utils.JWTUtil;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class ProfileController {

	@Autowired
	private UserService us;

	// @GetMapping
	// public User getUserById(@RequestParam("id") int id) {
	// return us.getId(id);
	// }
	//
	// @GetMapping
	// @Authorized
	// public ResponseEntity<User> getUserById(@RequestParam("id") int id) {
	// if(id <= 0) {
	// return new ResponseEntity<User>(us.getId(id), HttpStatus.BAD_REQUEST);
	// }
	// return ResponseEntity.ok(us.getId(id));
	// }

	@GetMapping
	public ResponseEntity<User> getUser(@RequestHeader("Authorization") String token) {
		int userid = JWTUtil.verifyUserToken(token);
		return ResponseEntity.ok(us.getId(userid));
	}

	@PutMapping
	@Authorized
	public ResponseEntity<User> updateUser(@RequestBody User u) {
		if (u == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(us.update(u));
	}

}
