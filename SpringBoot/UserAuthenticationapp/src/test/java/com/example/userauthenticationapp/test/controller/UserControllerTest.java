package com.example.userauthenticationapp.test.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.userauthenticationapp.controller.UserController;
import com.example.userauthenticationapp.model.UserProfile;
import com.example.userauthenticationapp.service.UserprofileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	 @Autowired
	    private MockMvc mockMvc;
	 
	 
	  @Mock
	    UserprofileService userprofileService;
	    
	    private UserProfile usernew;

		@InjectMocks
		   UserController usercontroller;

	    @BeforeEach
	    public void setUp() {

	        
	        mockMvc = MockMvcBuilders.standaloneSetup(usercontroller).build();

	        usernew = new UserProfile();
	        usernew.setUsername("ajay1");
	        usernew.setPassword("ajaypass");
	    }
	        
	    @Test
	    public void adduserandpassword() throws Exception{
	    
	    	Mockito.when(userprofileService.addUser(usernew)).thenReturn(true);
	    	 mockMvc.perform(MockMvcRequestBuilders.post("/auth/user/adduser").contentType(MediaType.APPLICATION_JSON).content(jsonToString(usernew)))
	    	 .andExpect(MockMvcResultMatchers.status().isCreated()).andDo(MockMvcResultHandlers.print());
	    }
	    	   @Test
	    	    public void testLoginUser() throws Exception {


	    	        String username = "ajay1";
	    	        String password = "ajaypass";


	    	        Mockito.when(userprofileService.addUser(usernew)).thenReturn(true);
	    	        Mockito.when(userprofileService.findByUsernameAndPassword(username, password)).thenReturn(usernew);
	    	        mockMvc.perform(MockMvcRequestBuilders.post("/auth/user/login").contentType(MediaType.APPLICATION_JSON).content(jsonToString(usernew)))
	    	        .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
	    	    }
 
	    
   private static String jsonToString(final Object obj) throws JsonProcessingException {
	        String result;
	        try {
	            final ObjectMapper mapper = new ObjectMapper();
	            final String jsonContent = mapper.writeValueAsString(obj);
	            result = jsonContent;
	        } catch (JsonProcessingException e) {
	            result = "Json processing error";
	        }
	        return result;
	    }
	}
