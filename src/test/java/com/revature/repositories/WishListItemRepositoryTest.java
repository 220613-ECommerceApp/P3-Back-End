package com.revature.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
import com.revature.services.UserService;
import com.revature.services.WishListItemService;

@DataJpaTest
public class WishListItemRepositoryTest {

    @MockBean
    private WishListItemRepository underRepoTest;
    @MockBean
    private WishListItemService wishListItemService;
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
        User dummyUser = new User(0, "billy123", "billypass", "billy@me.com");
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
    void testDeleteProductFromWishList() {
        
    }

    @Test
    void testFindByUserId() {
        System.out.print(uR.getById(1));
        uR.save(dummyUser);
        wishListItemService.addToWishList(dummyProduct, dummyUser, 2);

        when(methodCall).thenreturn();
        
        assertEquals(dummyUser, underRepoTest.findByUserId(0));
    }

    @Test
    void testGetById() {

    }

    @Test
    void testUpdateQuantityInWishList() {

    }
}
