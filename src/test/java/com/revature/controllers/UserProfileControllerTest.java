package com.revature.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.revature.models.User;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase
public class UserProfileControllerTest {

	@Autowired
    private ObjectMapper mapper;
	
	@Autowired
    private MockMvc mockMvc;
	
	private String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiZW1haWwiOiJ0ZXN0dXNlckBnbWFpbC5jb20iLCJ1c2VybmFtZSI6InRlc3QifQ.6l6ywOw-LNeYfJ0cDyK2lN1YulkFLbTmlOazm2sjAQQ";
	
	 @Test
     void shouldGetUser() throws Exception {
		 
		 User user = new User(1, "nef", "bro", "test", "pass", "testuser@gmail.com" );
		 
		 MvcResult result = mockMvc.perform(get("/users")
				 .header("authorization", "Bearer " + token))
				 .andExpect(status().isOk())
				 .andReturn();
		 String json = result.getResponse().getContentAsString();
		 
		 // this converts the json object into a user class object
		 User jsonUser = mapper.readValue(json, User.class);
		 System.out.println(jsonUser.getId());
		 jsonUser.getUsername();
		 jsonUser.getEmail();
		 
		 assertEquals(user.getId(), jsonUser.getId());
		 assertEquals(user.getUsername(), jsonUser.getUsername());
		 assertEquals(user.getEmail(), jsonUser.getEmail());
		 
	 }
	 
	 @Test
     void shouldUpdateUser() throws Exception {
		 
		 User user = new User(1, "john", "doe", "test", "passupdated", "testuser@gmail.com" );
		 
		 String jsonUserContent = mapper.writeValueAsString(user);
		 
		 MvcResult result = mockMvc.perform(put("/users")
				 .header("authorization", "Bearer " + token)
				 .accept(MediaType.APPLICATION_JSON)
				 .contentType(MediaType.APPLICATION_JSON)
				 .content(jsonUserContent))
				 .andExpect(status().isOk())
				 .andReturn();
		 
		 String json = result.getResponse().getContentAsString();
		 
		 // this converts the json object into a user class object
		 User jsonUser = mapper.readValue(json, User.class);
		 		 
		 assertEquals(user.getId(), jsonUser.getId());
		 assertEquals(user.getUsername(), jsonUser.getUsername());
		 assertEquals(user.getEmail(), jsonUser.getEmail());
		 		 
	 }
	
}
