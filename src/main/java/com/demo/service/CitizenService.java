package com.demo.service;



import java.util.List;

import com.demo.binding.CitizenRequest;
import com.demo.binding.CitizenResponse;


public interface CitizenService {
	public CitizenResponse addCitizen(CitizenRequest request);
	
	public List<CitizenResponse> updateCitizen(CitizenRequest request);
	
	public boolean deleteCitizen (Long ssn);
	
	public List<CitizenResponse> getAllCitizens();
	
	public    boolean deleteCitizenById(Integer id);

	public CitizenResponse getCitizenBySsn(Long ssn);
	
	public  CitizenResponse getCitizenById(Integer id);
}
