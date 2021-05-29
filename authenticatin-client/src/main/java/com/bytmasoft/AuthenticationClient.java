/**
 * 
 */
package com.bytmasoft;

import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Mahamat Abakar created on 17.11.2020 at 23:56:10
 */
public class AuthenticationClient {

	private final WebClient webClient;

	/**
	 * 
	 */
	public AuthenticationClient(WebClient webClient) {
		this.webClient = webClient;

	}

	/**
	 * @return the webClient
	 */
	public WebClient getWebClient() {
		return webClient;
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
