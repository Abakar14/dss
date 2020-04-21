/**
 * @author Mahamat
 * Date 28.03.2020 : 13:58:07
 */
package com.bytmasoft.um.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import com.bytmasoft.login.service.impl.LoginServiceImpl;

/**
 * @author Mahamat Date 28.03.2020 : 13:58:07
 */
@SpringBootTest
class LoginServiceTest {

	@Autowired
	LoginServiceImpl service;

	private String username = "ABAKAM27";

	private String email = "abakar321@web.de";

	/**
	 * Test method for
	 * {@link com.bytmasoft.login.service.impl.LoginServiceImpl#loadUserByUsername(java.lang.String)}.
	 */
	@Test
	void testLoadUserByUsername() {
		System.out.println("testLoadUserByUsername");
		UserDetails user = service.loadUserByUsername(username);
		assertThat(user).isNotNull();
		System.out.println("userdetails1 : username : " + user.getUsername() + "password : " + user.getPassword());

		user.getAuthorities().forEach(a -> {
			System.out.println("Roles : " + a.getAuthority());
		});
	}

}
