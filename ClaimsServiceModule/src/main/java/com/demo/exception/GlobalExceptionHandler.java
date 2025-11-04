package com.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<APIError> handleNotFound(ResourceNotFoundException e) {
		APIError api = new APIError();
		api.setStatus(HttpStatus.NOT_FOUND.value());
		api.setError("EXP125");
		api.setMessage(e.getMessage());
		return new ResponseEntity<APIError>(api,HttpStatus.NOT_FOUND);
	}

}
