package com.revature.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.revature.models.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

	Optional<CartItem> findByUserId(int userid);

	// adding an item with initial quantity for a cart's user
	@Modifying
	@Query(value = "INSERT INTO cart_item (user_id, product_id, quantity) VALUES (?1 ,?2 ,?3)", nativeQuery = true)
	void addToCart(int userid, int productid, int quantity);
	
	// custom query to change quantity of an item inside the cart
	@Modifying
	@Query(value = "UPDATE cart_item SET quantity = ?1  WHERE product_id = ?2 AND user_id = ?3", nativeQuery = true)
	void updateQuantityInCart(int quantity, int productid, int userid);

	// custom query to remove an item from the cart
	@Modifying
	@Query(value = "DELETE FROM cart_item WHERE product_id = ?1 AND user_id = ?2", nativeQuery = true)
	void deleteProductFromCart(int productid, int userid);

	CartItem getById(int id);
	
	 @Query(value = "SELECT * FROM cart_item WHERE user_id = ?1", nativeQuery = true)
	    public List<CartItem> findListByUserId(int id);

}
