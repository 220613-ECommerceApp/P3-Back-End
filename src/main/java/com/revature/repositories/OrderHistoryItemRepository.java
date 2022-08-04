package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.revature.models.OrderHistoryItem;

public interface OrderHistoryItemRepository extends JpaRepository<OrderHistoryItem, Integer> {
    @Query(value = "SELECT * FROM orderhistory_item WHERE user_id = :userId ORDER BY purchase_time DESC", nativeQuery = true)
    public List<OrderHistoryItem> findByUserId(@Param("userId") int id);
}
