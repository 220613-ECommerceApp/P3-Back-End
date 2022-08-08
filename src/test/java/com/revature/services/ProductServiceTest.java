package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.models.Product;
import com.revature.repositories.ProductRepository;
import com.revature.models.Tag;
import com.revature.dtos.ProductInfo;
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

	@Test
	void findByDescriptionContainingShouldReturnAMatchIfInputExtraSpacesTest() {
		Product testProduct = new Product();
		testProduct.setDescription("A reusable shopping bag");

		List<Product> testList = new ArrayList<>();
		testList.add(testProduct);
		when(productRepository.findByDescriptionContainingIgnoreCase("(A|reusable|bag)")).thenReturn(testList);

		List<Product> resultList = productService.findByDescription("A  reusable bag");

		assertEquals(1, resultList.size());
		assertEquals("A reusable shopping bag", resultList.get(0).getDescription());
	}

	@Test
	void findAllShouldReturnAllProductsTest() {
		List<Product> productList = new ArrayList<>();
		productList.add(new Product(1, "Pro-Pain", 20.00, 5, "String image", "3rd-person shooter"));
		productList.add(new Product(1, "Diablo Immortal", 0.00, 100, "String image", "100% free. WOW, that's GREAT!"));

		when(productRepository.findAll()).thenReturn(productList);

		List<Product> actualProductList = productService.findAll();

		assertEquals(2, actualProductList.size());
		assertEquals(actualProductList, productList);
	}

	@Test
	void findByIdShouldReturnAMatchTest() {
		Product pDummy = new Product(1, "Pro-Pain", 20.00, 5, "String image", "3rd-person shooter");
		int pId = pDummy.getId();

		when(productRepository.findById(pId)).thenReturn(Optional.of(pDummy));
		Optional<Product> pExpected = productService.findById(pId);

		assertEquals(true, pExpected.isPresent());
		assertEquals(pDummy, pExpected.get());
	}

	@Test
	void saveShouldUpdateProductTest() {
		Product pDummy = new Product(1, "Pro-Pain", 20.00, 5, "String image", "3rd-person shooter");
		pDummy.setName("Pro-Pain 2");
		pDummy.setPrice(59.99);
		pDummy.setImage_url("Another image");

		when(productRepository.save(pDummy)).thenReturn(pDummy);

		Product pActual = productService.save(pDummy);

		assertEquals(pDummy, pActual);
	}

	@Test
	void deleteProductTest() {
		Product pDummy = new Product(1, "Pro-Pain", 20.00, 5, "String image", "3rd-person shooter");

		List<Product> pList = new LinkedList<Product>();
		pList.add(pDummy);
		int pDummyId = pList.get(0).getId();

		assertEquals(true, !pList.isEmpty());
		assertEquals(1, pDummyId);

		Mockito.doNothing().when(productRepository).deleteById(pDummyId);

		productService.delete(pDummyId);
		List<Product> actual = new LinkedList<Product>(pList);

		assertEquals(pList.isEmpty(), actual.isEmpty());
	}

	@Test
	void findBySimilarNameDescriptionShouldReturnAMatchTest() {
		Product testProduct = new Product();
		testProduct.setDescription("A reusable shopping bag");
		testProduct.setName("Shopping Bag");

		List<Product> testList = new ArrayList<>();
		testList.add(testProduct);

		when(productRepository.findAll()).thenReturn(testList);
		when(productRepository.findByDescriptionContainingIgnoreCase("(bAg)")).thenReturn(testList);
		when(productRepository.findBySimilarName("(bAg)")).thenReturn(testList);

		Set<Product> resultSet = new HashSet<Product>(productService.findBySimilarNameDescription("bAg"));

		assertEquals(1, resultSet.size());
		assertEquals(true, resultSet.contains(testProduct));
	}

	@Test
	void findBySimilarNameDescriptionShouldReturnEmtpyIfNoMatchTest() {
		when(productRepository.findBySimilarName("(GTA|5)")).thenReturn(Collections.<Product>emptyList());
		Set<Product> resultList = new HashSet<Product>(productService.findBySimilarNameDescription("GTA 5"));
		assertEquals(0, resultList.size());
	}

	@Test
	void findBySimilarNameDescriptionShouldReturnAMatchIfInputExtraSpacesTest() {
		Product testProduct = new Product();
		testProduct.setDescription("A reusable shopping bag");
		testProduct.setName("Shopping Bag");

		List<Product> testList = new ArrayList<>();
		testList.add(testProduct);

		when(productRepository.findAll()).thenReturn(testList);
		when(productRepository.findByDescriptionContainingIgnoreCase("(A|reusable|bag)")).thenReturn(testList);
		when(productRepository.findBySimilarName("(A|reusable|bag)")).thenReturn(testList);

		Set<Product> resultSet = new HashSet<Product>(productService.findBySimilarNameDescription("A  reusable bag"));

		assertEquals(1, resultSet.size());
		assertEquals(true, resultSet.contains(testProduct));
	}

	@Test
	void shouldSaveAll() {
		Product testBag = new Product();
		testBag.setDescription("A reusable shopping bag");
		testBag.setName("Shopping Bag");

		Product testHat = new Product();
		testHat.setDescription("A hat that Bryan wants");
		testHat.setName("Fancy Hat");

		List<Product> testList = new ArrayList<>();
		testList.add(testHat);
		testList.add(testBag);

		when(productRepository.saveAll(testList)).thenReturn(testList);

		List<ProductInfo> metaDataList = new ArrayList<>();

		List<Product> resultList = productService.saveAll(testList, metaDataList);
		assertEquals(testList, resultList);
	}

	@Test
	void findBySimilarNameDescriptionShouldReturnAnExtraMatch() {
		Product testProduct = new Product();
		testProduct.setDescription("A reusable shopping bag");
		testProduct.setName("Cap");

		Product testHat = new Product();
		testHat.setDescription("A hat that Bryan wants");
		testHat.setName("Coat");

		List<Product> testList = new ArrayList<>();
		testList.add(testHat);
		testList.add(testProduct);

		when(productRepository.findAll()).thenReturn(testList);
		when(productRepository.findByDescriptionContainingIgnoreCase("(Cap)")).thenReturn(testList);
		when(productRepository.findBySimilarName("(Cap)")).thenReturn(testList);

		Set<Product> resultSet = new HashSet<Product>(productService.findBySimilarNameDescription("Cap"));

		assertEquals(2, resultSet.size());
		assertEquals(true, resultSet.contains(testProduct));
	}

	@Test
	void superSearchShouldReturnAMatchIfInputExtraSpacesTest() {
		Product testProduct = new Product();
		testProduct.setDescription("A reusable shopping bag");
		testProduct.setName("Shopping Bag");

		List<Product> testList = new ArrayList<>();
		testList.add(testProduct);

		when(productRepository.findAll()).thenReturn(testList);
		when(productRepository.superSearchWithoutTag(0.0, 100, "(A|reusable|bag)")).thenReturn(testList);

		Set<Product> resultSet = new HashSet<Product>(productService.superSearch(0.0, 100, "NULL", "A  reusable bag"));

		assertEquals(1, resultSet.size());
		assertEquals(true, resultSet.contains(testProduct));
	}
}
