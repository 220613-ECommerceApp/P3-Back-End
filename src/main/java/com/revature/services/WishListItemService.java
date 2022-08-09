package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void addToWishList(int productId, int userId){
        wishListItemRepository.addProductToWishlist(productId, userId);
    }

    public List<Product> findWishListItemsByUserId(int userId){
        return convertWishListItemToProduct(wishListItemRepository.findByUserId(userId));
    }

    private List<Product> convertWishListItemToProduct(List<WishListItem> wishListItems){
        List<Product> products = new ArrayList<>();

        for(WishListItem w : wishListItems)
            products.add(w.getProduct());

        return products;
    }
    /* 
    public boolean removeWishListItem(Integer wishListId){
        wishListItemRepository.deleteById(wishListId);
        return true;
    }

    
    public WishListItem updateItemQuantity(int quantity, int wishListId, Product product, User user){
        WishListItem wLi = wishListItemRepository.getById(wishListId);
        wishListItemRepository.updateQuantityInWishList(quantity, wishListId, product, user);
        return wLi;
    }*/

}


