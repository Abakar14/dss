package com.bytmasoft.common.exception;

public class ForbiddenException extends MyEntityNotFoundException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6073399726949565761L;


	public ForbiddenException() {
		super();
	}
	
	public ForbiddenException(String message) {
		super(message);
	}
	
	public ForbiddenException(Exception ex, String message) {
		super(message, ex);
	}
	
	public ForbiddenException(Exception ex) {
		super(ex);
	}
}
