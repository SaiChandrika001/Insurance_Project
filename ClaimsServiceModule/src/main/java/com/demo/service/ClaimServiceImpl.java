package com.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.binding.ClaimRequest;
import com.demo.binding.ClaimResponse;
import com.demo.entity.Claim;
import com.demo.exception.ResourceNotFoundException;
import com.demo.repository.ClaimRepository;

@Service
@Transactional
public class ClaimServiceImpl implements ClaimService {

	private final ClaimRepository claimRepository;

	public ClaimServiceImpl(ClaimRepository claimRepository) {
		this.claimRepository = claimRepository;
	}

	@Override
	public ClaimResponse createClaim(ClaimRequest request) {
		Claim claim = new Claim();
		claim.setPolicyNumber(request.getPolicyNumber());
		claim.setClaimantName(request.getClaimantName());
		claim.setDescription(request.getDescription());
		claim.setStatus("OPEN");

		Claim savedClaim = claimRepository.save(claim);
		return convertToResponse(savedClaim);
	}

	@Override
	public ClaimResponse getClaimById(Integer id) {
		Claim claim = claimRepository.findById(id).orElse(null);
		if (claim == null) {
			throw new ResourceNotFoundException("Claim not found with ID: " + id);
		}
		return convertToResponse(claim);
	}

	@Override
	public List<ClaimResponse> getAllClaims() {
		List<Claim> claimList = claimRepository.findAll();
		List<ClaimResponse> responseList = new ArrayList<ClaimResponse>();

		Iterator<Claim> iterator = claimList.iterator();
		while (iterator.hasNext()) {
			Claim claim = iterator.next();
			responseList.add(convertToResponse(claim));
		}
		return responseList;
	}

	@Override
	public List<ClaimResponse> getClaimsByPolicy(String policyNumber) {
		List<Claim> claimList = claimRepository.findByPolicyNumber(policyNumber);
		List<ClaimResponse> responseList = new ArrayList<ClaimResponse>();

		for (int i = 0; i < claimList.size(); i++) {
			Claim claim = claimList.get(i);
			responseList.add(convertToResponse(claim));
		}
		return responseList;
	}

	@Override
	public ClaimResponse updateStatus(Integer id, String status) {
		Claim claim = claimRepository.findById(id).orElse(null);
		if (claim == null) {
			throw new ResourceNotFoundException("Claim not found with ID: " + id);
		}
		claim.setStatus(status);
		Claim updated = claimRepository.save(claim);
		return convertToResponse(updated);
	}

	@Override
	public boolean deleteClaim(Integer id) {
		Claim claim = claimRepository.findById(id).orElse(null);
		if (claim == null) {
			throw new ResourceNotFoundException("Claim not found with ID: " + id);
		}
		claimRepository.delete(claim);
		return true;
	}

	private ClaimResponse convertToResponse(Claim claim) {
		ClaimResponse response = new ClaimResponse();
		response.setId(claim.getId());
		response.setPolicyNumber(claim.getPolicyNumber());
		response.setClaimantName(claim.getClaimantName());
		response.setDescription(claim.getDescription());
		response.setStatus(claim.getStatus());
		response.setCreatedAt(claim.getCreatedAt());
		response.setUpdatedAt(claim.getUpdatedAt());
		return response;
	}
}
