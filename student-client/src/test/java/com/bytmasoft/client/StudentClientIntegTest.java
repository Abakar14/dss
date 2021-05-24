/**
 * 
 */
package com.bytmasoft.client;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.checkerframework.checker.units.qual.s;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import com.bytmasoft.StudentClient;
import com.bytmasoft.clientDomain.models.Student;

import reactor.core.publisher.Flux;

/**
 * @author Mahamat Abakar
 * created on 15.11.2020 at 19:28:31
 */

public class StudentClientIntegTest {
	
	private WebClient webClient = WebClient.builder().build(); 
	
//	@Test
//	void shouldRetrieveStudentFromService() {
//		
//		StudentClient studentClient = new StudentClient(webClient);
//		
//		Flux<Student> student = studentClient.findStudentWitdID(1L);
//		
//		assertNotNull(student);
//		assertTrue(student.blockFirst().getFirstName().equals("Mahamat"));
//		
//		
//	}

}
