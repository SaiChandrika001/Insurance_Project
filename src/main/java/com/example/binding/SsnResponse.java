package com.example.binding;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SsnResponse {

	

	private Integer id;

	@Column(name="FullName")
	private String fullName;
	
	@Column(name="SSN")
	private Long ssn;
	
	@Column(name="StateName")
	private String stateName;
	
	   public SsnResponse() {
	    }

	   
		
}
