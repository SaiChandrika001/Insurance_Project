package com.demo.binding;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SsnResponse {
		
	private Long ssn;
	private String fullName;
    private String stateName;
   
}
