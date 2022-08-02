package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dtos.ProductInfo;
import com.revature.models.WishListItem;
import com.revature.repositories.UserRepository;
import com.revature.repositories.WishListItemRepository;

@Service
public class WishListItemService {

    @Autowired
    private final WishListItemRepository wishListItemRepository;

    @Autowired
    private final UserRepository userRepository;
    
    public WishListItemService(WishListItemRepository wishListItemRepository, UserRepository userRepository){
        this.wishListItemRepository = wishListItemRepository;
        this.userRepository = userRepository;
    }

    public WishListItem createWishList(WishListItem wishListItem, int userId){
        WishListItem wLi = new WishListItem();
        wLi.setUserId(userId);
        return wishListItemRepository.save(wLi);
    }

    public WishListItem addToWishList(int productId, int userId, int quantity){
        WishListItem wLi = wishListItemRepository.findByuserId(userId).get();
        wLi.setProductId(productId);
        wishListItemRepository.updateQuantityInCart(quantity, wLi.getId(), productId, wLi.getUserId());
        return wLi;
    }

    public WishListItem removeWishListItem(Integer wishListId, Integer productId, Integer userId){
        WishListItem wLi = wishListItemRepository.getById(wishListId);
        wishListItemRepository.deleteProductFromWishList(wishListId, productId, userId);
        return wLi;
    }

    public WishListItem updateItemQuantity(int quantity, int wishListId, int productId, int userId){
        WishListItem wLi = wishListItemRepository.getById(wishListId);
        wishListItemRepository.updateQuantityInWishList(quantity, wishListId, productId, userId);
        return wLi;
    }

}


