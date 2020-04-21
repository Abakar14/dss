/**
 * 
 */
package com.bytmasoft.domain.enums;

/**
 * 
 * @author Mahamat Abakar Date 09.12.2019
 */

public enum RoleType {

	ADMIN("Administrator Role"), STUDENT("Student Role"), TEACHER("Teacher Role"), GUEST("Guest Role");

	private String description;

	/**
	 * 
	 */
	private RoleType(String description) {
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
}
