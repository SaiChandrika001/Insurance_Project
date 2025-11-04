package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.demo.binding.CitizenRequest;
import com.demo.binding.CitizenResponse;
import com.demo.service.CitizenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/citizens")
@Validated
public class CitizenController {

    @Autowired
    private CitizenService citizenService;

    @PostMapping("/add")
    public ResponseEntity<CitizenResponse> addCitizen(@Valid @RequestBody CitizenRequest request) {
        return ResponseEntity.ok(citizenService.addCitizen(request));
    }

    @PutMapping("/update-user")
    public ResponseEntity<List<CitizenResponse>> updateCitizen(@Valid @RequestBody CitizenRequest request) {
        return ResponseEntity.ok(citizenService.updateCitizen(request));
    }

    @DeleteMapping("/delete/{ssn}")
    public ResponseEntity<String> deleteCitizen(@PathVariable Long ssn) {
        citizenService.deleteCitizen(ssn);
        return ResponseEntity.ok("Citizen deleted successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<CitizenResponse>> getAllCitizens() {
        return ResponseEntity.ok(citizenService.getAllCitizens());
    }

    @GetMapping("/{ssn}")
    public ResponseEntity<CitizenResponse> getCitizenBySsn(@PathVariable Long ssn) {
        return ResponseEntity.ok(citizenService.getCitizenBySsn(ssn));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<CitizenResponse> getCitizenById(@PathVariable Integer id) {
        return ResponseEntity.ok(citizenService.getCitizenById(id));
    }
}
