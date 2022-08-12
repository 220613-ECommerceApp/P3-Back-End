package com.revature.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.OrderInfo;
import com.revature.dtos.ProductId;
import com.revature.dtos.ProductInfo;
import com.revature.dtos.WishListId;
import com.revature.models.WishListItem;
import com.revature.services.WishListItemService;
import org.springframework.test.annotation.DirtiesContext.ClassMode;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class WishListItemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiZW1haWwiOiJ0ZXN0dXNlckBnbWFpbC5jb20iLCJ1c2VybmFtZSI6InRlc3QifQ.6l6ywOw-LNeYfJ0cDyK2lN1YulkFLbTmlOazm2sjAQQ";

    @Test
    void testGetWishList() throws Exception{
        MvcResult result = mockMvc.perform(get("/api/getWishList")
                        .header("Authorization", token))
                        .andExpect(status().isOk())
                        .andReturn();

        String json = result.getResponse().getContentAsString();
        List<WishListItem> wishListItems = mapper.readValue(json, new TypeReference<List<WishListItem>>() {
        });

        assertEquals(3, wishListItems.size());
    }

    @Test
    void testRemoveFromWishList() throws Exception{
        WishListId wishListId = new WishListId(1);
        String wishlistIdJSON = mapper.writeValueAsString(wishListId);

        mockMvc.perform(delete("/api/removeFromWishList")
        .header("Authorization", token)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(wishlistIdJSON))
        .andExpect(status().isOk());

        MvcResult result = mockMvc.perform(get("/api/getWishList")
                        .header("Authorization", token))
                        .andExpect(status().isOk())
                        .andReturn();

        String json = result.getResponse().getContentAsString();
        List<WishListItem> wishListItems = mapper.readValue(json, new TypeReference<List<WishListItem>>() {
        });

        assertEquals(2, wishListItems.size());
    }

    
    @Test
    void testAddToWishList() throws Exception{
        ProductId productId = new ProductId(3);
        String wishlistIdJSON = mapper.writeValueAsString(productId);

        mockMvc.perform(post("/api/addToWishList")
        .header("Authorization", token)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(wishlistIdJSON))
        .andExpect(status().isOk());

        MvcResult result = mockMvc.perform(get("/api/getWishList")
                        .header("Authorization", token))
                        .andExpect(status().isOk())
                        .andReturn();

        String json = result.getResponse().getContentAsString();
        List<WishListItem> wishListItems = mapper.readValue(json, new TypeReference<List<WishListItem>>() {
        });

        assertEquals(4, wishListItems.size());
    }


}
