package com.revature.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.CartItem;
import com.revature.repositories.CartItemRepository;
import com.revature.repositories.UserRepository;

@Service
public class CartItemService {

	@Autowired
	private final CartItemRepository cartItemRepository;

	@Autowired
	private final UserRepository userRepository;

	public CartItemService(CartItemRepository cartItemRepository, UserRepository userRepository) {
		this.cartItemRepository = cartItemRepository;
		this.userRepository = userRepository;
	}

	public CartItem createCart(CartItem cartitem, int userId) {
		CartItem ci = new CartItem();
		ci.setUserId(userId);
		return cartItemRepository.save(ci);

	}
	
	 
	
	public boolean addItemToCart(int productId, int userId, int quantity) {
		CartItem ci = cartItemRepository.findByUserId(userId).get();
		ci.setProductId(productId);
		ci.setQuantity(quantity);
		return false;
	}
	

}
