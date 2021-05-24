package com.bytmasoft.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DSSValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3144449474096782230L;


	public DSSValidationException() {
		super();
	}
	
	public DSSValidationException(String message) {
		super(message);
	}
	
	public DSSValidationException(Exception ex, String message) {
		super(message, ex);
	}
	
	public DSSValidationException(Exception ex) {
		super(ex);
	}
}
