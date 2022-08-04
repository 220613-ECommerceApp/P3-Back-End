package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.models.OrderHistoryItem;
import com.revature.services.OrderHistoryService;
import com.revature.utils.JWTUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class OrderHistoryController {

    private final OrderHistoryService orderHistoryService;

    public OrderHistoryController(OrderHistoryService orderHistoryService) {
        this.orderHistoryService = orderHistoryService;
    }

    @Authorized
    @GetMapping("/orderHistory")
    public ResponseEntity<List<OrderHistoryItem>> getOrderHistory(@RequestHeader("Authorization") String authToken) {
        int id = JWTUtil.verifyUserToken(authToken);
        return ResponseEntity.ok(orderHistoryService.findByUserId(id));
    }

}
