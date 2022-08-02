package com.revature.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.revature.models.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

	Optional<CartItem> findByUserId(int userid);
	
	//custom query to change quantity of an item inside the cart
	@Modifying
	@Query(value = "UPDATE cart_item c set c.quantity = ?  WHERE c.id = ? AND c.product_id = ? AND c.user_id = ?", nativeQuery = true)
	void updateQuantityInCart(Integer quantity, Integer cartd, Integer productid, Integer userid);
	
	//custom query to change quantity of an item inside the cart
//		@Modifying
//		@Query(value = "UPDATE cart_item c set c.quantity = ?  WHERE c.id = ? AND c.product_id = ? AND c.user_id = ?", nativeQuery = true)
//		void updateQuantityInCart(Integer quantity, Integer cartd, Integer productid, Integer userid);
	
	CartItem getById(int id);
	
}
