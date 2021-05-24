/**
 * 
 */
package com.bytmasoft.domain.enums;

/**
 * @author Mahamat Abakar created on 29.12.2020 at 18:38:07
 */
public enum FileType {

	PROFILEIMAGE("Profile Image"), GEBURTSTAGCERTIFICAT("Geburtstag Certificate"), EXAM("Exam");

	private String description;

	/**
	 * @param string
	 */
	FileType(String description) {
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

}
