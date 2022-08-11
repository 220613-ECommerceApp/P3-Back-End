package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.models.CartItem;
import com.revature.models.Product;
import com.revature.models.User;
import com.revature.repositories.CartItemRepository;
import com.revature.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class CartItemServiceTest
{
	
	@Mock
	private CartItemRepository mockCartRepo;
	
	@Mock
	private UserRepository mockUserRepo;
	
	@InjectMocks
	CartItemService cartService;
	
	private Product dummyProduct1;
	private Product dummyProduct2;
	private User dummyUser;
	private CartItem dummyItem1;
	private CartItem dummyItem2;
	private List<CartItem> dummyList;
	
	@BeforeEach
	public void setup()
	{
		dummyProduct1 = new Product(1, "Cool Game", 12.25, 6, "https:/imageURL", "This Game is cool");
		dummyProduct2 = new Product(2, "Fun Game", 14.25, 8, "https:/imageURL3", "This Game is fun");
		dummyUser = new User(1, "John", "Doe", "username", "password", "username@email.com");
		
		dummyItem1 = new CartItem(0, 0, dummyProduct1, dummyUser);
		dummyItem2 = new CartItem(0, 0, dummyProduct2, dummyUser);
		
		dummyList = new ArrayList<CartItem>();
		dummyList.add(dummyItem1);
		dummyList.add(dummyItem2);
	}
	
	@Test
	public void TestGetByUserID()
	{
		when(mockCartRepo.findListByUserId(1)).thenReturn(dummyList);
		List<CartItem> returnedList = cartService.getByUserId(1);
		assertEquals(returnedList, dummyList);
		
	}
	
	@Test
	public void TestAddItem()
	{
		when(mockCartRepo.addToCart(1, 1, 2)).thenReturn(true);
		when(mockCartRepo.findByUserIdAndProductId(1, 1)).thenReturn(dummyItem1);
		CartItem returnedItem = cartService.addItemToCart(1, 1, 2);
		assertEquals(returnedItem, dummyItem1);
	}
	
	@Test
	public void TestRemoveItem()
	{
		when(mockCartRepo.deleteProductFromCart(1, 1)).thenReturn(true);
		when(mockCartRepo.findByUserIdAndProductId(1, 1)).thenReturn(null);
		CartItem returnedItem = cartService.removeItem(1, 1);
		assertNull(returnedItem);
	}
	
	@Test
	public void TestUpdateQunatity()
	{
		when(mockCartRepo.updateQuantityInCart(2, 1, 1)).thenReturn(true);
		when(mockCartRepo.findByUserIdAndProductId(1, 1)).thenReturn(dummyItem1);
		CartItem returnedItem = cartService.updateItemQuantity(2, 1, 1);
		assertEquals(returnedItem, dummyItem1);
	}
	
	
	@Test
	public void TestClearCart()
	{
		List<CartItem> emptyList = new ArrayList<CartItem>();
		when(mockCartRepo.clearCartForUser(1)).thenReturn(true);
		when(mockCartRepo.findListByUserId(1)).thenReturn(emptyList);
		List<CartItem> returnedList = cartService.clearCart(1);
		assertEquals(returnedList, emptyList);
	}
	
	@Test
	public void TestCreateCart()
	{
		when(mockUserRepo.findById(1)).thenReturn(dummyUser);
		CartItem ci = new CartItem();
		ci.setUser(dummyUser);
		when(mockCartRepo.save(ci)).thenReturn(dummyItem1);
		CartItem returnedItem = cartService.createCart(1);
		assertEquals(returnedItem, dummyItem1);
	}
	
}
