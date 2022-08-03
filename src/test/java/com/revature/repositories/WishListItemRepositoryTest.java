package com.revature.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.revature.services.WishListItemService;

@DataJpaTest
public class WishListItemRepositoryTest {

    
    private WishListItemService wishListItemService;

    @BeforeEach
    private void setup() {

    }
    
    @AfterEach
    private void tearDown() {

    }

    @Test
    void testDeleteProductFromWishList() {

    }

    @Test
    void testFindByUserId() {

    }

    @Test
    void testGetById() {

    }

    @Test
    void testUpdateQuantityInWishList() {

    }
}
