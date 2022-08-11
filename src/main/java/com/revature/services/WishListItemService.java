package com.revature.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Product;
import com.revature.models.User;
import com.revature.models.WishListItem;
import com.revature.repositories.WishListItemRepository;

@Service
public class WishListItemService {

    @Autowired
    private WishListItemRepository wishListItemRepository;

    public WishListItem createWishList(Product product, User user) {
        WishListItem wLi = new WishListItem();
        wLi.setUser(user);
        return wishListItemRepository.save(wLi);
    }

    @Transactional
    public void addToWishList(int productId, int userId) {
        wishListItemRepository.addProductToWishlist(productId, userId);
    }

    public List<WishListItem> findWishListItemsByUserId(int userId) {
        return wishListItemRepository.findByUserId(userId);
    }

    @Transactional
    public void removeFromWishList(int wishListId){
        wishListItemRepository.deleteProductFromWishList(wishListId);
    }

}
