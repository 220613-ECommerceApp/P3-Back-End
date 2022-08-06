package com.revature.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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

@DataJpaTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class WishListItemRepositoryTest {
    
    @Autowired
    private WishListItemRepository underTest;
    @Autowired
    private UserRepository uR;
    @Autowired
    private ProductRepository pR;

    @Test
    void testDeleteProductFromWishList() {
        // Given
        Product dP = new Product(6, "Ball", 5.79, 23, "", "This ball is for sale!");
        User dU  = new User(2, "billy", "billypass", "billy@me.com", null, null);
        WishListItem wLi = new WishListItem(1, 23, dP, dU);
        // List<WishListItem> wishList = new ArrayList<>(wLi);
        

        // When
        pR.save(dP);
        uR.save(dU);
        underTest.save(wLi);

        // Then
        int wId = wLi.getId();
        int pId = dP.getId();
        int uId = dU.getId();
        underTest.deleteProductFromWishList(wId, pId, uId);
        assertThat(wLi).isNull();
    }

    @Test
    void testFindByUserId() {
        // Given
        Product dP = new Product(6, "Ball", 5.79, 23, "", "This ball is for sale!");
        User dU  = new User(2, "billy", "billypass", "billy@me.com", null, null);
        WishListItem wLi = new WishListItem(1, 23, dP, dU);

        // When
        pR.save(dP);
        uR.save(dU);
        underTest.save(wLi);

        // Then
        int userId = wLi.getUser().getId();
        int wId = wLi.getId();
        Optional<WishListItem> optionalWishListItem = underTest.findByUserId(userId);
        assertThat(optionalWishListItem)
            .isPresent()
            .hasValueSatisfying(w -> {
                assertThat(w.getId()).isEqualTo(wId);
                assertThat(w.getQuantity()).isEqualTo(23);
                assertThat(w.getProduct()).isEqualTo(dP);
                assertThat(w.getUser()).isEqualTo(dU);
                assertThat(w).isSameAs(wLi);
                assertThat(w).isSameAs(wLi);
            });
    }


    // @Test
    // void testGetById() {

    // }

    // @Test
    // void testUpdateQuantityInWishList() {

    // }
}
