package com.demo;

import com.demo.binding.CitizenRequest;
import com.demo.binding.CitizenResponse;
import com.demo.controller.CitizenController;
import com.demo.modal.Citizen;
import com.demo.service.CitizenService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CitizenController.class)
public class CitizenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CitizenService citizenService;

    // 🟢 Test: Add Citizen
    @Test
    void testAddCitizen() throws Exception {
        Citizen citizen = createSampleCitizen();
        CitizenResponse mockResponse = new CitizenResponse(citizen, "Citizen added successfully");

        Mockito.when(citizenService.addCitizen(any(CitizenRequest.class))).thenReturn(mockResponse);

        String requestJson = """
            {
              "fullName": "John Doe",
              "mobileNumber": 9876543210,
              "email": "john@example.com",
              "gender": "Male",
              "ssn": 123456789,
              "dateofBirth": "1990-01-01",
              "stateName": "California"
            }
        """;

        mockMvc.perform(post("/api/citizens/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.citizen.fullName").value("John Doe"))
                .andExpect(jsonPath("$.message").value("Citizen added successfully"));
    }

    // 🟡 Test: Get All Citizens
    @Test
    void testGetAllCitizens() throws Exception {
        Citizen citizen1 = createSampleCitizen();
        Citizen citizen2 = createSampleCitizen();
        citizen2.setFullName("Jane Smith");
        citizen2.setEmail("jane@example.com");

        List<CitizenResponse> mockList = Arrays.asList(
                new CitizenResponse(citizen1, "Found"),
                new CitizenResponse(citizen2, "Found")
        );

        Mockito.when(citizenService.getAllCitizens()).thenReturn(mockList);

        mockMvc.perform(get("/api/citizens/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].citizen.fullName").value("John Doe"))
                .andExpect(jsonPath("$[1].citizen.fullName").value("Jane Smith"));
    }

    // 🔵 Test: Get Citizen By SSN
    @Test
    void testGetCitizenBySsn() throws Exception {
        Citizen citizen = createSampleCitizen();
        CitizenResponse mockResponse = new CitizenResponse(citizen, "Citizen Found");

        Mockito.when(citizenService.getCitizenBySsn(123456789L)).thenReturn(mockResponse);

        mockMvc.perform(get("/api/citizens/123456789"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.citizen.fullName").value("John Doe"))
                .andExpect(jsonPath("$.citizen.email").value("john@example.com"))
                .andExpect(jsonPath("$.message").value("Citizen Found"));
    }

    // 🔴 Test: Delete Citizen
    @Test
    void testDeleteCitizen() throws Exception {
        Mockito.when(citizenService.deleteCitizen(123456789L)).thenReturn(true);

        mockMvc.perform(delete("/api/citizens/delete/123456789"))
                .andExpect(status().isOk())
                .andExpect(content().string("Citizen deleted successfully"));
    }

    // ⚫ Test: Update Citizen
    @Test
    void testUpdateCitizen() throws Exception {
        Citizen citizen = createSampleCitizen();
        List<CitizenResponse> mockList = List.of(new CitizenResponse(citizen, "Citizen updated"));

        Mockito.when(citizenService.updateCitizen(any(CitizenRequest.class))).thenReturn(mockList);

        String requestJson = """
            {
              "fullName": "John Doe",
              "mobileNumber": 9876543210,
              "email": "john@example.com",
              "gender": "Male",
              "ssn": 123456789,
              "dateofBirth": "1990-01-01",
              "stateName": "California"
            }
        """;

        mockMvc.perform(put("/api/citizens/update-user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].citizen.fullName").value("John Doe"))
                .andExpect(jsonPath("$[0].message").value("Citizen updated"));
    }

    // Helper method to create a Citizen
    private Citizen createSampleCitizen() {
        Citizen citizen = new Citizen();
        citizen.setId(1);
        citizen.setFullName("John Doe");
        citizen.setEmail("john@example.com");
        citizen.setGender("Male");
        citizen.setMobileNumber(9876543210L);
        citizen.setStateName("California");
        citizen.setDateofBirth(LocalDate.of(1990, 1, 1));
        citizen.setSsn(123456789L);
        citizen.setCreatedBy("System");
        citizen.setUpdatedBy("System");
        return citizen;
    }
}
