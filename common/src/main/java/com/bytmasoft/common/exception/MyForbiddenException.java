package com.bytmasoft.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class MyForbiddenException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6073399726949565761L;


	public MyForbiddenException() {
		super();
	}
	
	public MyForbiddenException(String message) {
		super(message);
	}
	
	public MyForbiddenException(Exception ex, String message) {
		super(message, ex);
	}
	
	public MyForbiddenException(Exception ex) {
		super(ex);
	}
}
