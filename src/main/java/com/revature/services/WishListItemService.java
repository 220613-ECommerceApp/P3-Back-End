package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Product;
import com.revature.models.User;
import com.revature.models.WishListItem;
import com.revature.repositories.UserRepository;
import com.revature.repositories.WishListItemRepository;

@Service
public class WishListItemService {

    private final WishListItemRepository wishListItemRepository;

    
    public WishListItemService(WishListItemRepository wishListItemRepository){
        this.wishListItemRepository = wishListItemRepository;
    }

    public WishListItem createWishList(Product product, User user){
        WishListItem wLi = new WishListItem();
        wLi.setUser(user);
        return wishListItemRepository.save(wLi);
    }

    @Transactional
    public void addToWishList(int productId, int userId){
        wishListItemRepository.addProductToWishlist(productId, userId);
    }

    public List<WishListItem> findWishListItemsByUserId(int userId){
        return wishListItemRepository.findByUserId(userId);
    }

    
    public boolean removeWishListItem(Integer wishListId){
        wishListItemRepository.deleteById(wishListId);
        return true;
    }
    /*
    
    public WishListItem updateItemQuantity(int quantity, int wishListId, Product product, User user){
        WishListItem wLi = wishListItemRepository.getById(wishListId);
        wishListItemRepository.updateQuantityInWishList(quantity, wishListId, product, user);
        return wLi;
    }*/

}


