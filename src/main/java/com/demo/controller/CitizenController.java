package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.binding.CitizenRequest;
import com.demo.binding.CitizenResponse;
import com.demo.service.CitizenService;

@RestController
@RequestMapping("/api/citizens")
public class CitizenController {

    @Autowired
    private CitizenService citizenService;

    // 🟢 Add new citizen
    @PostMapping("/add")
    public ResponseEntity<CitizenResponse> addCitizen(@RequestBody CitizenRequest request) {
        CitizenResponse response = citizenService.addCitizen(request);
        return ResponseEntity.ok(response);
    }

    // 🟡 Update citizen
    @PutMapping("/update-user")
    public ResponseEntity<List<CitizenResponse>> updateCitizen(@RequestBody CitizenRequest request) {
        List<CitizenResponse> response = citizenService.updateCitizen(request);
        return ResponseEntity.ok(response);
    }

    // 🔴 Delete citizen by SSN
    @DeleteMapping("/delete/{ssn}")
    public ResponseEntity<String> deleteCitizen(@PathVariable Long ssn) {
        boolean deleted = citizenService.deleteCitizen(ssn);
        return ResponseEntity.ok(deleted ? "Citizen deleted successfully" : "Citizen not found");
    }

    // 🟣 Get all citizens
    @GetMapping("/all")
    public ResponseEntity<List<CitizenResponse>> getAllCitizens() {
        List<CitizenResponse> response = citizenService.getAllCitizens();
        return ResponseEntity.ok(response);
    }

    // 🔵 Get citizen by SSN
    @GetMapping("/{ssn}")
    public ResponseEntity<CitizenResponse> getCitizenBySsn(@PathVariable Long ssn) {
        CitizenResponse response = citizenService.getCitizenBySsn(ssn);
        return ResponseEntity.ok(response);
    }

    // ⚫ Get citizen by ID
    @GetMapping("/id/{id}")
    public ResponseEntity<CitizenResponse> getCitizenById(@PathVariable Integer id) {
        CitizenResponse response = citizenService.getCitizenById(id);
        return ResponseEntity.ok(response);
    }
}
