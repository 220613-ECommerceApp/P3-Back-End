package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.revature.models.WishListItem;

public interface WishListItemRepository extends JpaRepository<WishListItem, Integer>{
    
    @Query(value = "SELECT * FROM wishlist_item WHERE user_id = :userId", nativeQuery = true)
    List<WishListItem> findByUserId(@Param("userId") int id);

    @Modifying
    @Query(value = "INSERT INTO wishlist_item (product_id, user_id, quantity) VALUES (?1, ?2, 1)", nativeQuery = true)
    void addProductToWishlist(int productId, int userId);

    @Modifying
    @Query(value = "DELETE FROM wishlist_item w WHERE w.id = ?1", nativeQuery = true)
    void deleteProductFromWishList(int wishListId);

}

