/**
 * 
 */
package com.bytmasoft.clientDomain.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Mahamat Abakar Date 19.12.2019
 */

public class JwtResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2891421367470105586L;

	private final String jwttoken;
	private List<String> roles;

	/**
	 * @param token
	 */
	public JwtResponse(String token) {
		this.jwttoken = token;
	}

	public String getToken() {
		return this.jwttoken;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	/**
	 * @return the roles
	 */
	public List<String> getRoles() {
		if (roles == null) {
			roles = new ArrayList<>();
		}
		return roles;
	}

}
