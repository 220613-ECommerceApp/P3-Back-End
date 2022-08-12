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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.OrderInfo;
import com.revature.dtos.ProductInfo;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase
class OrderHistoryControllerTest {
      @Autowired
      private MockMvc mockMvc;

      @Autowired
      private ObjectMapper mapper;

      private String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiZW1haWwiOiJ0ZXN0dXNlckBnbWFpbC5jb20iLCJ1c2VybmFtZSI6InRlc3QifQ.6l6ywOw-LNeYfJ0cDyK2lN1YulkFLbTmlOazm2sjAQQ";
      private String token2 = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiZW1haWwiOiJ0ZXN0dXNlcjJAZ21haWwuY29tIiwidXNlcm5hbWUiOiJ0ZXN0MiJ9.yvdHDy-33PEU9zF5HOTJxGNTBGDq_MH5S1YYBMBEPJ8";

      private String orderHistoryUrl = "/api/orderHistory";

      @Test
      void testGetOrderHistory() throws Exception{
        MvcResult result = mockMvc.perform(get(orderHistoryUrl)
                        .header("Authorization", token))
                        .andExpect(status().isOk())
                        .andReturn();

        String json = result.getResponse().getContentAsString();
        List<List<OrderInfo>> orderInfos = mapper.readValue(json, new TypeReference<List<List<OrderInfo>>>() {
        });

        List<OrderInfo> orderInfo = orderInfos.get(0);

        assertEquals(1, orderInfos.size());
        assertEquals(5, orderInfo.size());
      }

      @Test
      void testAddOrderToOrderHistory() throws Exception{
        List<ProductInfo> productInfos = new LinkedList<>();
        productInfos.add(new ProductInfo(2, 1));
        productInfos.add(new ProductInfo(5, 3));


        String productInfosJSON = mapper.writeValueAsString(productInfos);
    
        mockMvc.perform(post(orderHistoryUrl)
            .header("Authorization", token2)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(productInfosJSON))
            .andExpect(status().isOk());

        mockMvc.perform(post(orderHistoryUrl)
            .header("Authorization", token2)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(productInfosJSON))
            .andExpect(status().isOk());

        MvcResult result = mockMvc.perform(get(orderHistoryUrl)
        .header("Authorization", token2))
        .andExpect(status().isOk())
        .andReturn();

        String json = result.getResponse().getContentAsString();
        List<List<OrderInfo>> orderInfos = mapper.readValue(json, new TypeReference<List<List<OrderInfo>>>() {
        });

        List<OrderInfo> orderInfo = orderInfos.get(0);

        assertEquals(2, orderInfos.size());
        assertEquals(2, orderInfo.size());
      }

}