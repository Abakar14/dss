/**
 * @author Mahamat
 * Date 21.03.2020 : 21:07:15
 */
package com.bytmasoft.um.controller.it;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bytmasoft.um.controller.LoginController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Mahamat Date 21.03.2020 : 21:07:15
 */
@SpringBootTest(classes = LoginController.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class LoginControllerIT {

	@Autowired
	private MockMvc mockMvc;

	@LocalServerPort
	private int port;

	HttpHeaders headers = new HttpHeaders();

	private String username = "tasnim@web.de";
	private String password = "Aba14mah?";

	String body = "{\"username\":\"" + username + "\",\n" + "\"password\":\"" + password + "\"}";

	RequestBuilder builder = formLogin().user(this.username).password(this.password);

	/**
	 * Test method for
	 * {@link com.bytmasoft.um.controller.LoginController#createAuthenticationToken(com.bytmasoft.domain.models.JwtRequest)}.
	 * 
	 * @throws Exception
	 */
	@Test
	void testCreateAuthenticationToken() throws Exception {

		System.out.println("Body : " + body);
		System.out.println("Url : " + createUrlWithPort("/login"));
//		MvcResult result = this.mockMvc.perform(post(createUrlWithPort("/login")).accept(MediaType.APPLICATION_JSON)
//				.param("username", this.username).param("password", this.password)).andReturn();

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(createUrlWithPort("/login")).content(body))
				.andReturn();

		System.out.println("result : " + result.getResponse().getStatus());
//		System.out.println("json : " + asJsonString(""));

	}

	private String asJsonString(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {

			throw new RuntimeException(e);
		}

	}

	private String createUrlWithPort(String uri) {
		return "http://localhost:" + port + "/um/api/v1" + uri;
	}

}
