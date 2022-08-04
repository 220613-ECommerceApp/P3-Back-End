package com.revature.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.Authorized;
import com.revature.models.CartItem;
import com.revature.services.CartItemService;
import com.revature.utils.JWTUtil;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:3000", "http://propanegaming.s3-website.us-east-2.amazonaws.com" }, allowCredentials = "true")
public class CartItemController {

	private final CartItemService cis;

	public CartItemController(CartItemService cis) {
		this.cis = cis;
	}

	@Authorized
	@Transactional
	@PostMapping("/addtocart/{productid}")
	public ResponseEntity<CartItem> addToCart(@PathVariable("productid") int productid,
			@RequestHeader("Authorization") String token, @RequestHeader int quantity) {
		int userid = JWTUtil.verifyUserToken(token);
		CartItem ci = cis.addItemToCart(productid, userid, quantity);
		return new ResponseEntity<CartItem>(ci, HttpStatus.OK);
	}

	// All counts of a single item
	@Authorized
	@Transactional
	@PostMapping("/removefromcart/{productid}")
	public ResponseEntity<CartItem> removeFromCart(@PathVariable("productid") int productid,
			@RequestHeader("Authorization") String token) {
		int userid = JWTUtil.verifyUserToken(token);
		CartItem ci = cis.removeItem(productid, userid);
		return new ResponseEntity<CartItem>(ci, HttpStatus.OK);
	}

	// Quantity = the new updated quantity
	@Authorized
	@Transactional
	@PutMapping("/updatecart/{productid}")
	public ResponseEntity<CartItem> changeQuantity(@PathVariable("productid") int productid,
			@RequestHeader("Authorization") String token, @RequestHeader int quantity) {
		int userid = JWTUtil.verifyUserToken(token);
		CartItem ci = cis.updateItemQuantity(quantity, productid, userid);
		return new ResponseEntity<CartItem>(ci, HttpStatus.OK);
	}

	@Authorized
	@GetMapping
	public ResponseEntity<List<CartItem>> getCart(@RequestHeader("Authorization") String token) {
		int userid = JWTUtil.verifyUserToken(token);
		return ResponseEntity.ok(cis.getByUserId(userid));
	}

	@Authorized
	@Transactional
	@DeleteMapping("/clear")
	public ResponseEntity<List<CartItem>> clearTheCart(@RequestHeader("Authorization") String token) {
		int userid = JWTUtil.verifyUserToken(token);
		return ResponseEntity.ok(cis.clearCart(userid));
	}

}
