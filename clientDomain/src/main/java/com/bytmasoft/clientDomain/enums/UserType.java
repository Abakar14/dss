package com.bytmasoft.clientDomain.enums;

/**
 * 
 * @author Mahamat Abakar Date 08.12.2019
 */

public enum UserType {

	HUMAN("Some Person"),
	MACHINE("Some application");
	
	private String description;

	/**
	 * 
	 */
	private UserType(String description) {
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

}
