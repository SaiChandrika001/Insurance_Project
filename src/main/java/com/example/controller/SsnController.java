package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.binding.SsnRequest;
import com.example.binding.SsnResponse;
import com.example.service.SsnService;
import com.example.service.SsnServiceImp;

@RestController
@RequestMapping("/ssn")
public class SsnController {


	 @Autowired
	    private SsnService ssnService;
	
	@PostMapping("/ssnu")
	public ResponseEntity<SsnResponse> saveSSn(@RequestBody SsnRequest request)
	{
		
		SsnResponse addSSn = ssnService.AddSSn(request);
		
		return new ResponseEntity<SsnResponse>(addSSn, HttpStatus.CREATED);
		
	}
//	
//	@GetMapping("/{ssn}")
//    public ResponseEntity<Boolean> validateSsn(@PathVariable Long ssn) {
//        boolean validateSsn = ssnServiceImp.validateSsn(ssn);
//        return ResponseEntity.ok(validateSsn);
//    }
	
	
	 @GetMapping("/{ssn}")
	 public ResponseEntity<SsnResponse> getSsn(@PathVariable Long ssn){
	     SsnResponse response = ssnService.getSsnDetails(ssn);
	     return ResponseEntity.ok(response);
	 }


}
