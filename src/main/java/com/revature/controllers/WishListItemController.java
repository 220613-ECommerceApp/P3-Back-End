package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.annotations.Authorized;
import com.revature.dtos.ProductId;
import com.revature.dtos.ProductInfo;
import com.revature.dtos.WishListId;
import com.revature.models.Product;
import com.revature.models.User;
import com.revature.models.WishListItem;
import com.revature.services.WishListItemService;
import com.revature.utils.JWTUtil;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:3000", "http://propanegaming.s3-website.us-east-2.amazonaws.com" }, allowCredentials = "true")
public class WishListItemController {

    private final WishListItemService wishListItemService;

    public WishListItemController(WishListItemService wishListItemService) {
        this.wishListItemService = wishListItemService;
    }

    @Authorized
    @GetMapping("/getWishList")
    public ResponseEntity<List<WishListItem>> getWishList(@RequestHeader("Authorization") String authToken) {
        int id = JWTUtil.verifyUserToken(authToken);
        return ResponseEntity.ok(wishListItemService.findWishListItemsByUserId(id));
    }

    @Authorized
    @PostMapping("/addToWishList")
    public ResponseEntity<String> addItemToWishList(@RequestHeader("Authorization") String authToken, @RequestBody ProductId productId) {
        int id = JWTUtil.verifyUserToken(authToken);
        wishListItemService.addToWishList(productId.getProductId(), id);
        return ResponseEntity.ok("Added product to order history successfully!");
    }

    @Authorized
    @DeleteMapping("/removeFromWishList")
    public ResponseEntity<String> removeFromWishList(@RequestHeader("Authorization") String authToken, @RequestBody WishListId wishListId) {
        int id = JWTUtil.verifyUserToken(authToken);
        wishListItemService.removeWishList(wishListId.getWishListId(), id);
        return ResponseEntity.ok("Deleted item from wish list successfully!");
    }

}
