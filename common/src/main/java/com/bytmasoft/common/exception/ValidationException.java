package com.bytmasoft.common.exception;

public class ValidationException extends MyEntityNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3144449474096782230L;


	public ValidationException() {
		super();
	}
	
	public ValidationException(String message) {
		super(message);
	}
	
	public ValidationException(Exception ex, String message) {
		super(message, ex);
	}
	
	public ValidationException(Exception ex) {
		super(ex);
	}
}
