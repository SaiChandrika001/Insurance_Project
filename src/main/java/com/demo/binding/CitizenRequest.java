package com.demo.binding;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitizenRequest {

	private String fullName;
	private Long mobileNumber;
	private String email;
	private String gender;
	private Long ssn;
	private LocalDate dateofBirth;
    private String stateName;
    
    private String createdBy;
    private String updatedBy;
    
   
    
}  
   

