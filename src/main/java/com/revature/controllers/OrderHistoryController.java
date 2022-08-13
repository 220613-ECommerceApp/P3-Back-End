package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.dtos.OrderInfo;
import com.revature.dtos.ProductInfo;
import com.revature.models.OrderHistoryItem;
import com.revature.models.Product;
import com.revature.models.User;
import com.revature.services.OrderHistoryService;
import com.revature.utils.JWTUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:3000",
        "http://propanegaming.s3-website.us-east-2.amazonaws.com" }, allowCredentials = "true")
public class OrderHistoryController {

    @Autowired
    private OrderHistoryService orderHistoryService;

    @Authorized
    @GetMapping("/orderHistory")
    public ResponseEntity<List<List<OrderInfo>>> getOrderHistory(@RequestHeader("Authorization") String authToken) {
        int id = JWTUtil.verifyUserToken(authToken);
        return ResponseEntity.ok(orderHistoryService.findByUserId(id));
    }

    @Authorized
    @PostMapping("/orderHistory")
    public ResponseEntity<List<ProductInfo>> postOrderHistory(@RequestHeader("Authorization") String authToken,
            @RequestBody List<ProductInfo> productInfos) {
        int id = JWTUtil.verifyUserToken(authToken);
        orderHistoryService.addToOrderHistory(convertToEntity(id, productInfos));
        return ResponseEntity.ok(productInfos);
    }

    private OrderHistoryItem convertToEntity(int userId, ProductInfo productInfo) {
        return new OrderHistoryItem(
                0,
                new Product(productInfo.getId(), null, 0, 0, null, null),
                new User(userId, null, null, null, null, null),
                productInfo.getQuantity(),
                null);
    }

    private List<OrderHistoryItem> convertToEntity(int userId, List<ProductInfo> productInfo) {
        List<OrderHistoryItem> orders = new ArrayList<>();

        for (ProductInfo p : productInfo)
            orders.add(convertToEntity(userId, p));

        return orders;
    }

}
