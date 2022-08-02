package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.revature.models.OrderHistoryItem;

public interface OrderHistoryItemRepository extends JpaRepository<OrderHistoryItem, Integer> {
    public List<OrderHistoryItem> findByUserId(int id);


}
