/**
 * 
 */
package com.bytmasoft;

import java.io.IOException;
import java.time.Duration;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;

import com.bytmasoft.clientDomain.models.Role;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Mahamat Abakar created on 17.11.2020 at 23:56:10
 */
@Log4j2
public class PermissionClient {

	private final WebClient webClient;

	/**
	 * 
	 */
	public PermissionClient(WebClient webClient) {
		this.webClient = webClient;

	}

	/************************************
	 * Get
	 ********************************************************/
	/**
	 * 
	 * @param url
	 * @return
	 */
	public Flux<Role> getAllRoles(String url) {

		return webClient.get().uri(url).header("Content-Type", "application/json").header("Accept", "application/json")
				.retrieve().bodyToFlux(Role.class);
	}

	/**
	 * @param l
	 * @return
	 */
	public Mono<Role> findRoleById(String uri) {
		return webClient.get().uri(uri).retrieve()
				.onStatus(status -> HttpStatus.NOT_FOUND.equals(status), response -> Mono.empty())
				.bodyToMono(Role.class).retryBackoff(5, Duration.ofSeconds(1), Duration.ofSeconds(20))
				.doOnError(IOException.class, e -> log.error(e.getMessage()));

	}

	/************************************
	 * Post
	 ********************************************************/

	/**
	 * 
	 * @param uri
	 * @param role
	 * @return
	 */
	public Mono<Role> addNewRole(String uri, Role role) {

		return webClient.post().uri(uri).body(Mono.just(role), Role.class).retrieve().bodyToMono(Role.class);
	}

	/************************************
	 * Put
	 ********************************************************/

	/**
	 * 
	 * @param url
	 * @param role
	 * @return
	 */
	public Mono<Role> update(String url, Role role) {
		return webClient.put().uri(url).body(Mono.just(role), Role.class).retrieve().bodyToMono(Role.class);

	}

	/************************************
	 * Delete
	 ********************************************************/

}
