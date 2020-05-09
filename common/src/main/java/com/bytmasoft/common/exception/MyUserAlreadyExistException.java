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
public class MyUserAlreadyExistException extends RuntimeException {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2663389361402250259L;

	public MyUserAlreadyExistException() {
		super();
	}
	
	public MyUserAlreadyExistException(String message) {
		super(message);
	}
	
	public MyUserAlreadyExistException(Exception ex, String message) {
		super(message, ex);
	}
	
	public MyUserAlreadyExistException(Exception ex) {
		super(ex);
	}
}
