package com.bytmasoft.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MyValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3144449474096782230L;


	public MyValidationException() {
		super();
	}
	
	public MyValidationException(String message) {
		super(message);
	}
	
	public MyValidationException(Exception ex, String message) {
		super(message, ex);
	}
	
	public MyValidationException(Exception ex) {
		super(ex);
	}
}
