package com.bytmasoft.dss.token.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bytmasoft.domain.models.BaseUser;
import com.bytmasoft.domain.models.Role;
import com.bytmasoft.domain.models.Teacher;
import com.bytmasoft.dss.token.helper.models.UserPrincipal;

class UserPrincipalUnitTest {

	private static BaseUser teacher;
	private static String salt = "Aba14mah";
	private static UserPrincipal userPrincipal;
	private static String username = "Abakar";
	private static String password = "Aba14";
	private static String email;
	private static boolean status = true;
	private static Role role1, role2;

	@BeforeAll
	private static void setup() {
		teacher = new Teacher();
		teacher.setUsername(username);
		teacher.setPassword(password);
		email = new String("abakar61@web.de");
		teacher.setActive(status);
		teacher.setEmail(email);
		teacher.setSalt(salt);
		role1 = new Role();
		role1.setId(1L);
		role1.setName("Manager");
		role1.setActive(status);
		role1.getTeachers().add((Teacher) teacher);

		role2 = new Role();
		role2.setId(2L);
		role2.setName("Teacher");
		role2.setActive(status);
		role2.getTeachers().add((Teacher) teacher);

		List<Role> roles = new ArrayList<Role>();

		roles.add(role1);
		roles.add(role2);

		((Teacher) teacher).getRoles().addAll(roles);

		userPrincipal = new UserPrincipal(teacher);
	}

	@Test
	void testGetAuthorities() {
		assertNotNull(userPrincipal.getAuthorities());
		assertFalse(userPrincipal.getAuthorities().isEmpty());

	}

	@Test
	void testGetPassword() {
		assertEquals(password, userPrincipal.getPassword());
	}

	@Test
	void testGetUsername() {
		assertEquals(username, userPrincipal.getUsername());
	}

	@Test
	void testIsAccountNonExpired() {
		assertThat(userPrincipal.isAccountNonExpired()).isTrue();
	}

	@Test
	void testIsAccountNonLocked() {
		assertTrue(userPrincipal.isAccountNonLocked());
	}

	@Test
	void testIsCredentialsNonExpired() {
		assertTrue(userPrincipal.isCredentialsNonExpired());
	}

	@Test
	void testIsEnabled() {
		assertTrue(userPrincipal.isEnabled());
	}

	@Test
	void testGetEmail() {
		assertEquals(email.toString(), userPrincipal.getEmail());
	}

	@Test
	void testGetSalz() {
		assertEquals(salt, userPrincipal.getSalz());
	}

}
