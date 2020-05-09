package com.bytmasoft.domain.enums;

/**
 * 
 * @author Mahamat Abakar Date 08.12.2019
 */

public enum UserType {

	HUMAN("Some application"),
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
