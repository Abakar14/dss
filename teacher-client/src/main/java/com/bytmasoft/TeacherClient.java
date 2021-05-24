/**
 * 
 */
package com.bytmasoft;

import java.io.IOException;
import java.time.Duration;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;

import com.bytmasoft.clientDomain.models.Teacher;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Mahamat Abakar created on 17.11.2020 at 23:56:10
 */
@Log4j2
public class TeacherClient {

	private final WebClient webClient;

	/**
	 * 
	 */
	public TeacherClient(WebClient webClient) {
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
	public Flux<Teacher> getAllTeachers(String url) {

		return webClient.get().uri(url).header("Content-Type", "application/json").header("Accept", "application/json")
				.retrieve().bodyToFlux(Teacher.class);
	}

	/**
	 * @param l
	 * @return
	 */
	public Mono<Teacher> findTeacherById(String uri) {
		return webClient.get().uri(uri).retrieve()
				.onStatus(status -> HttpStatus.NOT_FOUND.equals(status), response -> Mono.empty())
				.bodyToMono(Teacher.class).retryBackoff(5, Duration.ofSeconds(1), Duration.ofSeconds(20))
				.doOnError(IOException.class, e -> log.error(e.getMessage()));

	}

	/************************************
	 * Post
	 ********************************************************/

	/**
	 * 
	 * @param uri
	 * @param teacher
	 * @return
	 */
	public Mono<Teacher> addNewStudent(String uri, Teacher teacher) {

		return webClient.post().uri(uri).body(Mono.just(teacher), Teacher.class).retrieve().bodyToMono(Teacher.class);
	}

	/************************************
	 * Put
	 ********************************************************/

	/**
	 * 
	 * @param url
	 * @param teacher
	 * @return
	 */
	public Mono<Teacher> update(String url, Teacher teacher) {
		return webClient.put().uri(url).body(Mono.just(teacher), Teacher.class).retrieve().bodyToMono(Teacher.class);

	}

	/************************************
	 * Delete
	 ********************************************************/

}
