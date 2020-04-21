package com.bytmasoft.common.exception;

public class EntityNotFoundException extends MyEntityNotFoundException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8974361441228428469L;
	


	public EntityNotFoundException() {
		super();
	}
	
	public EntityNotFoundException(String message) {
		super(message);
	}
	
	public EntityNotFoundException(Exception ex, String message) {
		super(message, ex);
	}
	
	public EntityNotFoundException(Exception ex) {
		super(ex);
	}
	
	
}
