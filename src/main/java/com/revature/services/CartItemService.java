package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.repositories.CartItemRepository;
import com.revature.repositories.UserRepository;

@Service
public class CartItemService {

	@Autowired
	private final CartItemRepository cartItemRepository;

    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }
	
    
	
}
