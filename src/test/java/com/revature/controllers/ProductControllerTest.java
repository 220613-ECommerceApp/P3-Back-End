package com.revature.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
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
import com.revature.dtos.ProductInfo;
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
            MvcResult result = mockMvc.perform(get("/api/product/{id}", "9")
                        .header("authorization", "Bearer " + token))
                        .andExpect(status().isOk())
                        .andReturn();

            String json = result.getResponse().getContentAsString();
            Product product = mapper.readValue(json, Product.class);
            assertEquals("King of the Hill", product.getName());
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
            Product proPain = new Product(66, "Pro-Pain", 69.99, 3, "none",
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
            MvcResult result = mockMvc
                        .perform(get("/api/product/search/description/{query}", "fictionalized 80s Latin America")
                                    .header("authorization", "Bearer " + token))
                        .andExpect(status().isOk())
                        .andReturn();

            String json = result.getResponse().getContentAsString();
            List<Product> products = mapper.readValue(json, new TypeReference<List<Product>>() {
            });
            System.out.println(products.toString());
            assertEquals(1, products.size());
            assertEquals("Cartel Tycoon", products.get(0).getName());
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
                        .param("query", "LaWNmOWeR Mario moon"))
                        .andExpect(status().isOk())
                        .andReturn();

            String json = result.getResponse().getContentAsString();
            List<Product> products = mapper.readValue(json, new TypeReference<List<Product>>() {
            });

            Product prey = products.stream().filter(item -> item.getName().equals("Prey")).findAny()
                        .orElse(null);
            Product propane = products.stream().filter(item -> item.getName().equals("King of the Hill")).findAny()
                        .orElse(null);
            Product marioGolf = products.stream().filter(item -> item.getName().equals("Mario Golf: Toadstool Tour"))
                        .findAny().orElse(null);

            assertEquals(4, products.size());
            assertNotNull(prey);
            assertNotNull(propane);
            assertNotNull(marioGolf);
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

      @Test
      void shouldReturnBadRequestIfBuyingTooMuch() throws Exception {

            List<ProductInfo> testList = new ArrayList<>();
            ProductInfo productInfo = new ProductInfo(6, 100000);
            testList.add(productInfo);
            String purchaseJSON = mapper.writeValueAsString(testList);

            MvcResult result = mockMvc.perform(patch("/api/product")
                        .header("authorization", "Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(purchaseJSON))
                        .andExpect(status().isBadRequest())
                        .andReturn();

            int status = result.getResponse().getStatus();
            assertEquals(400, status);
      }

      @Test
      void shouldBeAbleToPurchaseAnItem() throws Exception {
            List<ProductInfo> testList = new ArrayList<>();
            ProductInfo productInfo = new ProductInfo(8, 2);
            testList.add(productInfo);
            String purchaseJSON = mapper.writeValueAsString(testList);

            MvcResult result = mockMvc.perform(patch("/api/product")
                        .header("authorization", "Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(purchaseJSON))
                        .andExpect(status().isOk())
                        .andReturn();

            String json = result.getResponse().getContentAsString();
            List<Product> products = mapper.readValue(json, new TypeReference<List<Product>>() {
            });

            assertEquals(1, products.size());
            assertEquals("Prey", products.get(0).getName());
            assertEquals(18, products.get(0).getQuantity());
      }

      @Test
      void shouldReturnNotFoundIfBuyingNonExistentProduct() throws Exception {
            List<ProductInfo> testList = new ArrayList<>();
            ProductInfo productInfo = new ProductInfo(94, 2);
            testList.add(productInfo);
            String purchaseJSON = mapper.writeValueAsString(testList);

            MvcResult result = mockMvc.perform(patch("/api/product")
                        .header("authorization", "Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(purchaseJSON))
                        .andExpect(status().isNotFound())
                        .andReturn();

            int status = result.getResponse().getStatus();
            assertEquals(404, status);
      }

      @Test
      void shouldReturnNotFoundIfDeletingNonExistentProduct() throws Exception {
            MvcResult result = mockMvc.perform(delete("/api/product/{id}", 400)
                        .header("authorization", "Bearer " + token))
                        .andExpect(status().isNotFound())
                        .andReturn();

            int status = result.getResponse().getStatus();
            assertEquals(404, status);
      }

      @Test
      void shouldDeleteAProduct() throws Exception {

            Product proPain = new Product(13, "Pro-Pain", 69.99, 3, "none",
                        "Well that figures, those two idiots spelled it wrong. -Hank Rutherford Hill, Assistant manager of Strickland Propane");
            String proPainJSON = mapper.writeValueAsString(proPain);

            mockMvc.perform(put("/api/product")
                        .header("authorization", "Bearer " + token)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(proPainJSON))
                        .andExpect(status().isOk())
                        .andReturn();

            MvcResult deleteionResult = mockMvc.perform(delete("/api/product/{id}", 10)
                        .header("authorization", "Bearer " + token))
                        .andExpect(status().isOk())
                        .andReturn();

            int status = deleteionResult.getResponse().getStatus();
            assertEquals(200, status);
      }

      @Test
      void searchByPriceRangeReturns204IfNoResults() throws Exception {
            MvcResult result = mockMvc.perform(get("/api/product/search/price")
                        .header("authorization", "Bearer " + token)
                        .param("startPrice", "50 000.00")
                        .param("endPrice", "100000"))
                        .andExpect(status().is(204))
                        .andReturn();

            int status = result.getResponse().getStatus();
            assertEquals(204, status);
      }

      @Test
      void searchByPriceRangeReturnsResult() throws Exception {
            MvcResult result = mockMvc.perform(get("/api/product/search/price")
                        .header("authorization", "Bearer " + token)
                        .param("startPrice", "9.99")
                        .param("endPrice", "9.99"))
                        .andExpect(status().isOk())
                        .andReturn();

            String json = result.getResponse().getContentAsString();
            List<Product> products = mapper.readValue(json, new TypeReference<List<Product>>() {
            });

            assertEquals(1, products.size());
            assertEquals("PUBG: Battlegrounds", products.get(0).getName());
      }

      @Test
      void searchByTagReturns204IfNoResults() throws Exception {
            MvcResult result = mockMvc.perform(get("/api/product/search/tag/{tagName}", "spider")
                        .header("authorization", "Bearer " + token))
                        .andExpect(status().is(204))
                        .andReturn();

            int status = result.getResponse().getStatus();
            assertEquals(204, status);
      }

      @Test
      void searchByTagReturnsResult() throws Exception {
            MvcResult result = mockMvc.perform(get("/api/product/search/tag/{tagName}", "Sports")
                        .header("authorization", "Bearer " + token))
                        .andExpect(status().isOk())
                        .andReturn();

            String json = result.getResponse().getContentAsString();
            List<Product> products = mapper.readValue(json, new TypeReference<List<Product>>() {
            });

            assertEquals(2, products.size());
      }

      @Test
      void shouldFindProductSuperSearch() throws Exception {
            MvcResult result = mockMvc.perform(get("/api/product/search/superSearch")
                        .header("authorization", "Bearer " + token)
                        .param("query", "LaWNmOWeR pubg moon"))
                        .andExpect(status().isOk())
                        .andReturn();

            String json = result.getResponse().getContentAsString();
            List<Product> products = mapper.readValue(json, new TypeReference<List<Product>>() {
            });

            Product prey = products.stream().filter(item -> item.getName().equals("Prey")).findAny()
                        .orElse(null);
            Product propane = products.stream().filter(item -> item.getName().equals("King of the Hill")).findAny()
                        .orElse(null);
            Product pubg = products.stream().filter(item -> item.getName().equals("PUBG: Battlegrounds"))
                        .findAny().orElse(null);

            assertEquals(3, products.size());
            assertNotNull(prey);
            assertNotNull(propane);
            assertNotNull(pubg);
      }

      @Test
      void shouldFindProductSuperSearchTag() throws Exception {
            MvcResult result = mockMvc.perform(get("/api/product/search/superSearch")
                        .header("authorization", "Bearer " + token)
                        .param("query", "Hank King of the")
                        .param("tagName", "Sports"))
                        .andExpect(status().isOk())
                        .andReturn();

            String json = result.getResponse().getContentAsString();
            List<Product> products = mapper.readValue(json, new TypeReference<List<Product>>() {
            });

            Product propane = products.stream().filter(item -> item.getName().equals("King of the Hill")).findAny()
                        .orElse(null);

            assertEquals(1, products.size());
            assertNotNull(propane);
      }

      @Test
      void shouldFindProductSuperSearchTagOnly() throws Exception {
            MvcResult result = mockMvc.perform(get("/api/product/search/superSearch")
                        .header("authorization", "Bearer " + token)
                        .param("query", " ")
                        .param("tagName", "Sports"))
                        .andExpect(status().isOk())
                        .andReturn();

            String json = result.getResponse().getContentAsString();
            List<Product> products = mapper.readValue(json, new TypeReference<List<Product>>() {
            });

            Product golf = products.stream().filter(item -> item.getName().equals("Mario Golf: Toadstool Tour"))
                        .findAny()
                        .orElse(null);

            Product propane = products.stream().filter(item -> item.getName().equals("King of the Hill")).findAny()
                        .orElse(null);

            assertEquals(2, products.size());
            assertNotNull(golf);
            assertNotNull(propane);
      }

      @Test
      void shouldReturnStatus204IfSSuperSearchFoundNothing() throws Exception {
            MvcResult result = mockMvc.perform(get("/api/product/search/superSearch")
                        .header("authorization", "Bearer " + token)
                        .param("query", "Bond. James bond."))
                        .andExpect(status().is(204))
                        .andReturn();

            int status = result.getResponse().getStatus();
            assertEquals(204, status);
      }
}