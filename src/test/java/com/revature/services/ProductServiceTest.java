package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.models.Product;
import com.revature.repositories.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
   @Mock
   private ProductRepository productRepository;

   private ProductService productService;

   @BeforeEach
   void beforeEach() {
      productService = new ProductService(productRepository);
   }

   @Test
   void findByDescriptionContainingShouldReturnAMatch() {
      Product testProduct = new Product();
      testProduct.setDescription("A reusable shopping bag");

      List<Product> testList = new ArrayList<>();
      testList.add(testProduct);
      when(productRepository.findByDescriptionContainingIgnoreCase("(bAg)")).thenReturn(testList);

      List<Product> resultList = productService.findByDescription("bAg");
      assertEquals(1, resultList.size());
      assertEquals("A reusable shopping bag", resultList.get(0).getDescription());
   }

   @Test
   void findByDescriptionContainingShouldReturnEmtpyIfNoMatch() {
      when(productRepository.findByDescriptionContainingIgnoreCase("(GTA|5)"))
            .thenReturn(Collections.<Product>emptyList());
      List<Product> resultList = productService.findByDescription("GTA 5");
      assertEquals(0, resultList.size());
   }
}
