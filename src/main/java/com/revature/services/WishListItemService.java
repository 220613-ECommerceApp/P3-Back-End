package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.revature.dtos.ProductInfo;
import com.revature.models.WishListItem;
import com.revature.repositories.WishListItemRepository;

@Service
public class WishListItemService {

    private final WishListItemRepository wishListItemRepository;
    
    public WishListItemService(WishListItemRepository wishListItemRepository){
        this.wishListItemRepository = wishListItemRepository;
    }

    public List<WishListItem> findAllWishListItem(){
        return wishListItemRepository.findAll();
    }

    public Optional<WishListItem> findWishListItemById(int id){
        return wishListItemRepository.findById(id);
    }

    public WishListItem saveWishListItem(WishListItem wishListItem){
        return wishListItemRepository.save(wishListItem);
    }

    public List<WishListItem> saveWishList(List<WishListItem> wishList, List<ProductInfo> metadata){
        return wishListItemRepository.saveAll(wishList);
    }

    public void deleteWishListItem(int id){
        wishListItemRepository.deleteById(id);
    }

}
