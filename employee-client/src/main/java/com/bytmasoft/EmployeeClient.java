/**
 * 
 */
package com.bytmasoft;

import java.io.IOException;
import java.time.Duration;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;

import com.bytmasoft.clientDomain.models.Employee;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Mahamat Abakar created on 17.11.2020 at 23:56:10
 */
@Log4j2
public class EmployeeClient {

	private final WebClient webClient;

	/**
	 * 
	 */
	public EmployeeClient(WebClient webClient) {
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
	public Flux<Employee> getAllEmployees(String url) {

		return webClient.get().uri(url).header("Content-Type", "application/json").header("Accept", "application/json")
				.retrieve().bodyToFlux(Employee.class);
	}

	/**
	 * @param l
	 * @return
	 */
	public Mono<Employee> findEmplyeeById(String uri) {
		return webClient.get().uri(uri).retrieve()
				.onStatus(status -> HttpStatus.NOT_FOUND.equals(status), response -> Mono.empty())
				.bodyToMono(Employee.class).retryBackoff(5, Duration.ofSeconds(1), Duration.ofSeconds(20))
				.doOnError(IOException.class, e -> log.error(e.getMessage()));

	}

	/************************************
	 * Post
	 ********************************************************/

	/**
	 * 
	 * @param uri
	 * @param employee
	 * @return
	 */
	public Mono<Employee> addNewEmplyee(String uri, Employee employee) {

		return webClient.post().uri(uri).body(Mono.just(employee), Employee.class).retrieve()
				.bodyToMono(Employee.class);
	}

	/************************************
	 * Put
	 ********************************************************/

	/**
	 * 
	 * @param url
	 * @param employee
	 * @return
	 */
	public Mono<Employee> update(String url, Employee employee) {
		return webClient.put().uri(url).body(Mono.just(employee), Employee.class).retrieve().bodyToMono(Employee.class);

	}

	/************************************
	 * Delete
	 ********************************************************/

}
