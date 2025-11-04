package com.demo.exception;

import lombok.Data;

@Data
public class APIError {
	
	private int status;
	private String error;
	private String message;
}
