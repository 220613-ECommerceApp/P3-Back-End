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
import com.revature.models.Tag;
import com.revature.models.ProductTagJunction;

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

@Test
public void findByTagContainingShouldReturnAMatch() {
	Product testProduct = new Product();
	testProduct.setId(0);
	Tag testTag = new Tag();
	testTag.setName("Coding");
	ProductTagJunction testJ = new ProductTagJunction(testProduct, testTag);
	testJ.setId(0);
    List<Product> testList = new ArrayList<>();
	testList.add(testProduct);
	when(productRepository.tagSearch("Coding")).thenReturn(testList);
	
    List<Product> resultList = productService.searchByTag("Coding");
    assertEquals(1, resultList.size());
}

@Test
void findByTagContainingShouldReturnEmptyIfNoMatch() {
   when(productRepository.tagSearch("Coding"))
         .thenReturn(Collections.<Product>emptyList());
   List<Product> resultList = productService.searchByTag("Coding");
   assertEquals(0, resultList.size());
}

@Test
public void findByPriceRangeContainingShouldReturnAMatch() {
	Product testProduct = new Product();
	testProduct.setPrice(911.84);
    List<Product> testList = new ArrayList<>();
	testList.add(testProduct);
	when(productRepository.priceRangeSearch(911, 912)).thenReturn(testList);
	
    List<Product> resultList = productService.searchByPriceRange(911, 912);
    assertEquals(1, resultList.size());
}

@Test
void findByPriceRangeContainingShouldReturnEmptyIfNoMatch() {
   when(productRepository.priceRangeSearch(911, 912))
         .thenReturn(Collections.<Product>emptyList());
   List<Product> resultList = productService.searchByPriceRange(911, 912);
   assertEquals(0, resultList.size());
}


}
