package com.demo.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.binding.ClaimRequest;
import com.demo.binding.ClaimResponse;
import com.demo.binding.UpdateStatusRequest;
import com.demo.service.ClaimService;

import java.util.List;

@RestController
@RequestMapping("/claims")
public class ClaimController {

	private final ClaimService service;

	public ClaimController(ClaimService service) {
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<ClaimResponse> create(@Valid @RequestBody ClaimRequest req) {
		ClaimResponse response = service.createClaim(req);
		return new ResponseEntity<ClaimResponse>(response, HttpStatus.CREATED);
	}

	@GetMapping("/getClaim/{id}")
	public ResponseEntity<ClaimResponse> get(@PathVariable Integer id) {
		ClaimResponse claimById = service.getClaimById(id);
		return new ResponseEntity<ClaimResponse>(claimById, HttpStatus.OK);
	}

	@GetMapping("/claims")
	public ResponseEntity<List<ClaimResponse>> list(@RequestParam String policyNumber) {
		if (policyNumber != null)
			return ResponseEntity.ok(service.getClaimsByPolicy(policyNumber));
		return new ResponseEntity<List<ClaimResponse>>(service.getAllClaims(), HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<ClaimResponse> updateStatusClaim(@PathVariable Integer id,
			@Valid @RequestBody UpdateStatusRequest req) {
		ClaimResponse updateStatus = service.updateStatus(id, null);
		return new ResponseEntity<ClaimResponse>(updateStatus, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
		Boolean deleteClaim = service.deleteClaim(id);
		return new ResponseEntity<Boolean>(deleteClaim, HttpStatus.OK);
	}
}
