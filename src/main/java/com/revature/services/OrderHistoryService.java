package com.revature.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.revature.repositories.OrderHistoryItemRepository;
import com.revature.models.OrderHistoryItem;

@Service
public class OrderHistoryService {
    private final OrderHistoryItemRepository orderHistoryItemRepository;

    public OrderHistoryService(OrderHistoryItemRepository orderHistoryItemRepository) {
        this.orderHistoryItemRepository = orderHistoryItemRepository;
    }
    
    public List<OrderHistoryItem> findByUserId(int id){
        return orderHistoryItemRepository.findByUserId(id);
    }

    @Transactional
    public void addToOrderHistory(List<OrderHistoryItem> orderInfoList){
        for(OrderHistoryItem o : orderInfoList)
            orderHistoryItemRepository.addToOrderHistory(o.getProduct().getId(), o.getUser().getId(), o.getQuantity());
    }
}
