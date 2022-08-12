package com.revature.services;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

import com.revature.models.OrderHistoryItem;
import com.revature.repositories.OrderHistoryItemRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class OrderHistoryServiceTest {
    @Autowired
    private OrderHistoryItemRepository orderHistoryItemRepository;

    @Test
    public void testFindByUserIdReturnsOrderHistory(){
        List<OrderHistoryItem> orders = orderHistoryItemRepository.findByUserId(1);
        assertEquals(5, orders.size());
    }

}
