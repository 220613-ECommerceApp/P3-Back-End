package com.revature.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.revature.models.Product;
import com.revature.models.User;
import com.revature.models.WishListItem;

public interface WishListItemRepository extends JpaRepository<WishListItem, Integer>{
    
    Optional<WishListItem> findByUserId(int userId);

    // custom query to change quantity of an item inside the wish list
    @Modifying
    @Query
    (value = "UPDATE wishlist_item w SET w.quantity = ? WHERE w.id = ? AND w.product_id = ? AND w.user_id = ?", nativeQuery = true)
    void updateQuantityInWishList(Integer quantity, Integer wishListId, Product product, User user);

    @Modifying
    @Query
    (value = "DELETE FROM wishlist_item w WHERE w.id = ? AND w.product_id = ? AND w.user_id = ?", nativeQuery = true)
    void deleteProductFromWishList(Integer wishListId, Integer productId, Integer userId);

    WishListItem getById(int id);


}

