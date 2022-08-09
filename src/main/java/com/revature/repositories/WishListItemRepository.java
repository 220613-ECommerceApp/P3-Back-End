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
    (value = "DELETE FROM wishlist_item w WHERE w.id = ?", nativeQuery = true)
    void deleteProductFromWishList(Integer wishListId, Integer product, Integer user);

    WishListItem getById(int id);


}

