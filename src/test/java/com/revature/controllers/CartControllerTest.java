package com.revature.controllers;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.CartItem;
import com.revature.models.Product;
import com.revature.utils.JWTUtil;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase
public class CartControllerTest
{

	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiZW1haWwiOiJ0ZXN0dXNlckBnbWFpbC5jb20iLCJ1c2VybmFtZSI6InRlc3QifQ.6l6ywOw-LNeYfJ0cDyK2lN1YulkFLbTmlOazm2sjAQQ";
    
    /*@Test
    void TestAddToCart() throws Exception
    {
    	//System.out.println("is this visible");
    	
    	 MvcResult result = mockMvc.perform(post("/api/cart/addtocart/{productid}", "1")
    			 .header("authorization", "Bearer " + token)
    			 .header("quantity", 2))
    			 .andExpect(status().isOk())
                 .andReturn();
    	 
    	 //String json = result.getResponse().getContentAsString();
    	 //System.out.println("////////////\nthe json output is: "+json);
    			 
    }/**/
    
    @Test
    void TestRemoveFromCart() throws Exception
    {
    	//System.out.println("is this visible");
    	
    	 MvcResult result = mockMvc.perform(delete("/api/cart/removefromcart/{productid}", "1")
    			 .header("authorization", "Bearer " + token))
    			 .andExpect(status().isOk())
                 .andReturn();
    	 
    	 //String json = result.getResponse().getContentAsString();
    	 //CartItem item = mapper.readValue(json, CartItem.class);
    	 //assertNull(item);
    			 
    }
    
   /*@Test
    void TestUpdateCart() throws Exception
    {
    	//System.out.println("is this visible");
	   //int userid = JWTUtil.verifyUserToken(" "+token);
	   //System.out.println("////////////////\nthe token refers to user: "+ userid);
    	 MvcResult result = mockMvc.perform(put("/api/cart/updatecart/{productid}}", "1")
    			 .header("authorization", "Bearer " + token)
    			 .header("quantity", 3))
    			 .andExpect(status().isOk())
                 .andReturn();
    	 
    	 //String json = result.getResponse().getContentAsString();
    	 //CartItem item = mapper.readValue(json, CartItem.class);
    	 //assertNull(item);
    			 
    }/**/
}
