/**
 * 
 */
package com.bytmasoft.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author Mahamat Abakar Date 18.01.2020 
 */
@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class DSSUserExistException extends RuntimeException {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2663389361402250259L;

	public DSSUserExistException() {
		super();
	}
	
	public DSSUserExistException(String message) {
		super(message);
	}
	
	public DSSUserExistException(Exception ex, String message) {
		super(message, ex);
	}
	
	public DSSUserExistException(Exception ex) {
		super(ex);
	}
}
