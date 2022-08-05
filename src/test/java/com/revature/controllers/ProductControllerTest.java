package com.revature.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.revature.models.Product;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase
class ProductControllerTest {
      @Autowired
      private MockMvc mockMvc;

      @Autowired
      private ObjectMapper mapper;

      private String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiZW1haWwiOiJ0ZXN0dXNlckBnbWFpbC5jb20iLCJ1c2VybmFtZSI6InRlc3QifQ.6l6ywOw-LNeYfJ0cDyK2lN1YulkFLbTmlOazm2sjAQQ";

      @Test
      void shouldGetAllProducts() throws Exception {
            MvcResult result = mockMvc.perform(get("/api/product")
                        .header("authorization", "Bearer " + token))
                        .andExpect(status().isOk())
                        .andReturn();

            String json = result.getResponse().getContentAsString();
            List<Product> products = mapper.readValue(json, new TypeReference<List<Product>>() {
            });

            Product product = products.stream().filter(item -> item.getId() == 3).findAny().orElse(null);
            assertNotNull(product);
      }

      @Test
      void shouldGetProductById() throws Exception {
            MvcResult result = mockMvc.perform(get("/api/product/{id}", "1")
                        .header("authorization", "Bearer " + token))
                        .andExpect(status().isOk())
                        .andReturn();

            String json = result.getResponse().getContentAsString();
            Product product = mapper.readValue(json, Product.class);
            assertEquals("Headphones", product.getName());
      }

      @Test
      void shouldReturn404CodeIfProductNotFoundByID() throws Exception {
            MvcResult result = mockMvc.perform(get("/api/product/{id}", "946756")
                        .header("authorization", "Bearer " + token))
                        .andExpect(status().isNotFound())
                        .andReturn();

            int status = result.getResponse().getStatus();
            assertEquals(404, status);
      }

      @Test
      void shouldUpsertProduct() throws Exception {
            Product proPain = new Product(13, "Pro-Pain", 69.99, 3, "none",
                        "Well that figures, those two idiots spelled it wrong. -Hank Rutherford Hill, Assistant manager of Strickland Propane");
            String proPainJSON = mapper.writeValueAsString(proPain);

            MvcResult result = mockMvc.perform(put("/api/product")
                        .header("authorization", "Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(proPainJSON))
                        .andExpect(status().isOk())
                        .andReturn();

            String json = result.getResponse().getContentAsString();
            Product product = mapper.readValue(json, Product.class);
            assertEquals("Pro-Pain", product.getName());
            assertEquals(3, product.getQuantity());
      }

      @Test
      void shouldFindProductWithDescription() throws Exception {
            MvcResult result = mockMvc.perform(get("/api/product/search/description/{query}", "fancy person")
                        .header("authorization", "Bearer " + token))
                        .andExpect(status().isOk())
                        .andReturn();

            String json = result.getResponse().getContentAsString();
            List<Product> products = mapper.readValue(json, new TypeReference<List<Product>>() {
            });
            assertEquals(1, products.size());
            assertEquals("Baseball Cap", products.get(0).getName());
      }

      @Test
      void shouldReturnStatus204IfSearchFoundNothing() throws Exception {
            MvcResult result = mockMvc.perform(get("/api/product/search/description/{query}", "PIKACHU!!!!")
                        .header("authorization", "Bearer " + token))
                        .andExpect(status().is(204))
                        .andReturn();

            int status = result.getResponse().getStatus();
            assertEquals(204, status);
      }

      @Test
      void shouldFindProductWithDescriptionAndName() throws Exception {
            MvcResult result = mockMvc.perform(get("/api/product/search/name_description")
                        .header("authorization", "Bearer " + token)
                        .param("query", "nice Shirt Headphones"))
                        .andExpect(status().isOk())
                        .andReturn();

            String json = result.getResponse().getContentAsString();
            List<Product> products = mapper.readValue(json, new TypeReference<List<Product>>() {
            });

            Product headphones = products.stream().filter(item -> item.getName().equals("Headphones")).findAny()
                        .orElse(null);
            Product teeShirt = products.stream().filter(item -> item.getName().equals("TeeShirt")).findAny()
                        .orElse(null);
            Product coat = products.stream().filter(item -> item.getName().equals("Coat")).findAny().orElse(null);

            assertEquals(3, products.size());
            assertNotNull(headphones);
            assertNotNull(teeShirt);
            assertNotNull(coat);
      }

      @Test
      void shouldReturnStatus204IfSearchWithDescriptionAndNameFoundNothing() throws Exception {
            MvcResult result = mockMvc.perform(get("/api/product/search/name_description")
                        .header("authorization", "Bearer " + token)
                        .param("query", "Bond. James bond."))
                        .andExpect(status().is(204))
                        .andReturn();

            int status = result.getResponse().getStatus();
            assertEquals(204, status);
      }
}