package com.bytmasoft.domain.enums;

/**
 * 
 * @author Mahamat Abakar Date 08.12.2019
 */

public enum ContactPersonType {

	 
	
	PARENT("Mother or Father of the student"), FRIEND("Some Friend"), 
	WIFE("My wife"), HUSBAND("My husband"), BROTHER("My brother"),
	SISTER("My sister"),
	FAMILY_MEMBER("Relative person");	
	
	private String description;

	/**
	 * 
	 */
	private ContactPersonType(String description) {
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

}
