package com.bytmasoft.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DSSConfilictExpception extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2186268435454707678L;


	public DSSConfilictExpception() {
		super();
	}
	
	public DSSConfilictExpception(String message) {
		super(message);
	}
	
	public DSSConfilictExpception(Exception ex, String message) {
		super(message, ex);
	}
	
	public DSSConfilictExpception(Exception ex) {
		super(ex);
	}
	
}
