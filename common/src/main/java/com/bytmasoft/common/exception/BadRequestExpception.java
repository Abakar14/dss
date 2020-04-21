package com.bytmasoft.common.exception;

public class BadRequestExpception extends MyEntityNotFoundException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2186268435454707678L;


	public BadRequestExpception() {
		super();
	}
	
	public BadRequestExpception(String message) {
		super(message);
	}
	
	public BadRequestExpception(Exception ex, String message) {
		super(message, ex);
	}
	
	public BadRequestExpception(Exception ex) {
		super(ex);
	}
	
}
