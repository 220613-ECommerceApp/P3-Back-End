package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.models.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

}
