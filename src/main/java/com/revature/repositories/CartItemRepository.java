package com.revature.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.revature.models.CartItem;
import com.revature.models.OrderHistoryItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

	Optional<CartItem> findByUserId(int userid);

	// custom query to change quantity of an item inside the cart
	@Modifying
	@Query(value = "UPDATE cart_item c set c.quantity = ?  WHERE c.product_id = ? AND c.user_id = ?", nativeQuery = true)
	void updateQuantityInCart(Integer quantity, Integer productid, Integer userid);

	// custom query to remove an item from the cart
	@Modifying
	@Query(value = "DELETE FROM cart_item c WHERE c.product_id = ? AND c.user_id = ?", nativeQuery = true)
	void deleteProductFromCart(Integer productid, Integer userid);

	CartItem getById(int id);
	
	 @Query(value = "SELECT * FROM cart_item WHERE user_id = ?", nativeQuery = true)
	    public List<CartItem> findListByUserId(Integer id);

}
