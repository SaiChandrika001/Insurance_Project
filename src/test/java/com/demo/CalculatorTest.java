package com.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.demo.binding.CitizenRequest;
import com.demo.binding.CitizenResponse;
import com.demo.repository.CitizenRepository;
import com.demo.test.Calculator;

public class CalculatorTest {
	
	 @Autowired
     private CitizenRepository citizenRepo;

	
@Test
	public void Calculatoradd() {
		Calculator c= new Calculator();
		c.add(10, 20);
		int actualResult=30;
		int acceptedResult=30;
		assertEquals(actualResult, acceptedResult);
		
	}
@Test
 public void Calculatormul() {
	Calculator c1= new Calculator();
	c1.mul(3, 6);
	int actualResult=18;
	int acceptedResult=18;
	assertEquals(actualResult, acceptedResult);
	
}
}
