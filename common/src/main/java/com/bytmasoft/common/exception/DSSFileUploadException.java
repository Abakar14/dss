package com.bytmasoft.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DSSFileUploadException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	

	/**
	 * 
	 */
	public DSSFileUploadException(String message) {
		super(message);
	}

}
