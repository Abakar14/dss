/**
 * 
 */
package com.bytmasoft.domain.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Mahamat Abakar Date 19.12.2019
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JwtRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1809169900821608974L;

	private String username;
	private String password;
	private String confirmPassword;
	private String email;

}
