package com.revature.services;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

import com.revature.models.Product;
import com.revature.models.User;
import com.revature.models.WishListItem;
import com.revature.repositories.UserRepository;
import com.revature.repositories.WishListItemRepository;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class WishListItemServiceTest {

    @Autowired
    private WishListItemService underTest;
    @Autowired
    private WishListItemRepository wR;
    @Autowired
    private UserRepository uR;
    @Autowired
    private UserService uS;

    @BeforeEach
    private static void setup() {
    
        Product dP = new Product(6, "Ball", 5.79, 23, "", "This ball is for sale!");
        User dU  = new User(2, "billy", "billypass", "billy@me.com", null, null);
        WishListItem wLi = new WishListItem(1, 23, dP, dU);
        List<WishListItem> underTest = new LinkedList<>();
    }
    
    @AfterEach
    private static void tearDown() {
        
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
