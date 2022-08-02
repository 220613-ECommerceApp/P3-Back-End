package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.dtos.LoginRequest;
import com.revature.dtos.RegisterRequest;
import com.revature.models.OrderHistoryItem;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.OrderHistoryService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<OrderHistoryItem>> getOrderHistory(HttpSession session) {
        User u = (User) session.getAttribute("user");
        return ResponseEntity.ok(orderHistoryService.findById(u.getId()));
    }

}
