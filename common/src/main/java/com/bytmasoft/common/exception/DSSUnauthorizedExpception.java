package com.bytmasoft.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DSSUnauthorizedExpception extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2186268435454707678L;


	public DSSUnauthorizedExpception() {
		super();
	}
	
	public DSSUnauthorizedExpception(String message) {
		super(message);
	}
	
	public DSSUnauthorizedExpception(Exception ex, String message) {
		super(message, ex);
	}
	
	public DSSUnauthorizedExpception(Exception ex) {
		super(ex);
	}
	
}
