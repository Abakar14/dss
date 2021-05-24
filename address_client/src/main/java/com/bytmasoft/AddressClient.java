/**
 * 
 */
package com.bytmasoft;

import java.io.IOException;
import java.time.Duration;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;

import com.bytmasoft.clientDomain.models.Address;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Mahamat Abakar created on 17.11.2020 at 23:56:10
 */
@Log4j2
public class AddressClient {

	private final WebClient webClient;

	/**
	 * 
	 */
	public AddressClient(WebClient webClient) {
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
	public Flux<Address> getAllAddresses(String url) {

		return webClient.get().uri(url).header("Content-Type", "application/json").header("Accept", "application/json")
				.retrieve().bodyToFlux(Address.class);
	}

	/**
	 * @param l
	 * @return
	 */
	public Mono<Address> findAddressByID(String uri) {
		return webClient.get().uri(uri).retrieve()
				.onStatus(status -> HttpStatus.NOT_FOUND.equals(status), response -> Mono.empty())
				.bodyToMono(Address.class).retryBackoff(5, Duration.ofSeconds(1), Duration.ofSeconds(20))
				.doOnError(IOException.class, e -> log.error(e.getMessage()));

	}

	/************************************
	 * Post
	 ********************************************************/

	/**
	 * 
	 * @param uri
	 * @param address
	 * @return
	 */
	public Mono<Address> addNewAddress(String uri, Address address) {

		return webClient.post().uri(uri).body(Mono.just(address), Address.class).retrieve().bodyToMono(Address.class);
	}

	/************************************
	 * Put
	 ********************************************************/

	/**
	 * 
	 * @param url
	 * @param address
	 * @return
	 */
	public Mono<Address> update(String url, Address address) {
		return webClient.put().uri(url).body(Mono.just(address), Address.class).retrieve().bodyToMono(Address.class);

	}

	/************************************
	 * Delete
	 ********************************************************/

}
