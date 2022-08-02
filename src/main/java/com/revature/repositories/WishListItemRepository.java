package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.models.WishListItem;

public interface WishListItemRepository extends JpaRepository<WishListItem, Integer>{
    
}
