package com.revature.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.Authorized;
import com.revature.models.OrderHistoryItem;
import com.revature.models.User;
import com.revature.models.WishListItem;
import com.revature.services.OrderHistoryService;
import com.revature.services.WishListItemService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class WishListItemController {

    private final WishListItemService wishListItemService;

    public WishListItemController(WishListItemService wishListItemService) {
        this.wishListItemService = wishListItemService;
    }

    @Authorized
    @GetMapping("/wishlist")
    public ResponseEntity<List<WishListItem>> getWishList(HttpSession session) {
        User u = (User) session.getAttribute("user");
        return ResponseEntity.ok(wishListItemService.findById(u.getId()));
    }
}
