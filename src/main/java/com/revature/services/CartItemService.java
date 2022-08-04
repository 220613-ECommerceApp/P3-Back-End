package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.CartItem;
import com.revature.models.User;
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

	//Might not need this
	public CartItem createCart(int userId) {
		User u = userRepository.findById(userId);
		CartItem ci = new CartItem();
		ci.setUser(u);
		return cartItemRepository.save(ci);
	}
	
	public CartItem addItemToCart(int productid, int userId, int quantity) {
		cartItemRepository.addToCart(userId, productid,quantity);
		CartItem ci = cartItemRepository.findByUserIdAndProductId(userId, productid);
 		return ci;
	}
	
	public CartItem removeItem(int productid, int userid) {
		cartItemRepository.deleteProductFromCart(productid, userid);
		CartItem ci = cartItemRepository.findByUserIdAndProductId(userid, productid);
		return ci;
		
	}
	
	public CartItem updateItemQuantity(int quantity, int productid, int userid) {
		 cartItemRepository.updateQuantityInCart(quantity, productid, userid);
		CartItem ci = cartItemRepository.findByUserIdAndProductId(userid,productid);
		return ci;
		 
	}
	
	public List<CartItem> getByUserId(int id){
		return cartItemRepository.findListByUserId(id);
	}
	
	public CartItem clearCart(int productid, int userid) {
		cartItemRepository.clearCartForUser(userid);
		CartItem ci = cartItemRepository.findByUserIdAndProductId(userid, productid);
		return ci;
	}
 

}
