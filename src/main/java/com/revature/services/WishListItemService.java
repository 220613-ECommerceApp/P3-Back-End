package com.revature.services;

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

    @Autowired
    private final WishListItemRepository wishListItemRepository;

    @Autowired
    private final UserRepository userRepository;
    
    public WishListItemService(WishListItemRepository wishListItemRepository, UserRepository userRepository){
        this.wishListItemRepository = wishListItemRepository;
        this.userRepository = userRepository;
    }

    public WishListItem createWishList(Product product, User user){
        WishListItem wLi = new WishListItem();
        wLi.setUser(user);
        return wishListItemRepository.save(wLi);
    }

    public WishListItem addToWishList(Product product, User user, int quantity){
        WishListItem wLi = wishListItemRepository.getById(user.getId());
        wLi.setProduct(product);
        wishListItemRepository.updateQuantityInWishList(quantity, wLi.getId(), product, wLi.getUser());
        return wLi;
    }

    public Optional<WishListItem> findWishListById(int id){
        return wishListItemRepository.findById(id);
    }

    public boolean removeWishListItem(Integer wishListId){
        wishListItemRepository.deleteById(wishListId);
        return true;
    }


    public WishListItem updateItemQuantity(int quantity, int wishListId, Product product, User user){
        WishListItem wLi = wishListItemRepository.getById(wishListId);
        wishListItemRepository.updateQuantityInWishList(quantity, wishListId, product, user);
        return wLi;
    }

}


