/**
 * 
 */
package com.bytmasoft.client;

import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Mahamat Abakar created on 15.11.2020 at 19:28:31
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
