package com.example.binding;



import jakarta.persistence.Column;
import lombok.Data;

@Data
public class SsnRequest {

	@Column(name="FullName")
	private String fullName;
	
	@Column(name="SSN")
	private Long ssn;
	
	@Column(name="StateName")
	private String stateName;
}

