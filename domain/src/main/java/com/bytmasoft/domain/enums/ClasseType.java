/**
 * @author Mahamat
 * Date 09.04.2020 : 14:17:42
 */
package com.bytmasoft.domain.enums;

/**
 * @author Mahamat Date 09.04.2020 : 14:17:42
 */
public enum ClasseType {

	ADDVANCED("we need description "), FAIBLE("i need desc "), BEST("descriptin need it");

	private String description;

	/**
	 * 
	 */
	private ClasseType(String description) {
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

}
