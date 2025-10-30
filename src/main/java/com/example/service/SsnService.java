package com.example.service;

import com.example.binding.SsnRequest;
import com.example.binding.SsnResponse;

public interface SsnService {

//	public SsnResponse AddSSn(SsnRequest request);
//	
//	public boolean validateSsn(Long ssn) ;
//	
	//boolean validateSsn(Long ssn, String fullname, String statename);
	 public SsnResponse getSsnDetails(Long ssn);
	 
	 public SsnResponse AddSSn(SsnRequest request);
	 
	 
}
