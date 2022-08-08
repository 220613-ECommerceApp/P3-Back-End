package com.revature.services;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.models.Product;
import com.revature.models.User;
import com.revature.models.WishListItem;
import com.revature.repositories.UserRepository;
import com.revature.repositories.WishListItemRepository;

@DataJpaTest
public class WishListItemServiceTest {

    private WishListItemService wishListItemService;

    @Autowired
    private WishListItemRepository underRepoTest;
    @MockBean
    private UserRepository uR;
    @MockBean
    private UserService uS;
    
    private User dummyUser;
    private Product dummyProduct;
    private WishListItem dummyWli;
    private List<WishListItem> dummyWishList;

    @BeforeEach
    private void setup() {
    
        User dummyUser = new User("billy123", "billypass", "billy@me.com");
        Product dummyProduct = new Product(1, "Pro-Pain", 29.85, 3, "", "Propane Game of the Year");
        WishListItem dummyWli = new WishListItem(1, 23, dummyProduct, dummyUser);
        List<WishListItem> dummyWishList = new LinkedList<>();
    }
    
    @AfterEach
    private void tearDown() {
        dummyUser = null;
        dummyProduct = null;
        dummyWli = null;
        dummyWishList = null;
        dummyWishList = null;
    }
    
    @Test
    void testAddToWishList() {

    }

    @Test
    void testCreateWishList() {

    }

    @Test
    void testFindWishListById() {

    }

    @Test
    void testRemoveWishListItem() {

    }

    @Test
    void testUpdateItemQuantity() {

    }
}
