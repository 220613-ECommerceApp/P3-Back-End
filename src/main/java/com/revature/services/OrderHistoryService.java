package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.repositories.OrderHistoryItemRepository;
import com.revature.dtos.OrderInfo;
import com.revature.models.OrderHistoryItem;

@Service
public class OrderHistoryService {

    @Autowired
    private OrderHistoryItemRepository orderHistoryItemRepository;

    public List<List<OrderInfo>> findByUserId(int id) {
        List<OrderHistoryItem> orders = orderHistoryItemRepository.findByUserId(id);
        List<List<OrderInfo>> list = new ArrayList<>();

        list.add(new ArrayList<OrderInfo>());
        list.get(0).add(convertToDto(orders.get(0)));

        for (int i = 1; i < orders.size(); ++i) {
            if (!orders.get(i - 1).getPurchaseTime().equals(orders.get(i).getPurchaseTime())) {
                list.add(new ArrayList<OrderInfo>());
            }
            list.get(list.size() - 1).add(convertToDto(orders.get(i)));
        }

        return list;
    }

    @Transactional
    public void addToOrderHistory(List<OrderHistoryItem> orderInfoList) {
        for (OrderHistoryItem o : orderInfoList)
            orderHistoryItemRepository.addToOrderHistory(o.getProduct().getId(), o.getUser().getId(), o.getQuantity());
    }

    private OrderInfo convertToDto(OrderHistoryItem orderHistoryItem) {
        return new OrderInfo(
                orderHistoryItem.getId(),
                orderHistoryItem.getProduct(),
                orderHistoryItem.getQuantity(),
                orderHistoryItem.getPurchaseTime());
    }
}
