package com.bytmasoft.common.exception;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value =  HttpStatus.NOT_FOUND)
public class DSSEntityNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2893012585063649029L;

	public DSSEntityNotFoundException() {
		super();		
	}
	
	public DSSEntityNotFoundException(final String message, final Throwable e) {
		super(message, e);		
	}
	
	public DSSEntityNotFoundException(final String message) {
		super(message);		
	}
	
	public DSSEntityNotFoundException(final Throwable e) {
		super(e);		
	}
	
	
	
	
	
}
