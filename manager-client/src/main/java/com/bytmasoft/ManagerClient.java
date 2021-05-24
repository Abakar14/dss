/**
 * 
 */
package com.bytmasoft;

import java.io.IOException;
import java.time.Duration;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;

import com.bytmasoft.clientDomain.models.Manager;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Mahamat Abakar created on 17.11.2020 at 23:56:10
 */
@Log4j2
public class ManagerClient {

	private final WebClient webClient;

	/**
	 * 
	 */
	public ManagerClient(WebClient webClient) {
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
	public Flux<Manager> getAllManagers(String url) {

		return webClient.get().uri(url).header("Content-Type", "application/json").header("Accept", "application/json")
				.retrieve().bodyToFlux(Manager.class);
	}

	/**
	 * @param l
	 * @return
	 */
	public Mono<Manager> findManagerById(String uri) {
		return webClient.get().uri(uri).retrieve()
				.onStatus(status -> HttpStatus.NOT_FOUND.equals(status), response -> Mono.empty())
				.bodyToMono(Manager.class).retryBackoff(5, Duration.ofSeconds(1), Duration.ofSeconds(20))
				.doOnError(IOException.class, e -> log.error(e.getMessage()));

	}

	/************************************
	 * Post
	 ********************************************************/

	/**
	 * 
	 * @param uri
	 * @param manager
	 * @return
	 */
	public Mono<Manager> addNewStudent(String uri, Manager manager) {

		return webClient.post().uri(uri).body(Mono.just(manager), Manager.class).retrieve().bodyToMono(Manager.class);
	}

	/************************************
	 * Put
	 ********************************************************/

	/**
	 * 
	 * @param url
	 * @param manager
	 * @return
	 */
	public Mono<Manager> update(String url, Manager manager) {
		return webClient.put().uri(url).body(Mono.just(manager), Manager.class).retrieve().bodyToMono(Manager.class);

	}

	/************************************
	 * Delete
	 ********************************************************/

}
