package com.example;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.binding.CitizenRequest;
import com.example.binding.CitizenResponse;
import com.example.controller.CitizenController;
import com.example.service.CitizenService;
import com.fasterxml.jackson.databind.ObjectMapper;
@WebMvcTest(CitizenController.class)
public class CitizenControllerTest {

	 @Autowired
	    private MockMvc mockMvc;

	    @Autowired
	    private ObjectMapper objectMapper;

	    @MockBean
	    private CitizenService citizenService;

	    @Test
	    void testRegisterCitizen_Success() throws Exception {
	        // Arrange
	        CitizenRequest request = new CitizenRequest();
	        request.setSsn(9848270443L);
	        request.setFullName("John");
	        request.setEmail("john@example.com");
	        request.setPhno(9876543210L);
	        request.setGender("Male");
	        request.setDob(LocalDate.of(1990, 5, 15));
	        request.setStateName("Texas");

	        CitizenResponse response = new CitizenResponse();
	        response.setStatus("SUCCESS");
	        response.setMessage("Citizen registered successfully");

	        when(citizenService.registerCitizen(request)).thenReturn(response);

	        // Act & Assert
	        mockMvc.perform(post("/citizen/register")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(request)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.status", is("SUCCESS")))
	                .andExpect(jsonPath("$.message", is("Citizen registered successfully")));
	    }

	    @Test
	    void testRegisterCitizen_Fail() throws Exception {
	        // Arrange
	        CitizenRequest request = new CitizenRequest();
	        request.setSsn(9848270443L);

	        CitizenResponse response = new CitizenResponse();
	        response.setStatus("FAIL");
	        response.setMessage("Citizen already registered with this SSN");

	        when(citizenService.registerCitizen(request)).thenReturn(response);

	        // Act & Assert
	        mockMvc.perform(post("/citizen/register")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(request)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.status", is("FAIL")))
	                .andExpect(jsonPath("$.message", is("Citizen already registered with this SSN")));
	    }
	}

