package com.example.service;





import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.binding.SsnRequest;
import com.example.binding.SsnResponse;
import com.example.module.SSN;
import com.example.repo.SsnRepo;



@Service
public  class SsnServiceImp implements SsnService{

 

	@Autowired
	public SsnRepo ssnRepo;

 
	@Override
	public SsnResponse AddSSn(SsnRequest request) {
		SSN entity =new SSN();
		BeanUtils.copyProperties(request, entity);
		SSN save = ssnRepo.save(entity);
		if(save==null)
		{
		  System.out.println("Not added");
		}
		SsnResponse response =new SsnResponse();
		BeanUtils.copyProperties(save, response);
		return response;
	}

	@Override
	 public SsnResponse getSsnDetails(Long ssn) {
        SSN record = ssnRepo.findBySsn(ssn)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SSN not found"));

        SsnResponse response = new SsnResponse();
        response.setSsn(record.getSsn());
        response.setFullName(record.getFullName());
        response.setStateName(record.getStateName());

        return response;
    }
	





}
