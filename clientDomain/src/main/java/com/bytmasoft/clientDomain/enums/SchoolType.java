/**
 * @author Mahamat
 * Date 09.04.2020 : 14:17:42
 */
package com.bytmasoft.clientDomain.enums;

/**
 * @author Mahamat Date 09.04.2020 : 14:17:42
 */
public enum SchoolType {

	PRIMARY("Primary School"), HIGHERSECONDARY("Heiger Secondary School"), UNIVERSITY("University School");

	private String description;

	/**
	 * 
	 */
	private SchoolType(String description) {
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

}
