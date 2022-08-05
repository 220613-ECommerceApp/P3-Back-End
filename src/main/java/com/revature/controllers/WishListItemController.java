package com.revature.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.Authorized;
import com.revature.models.WishListItem;
import com.revature.services.WishListItemService;
import com.revature.utils.JWTUtil;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:3000" }, allowCredentials = "true")
public class WishListItemController {

    private final WishListItemService wishListItemService;

    public WishListItemController(WishListItemService wishListItemService) {
        this.wishListItemService = wishListItemService;
    }

    @Authorized
    @GetMapping("/wishlist")
    public ResponseEntity<Optional<WishListItem>> getWishList(@RequestHeader("Authorization") String authToken) {
        int id = JWTUtil.verifyUserToken(authToken);
        return ResponseEntity.ok(wishListItemService.findWishListById(id));
    }

    // @Authorized
    // @PutMapping("/wishlist")
    // public ResponseEntity<WishListItem> addItemToWishList(@RequestHeader("Authorization") String authToken, Product product, User user, int quantity) {
    //     int id = JWTUtil.verifyUserToken(authToken);
    //     return ResponseEntity.ok(wishListItemService.addToWishList(product, user, quantity));
    // }

    @Authorized
    @PutMapping("/wishlist")
    public ResponseEntity<Boolean> removeWishListItem(@RequestHeader("Authorization") String authToken, Integer wishListId) {
        int id = JWTUtil.verifyUserToken(authToken);
        return ResponseEntity.ok(wishListItemService.removeWishListItem(wishListId));
    }

}
