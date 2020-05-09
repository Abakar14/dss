package com.bytmasoft.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MyBadRequestExpception extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2186268435454707678L;


	public MyBadRequestExpception() {
		super();
	}
	
	public MyBadRequestExpception(String message) {
		super(message);
	}
	
	public MyBadRequestExpception(Exception ex, String message) {
		super(message, ex);
	}
	
	public MyBadRequestExpception(Exception ex) {
		super(ex);
	}
	
}
