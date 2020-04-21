/**
 * 
 */
package com.bytmasoft.common.exception;

/**
 * 
 * @author Mahamat Abakar Date 18.01.2020 
 */

public class UserAlreadyExistException extends MyEntityNotFoundException {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2663389361402250259L;

	public UserAlreadyExistException() {
		super();
	}
	
	public UserAlreadyExistException(String message) {
		super(message);
	}
	
	public UserAlreadyExistException(Exception ex, String message) {
		super(message, ex);
	}
	
	public UserAlreadyExistException(Exception ex) {
		super(ex);
	}
}
