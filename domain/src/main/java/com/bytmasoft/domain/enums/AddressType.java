/**
 * 
 */
package com.bytmasoft.domain.enums;

/**
 * 
 * @author Mahamat Abakar Date 13.12.2019 
 */

public enum AddressType {
	
	BUSINESS("Business address"), CONTRACT("Contact address"), HOME("Home address");
	
	private String description;
	
	/**
	 * 
	 */
	private AddressType(String description) {
		this.description = description;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

}
