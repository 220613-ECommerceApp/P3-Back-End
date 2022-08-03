package com.revature.services;

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

	//Don't think we need this but I'll leave it atm
	public CartItem createCart(int userId) {
		CartItem ci = new CartItem();
		ci.setUserId(userId);
		return cartItemRepository.save(ci);
	}
	
	public CartItem addItemToCart(int productId, int userId, int quantity) {
		CartItem ci = cartItemRepository.findByUserId(userId).get();
		ci.setProductId(productId);
		cartItemRepository.updateQuantityInCart(quantity, productId, ci.getUserId());
 		return ci;
	}
	
	public CartItem removeItem(Integer productid, Integer userid) {
		CartItem ci = cartItemRepository.findByUserId(userid).get();
		cartItemRepository.deleteProductFromCart(productid, userid);
		return ci;
		
	}
	
	public CartItem updateItemQuantity(int quantity, int productid, int userid) {
		
		CartItem ci = cartItemRepository.findByUserId(userid).get();
		 cartItemRepository.updateQuantityInCart(quantity, productid, userid);
		 
		return ci;
		 
	}
	
	
 

}
