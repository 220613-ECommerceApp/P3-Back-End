package com.revature.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Tag;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase
public class TagControllerTest {
   @Autowired
   private MockMvc mockMvc;

   @Autowired
   private ObjectMapper mapper;

   private String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiZW1haWwiOiJ0ZXN0dXNlckBnbWFpbC5jb20iLCJ1c2VybmFtZSI6InRlc3QifQ.6l6ywOw-LNeYfJ0cDyK2lN1YulkFLbTmlOazm2sjAQQ";

   @Test
   void shouldGetAlltags() throws Exception {
      MvcResult result = mockMvc.perform(get("/api/tag")
            .header("authorization", "Bearer " + token))
            .andExpect(status().isOk())
            .andReturn();

      String json = result.getResponse().getContentAsString();
      List<Tag> tags = mapper.readValue(json, new TypeReference<List<Tag>>() {
      });

      Tag tag = tags.stream().filter(item -> item.getName().equals("bryan")).findAny().orElse(null);
      assertNotNull(tag);
   }

}
