package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.revature.models.OrderHistoryItem;

public interface OrderHistoryItemRepository extends JpaRepository<OrderHistoryItem, Integer> {
    @Query(value = "SELECT * FROM orderhistory_item WHERE user_id = :userId ORDER BY purchase_time DESC", nativeQuery = true)
    public List<OrderHistoryItem> findByUserId(@Param("userId") int id);

    @Modifying
    @Query(value = "INSERT INTO orderhistory_item (product_id, user_id, quantity, purchase_time) VALUES (?1, ?2, ?3, CURRENT_TIMESTAMP)", nativeQuery = true)
    public void addToOrderHistory(int productId, int userId, int quantity);
}
