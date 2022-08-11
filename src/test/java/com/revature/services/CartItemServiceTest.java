package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
