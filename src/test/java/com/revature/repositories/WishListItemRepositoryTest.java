package com.revature.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
    
    @MockBean
    private WishListItemRepository underTest;
    @MockBean
    private UserRepository uR;
    @MockBean
    private ProductRepository pR;

    @Test
    void testDeleteProductFromWishList() {
        
    }

    @Test
    void testFindByUserId() {
        // Given
        Product dP = new Product(6, "Ball", 5.79, 23, "", "This ball is for sale!");
        User dU  = new User(2, "billy", "billypass", "billy@me.com");
        WishListItem wLi = new WishListItem(1, 23, dP, dU);

        // When
        pR.save(dP);
        uR.save(dU);
        underTest.save(wLi);

        // Then
        int userId = wLi.getUser().getId();
        Optional<WishListItem> optionalWishListItem = underTest.findByUserId(userId);
        assertThat(optionalWishListItem.isPresent());
    }

    @Test
    void testGetById() {

    }

    @Test
    void testUpdateQuantityInWishList() {

    }
}
