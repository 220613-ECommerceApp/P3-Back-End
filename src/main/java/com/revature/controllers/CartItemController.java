package com.revature.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.CartItem;
import com.revature.services.CartItemService;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:3000" }, allowCredentials = "true")
public class CartItemController {

	
	private final CartItemService cis;

	public CartItemController(CartItemService cis) {
		this.cis = cis;
	}
	
	@PostMapping("/addtocart/{productid}")
	public ResponseEntity<CartItem> addToCart(@PathVariable("productid") Integer productid, @RequestBody int userid, @RequestBody int quantity) {
		CartItem ci = cis.addItemToCart(productid, userid, quantity);
		return new ResponseEntity<CartItem>(ci, HttpStatus.OK);
	}
	
	//All counts of a single item
	@PostMapping("/removefromcart/{productid}")
	public ResponseEntity<CartItem> removeFromCart(@PathVariable("productid") Integer productid, @RequestBody int userid) {
		CartItem ci = cis.removeItem(productid, userid);
		return new ResponseEntity<CartItem>(ci, HttpStatus.OK);
	}
	
	//Quantity = the new updated quantity
		@PutMapping("/updatecart/{productid}")
		public ResponseEntity<CartItem> changeQuantity(@PathVariable("productid") Integer productid, @RequestBody int userid, @RequestBody int quantity) {
			CartItem ci = cis.updateItemQuantity(quantity, productid, userid);
			return new ResponseEntity<CartItem>(ci, HttpStatus.OK);
		}
	
	
	
}