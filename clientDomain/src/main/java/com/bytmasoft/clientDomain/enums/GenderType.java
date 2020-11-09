/**
 * 
 */
package com.bytmasoft.clientDomain.enums;

/**
 * 
 * @author Mahamat Abakar Date 13.12.2019
 */

public enum GenderType {

	Mr("Male"), Mrs("Female"), OTHER("other");

	private String description;

	/**
	 * 
	 */
	private GenderType(String description) {
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

}
