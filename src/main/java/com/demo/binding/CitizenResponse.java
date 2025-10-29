package com.demo.binding;



import java.time.LocalDate;

import com.demo.modal.Citizen;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitizenResponse {

	
//	private Integer id;
//	private String fullName;
//    private String stateName;
//	private String email;
//	private String gender;
	private Citizen citizen;
	private String Message;
	
	
  	
}
