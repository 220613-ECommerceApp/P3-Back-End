package com.revature.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.repositories.OrderHistoryItemRepository;
import com.revature.models.OrderHistoryItem;

@Service
public class OrderHistoryItemService {
    private final OrderHistoryItemRepository orderHistoryItemRepository;

    public OrderHistoryItemService(OrderHistoryItemRepository orderHistoryItemRepository) {
        this.orderHistoryItemRepository = orderHistoryItemRepository;
    }
    
    public List<OrderHistoryItem> findById(int id){
        return orderHistoryItemRepository.findByUserId(id);
    }
}
