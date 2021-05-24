/**
 * 
 */
package com.bytmasoft;

import java.io.IOException;
import java.time.Duration;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;

import com.bytmasoft.clientDomain.models.Student;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Mahamat Abakar created on 17.11.2020 at 23:56:10
 */
@Log4j2
public class StudentClient {

	private final WebClient webClient;

	/**
	 * 
	 */
	public StudentClient(WebClient webClient) {
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
	public Flux<Student> getAllStudents(String url) {

		return webClient.get().uri(url).header("Content-Type", "application/json").header("Accept", "application/json")
				.retrieve().bodyToFlux(Student.class);
	}

	/**
	 * @param l
	 * @return
	 */
	public Mono<Student> findStudentWitdID(String uri) {
		return webClient.get().uri(uri).retrieve()
				.onStatus(status -> HttpStatus.NOT_FOUND.equals(status), response -> Mono.empty())
				.bodyToMono(Student.class).retryBackoff(5, Duration.ofSeconds(1), Duration.ofSeconds(20))
				.doOnError(IOException.class, e -> log.error(e.getMessage()));

	}

	public Flux<Student> findStudentByName(String uri) {

		return webClient.get().uri(uri).header("Content-Type", "application/json").header("Accept", "application/json")
				.retrieve().bodyToFlux(Student.class);

	}

	/************************************
	 * Post
	 ********************************************************/

	/**
	 * 
	 * @param uri
	 * @param student
	 * @return
	 */
	public Mono<Student> addNewStudent(String uri, Student student) {

		return webClient.post().uri(uri).body(Mono.just(student), Student.class).retrieve().bodyToMono(Student.class);
	}

	/************************************
	 * Put
	 ********************************************************/

	/**
	 * 
	 * @param url
	 * @param student
	 * @return
	 */
	public Mono<Student> update(String url, Student student) {
		return webClient.put().uri(url).body(Mono.just(student), Student.class).retrieve().bodyToMono(Student.class);

	}

	/************************************
	 * Delete
	 ********************************************************/

}
