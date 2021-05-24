/**
 * 
 */
package com.bytmasoft;

import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.log4j.Log4j2;

/**
 * @author Mahamat Abakar created on 17.11.2020 at 23:56:10
 */
@Log4j2
public class AuthenticationClient {

	private final WebClient webClient;

	/**
	 * 
	 */
	public AuthenticationClient(WebClient webClient) {
		this.webClient = webClient;

	}

	/************************************
	 * Get
	 ********************************************************/

	public String login() {
		String token = "";

		return token;
	}

	public boolean logout(String token, String username) {

		boolean isLogout = false;

		return isLogout;
	}

	/************************************
	 * Post
	 ********************************************************/

	/************************************
	 * Put
	 ********************************************************/

	/************************************
	 * Delete
	 ********************************************************/

}
