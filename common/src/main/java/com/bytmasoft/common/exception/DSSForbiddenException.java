package com.bytmasoft.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class DSSForbiddenException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6073399726949565761L;


	public DSSForbiddenException() {
		super();
	}
	
	public DSSForbiddenException(String message) {
		super(message);
	}
	
	public DSSForbiddenException(Exception ex, String message) {
		super(message, ex);
	}
	
	public DSSForbiddenException(Exception ex) {
		super(ex);
	}
}
