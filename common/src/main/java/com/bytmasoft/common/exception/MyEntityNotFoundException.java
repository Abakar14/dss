package com.bytmasoft.common.exception;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value =  HttpStatus.NOT_FOUND)
public class MyEntityNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2893012585063649029L;

	public MyEntityNotFoundException() {
		super();		
	}
	
	public MyEntityNotFoundException(final String message, final Throwable e) {
		super(message, e);		
	}
	
	public MyEntityNotFoundException(final String message) {
		super(message);		
	}
	
	public MyEntityNotFoundException(final Throwable e) {
		super(e);		
	}
	
	
	
	
	
}
