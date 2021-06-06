/**
 * 
 */
package com.bytmasoft.domain.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Mahamat Abakar
 * @version 1.0 Friday 04.06.2021 22:34:23
 * 
 */

class StudentTest {

	Student s1;
	private String email = "abakar61@web.de";
	private Role role;
	private Long id = 1L;
	private Student s2;

	@BeforeEach
	private void init() {

		s1 = new Student();
		s1.setId(id);
		s1.setEmail(email);
		s1.setFirstName("Mahamat");
		s1.setLastName("Abakar");

		s2 = new Student();

		role = new Role();
		role.setId(id);
		role.setName("Student_Role");
	}

	/**
	 * Test method for {@link com.bytmasoft.domain.models.Student#hashCode()}.
	 */
	@Test
	void testHashCode() {
		assertThat(s1.hashCode()).isNotNull();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.domain.models.Student#equals(java.lang.Object)}.
	 */
	@Test
	void testEqualsObject() {
		assertEquals(s1, s1);
	}

	/**
	 * Test method for Example Firstname = Mahamat, Lastname = Abakar, Day ot the
	 * Month 4 then username = STABAKAM04
	 * {@link com.bytmasoft.domain.models.Student#generateUsername()}.
	 */
	@Test
	void testGenerateUsername() {
		s1.generateUsername();
		assertEquals(s1.getUsername(), "STABAKAM05");

		s2.generateUsername();
		assertEquals(s2.getUsername(), "STABAKAM05");

	}

	@Test
	void testSetUsername() {

		s1.setUsername("STABAKAM05");
		assertEquals(s1.getUsername(), "STABAKAM05");
	}

	/**
	 * Test method for {@link com.bytmasoft.domain.models.Student#toString()}.
	 */
	@Test
	void testToString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.domain.models.Student#addFile(com.bytmasoft.domain.models.FileDB)}.
	 */
	@Test
	void testAddFile() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.domain.models.Student#addRole(com.bytmasoft.domain.models.Role)}.
	 */
	@Test
	void testAddRole() {

		s1.addRole(role);
		assertTrue(s1.getRoles().contains(role));

	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.domain.models.Student#romveRole(com.bytmasoft.domain.models.Role)}.
	 */
	@Test
	void testRomveRole() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.domain.models.Student#addAddress(com.bytmasoft.domain.models.Address)}.
	 */
	@Test
	void testAddAddress() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.domain.models.Student#removeAddress(com.bytmasoft.domain.models.Address)}.
	 */
	@Test
	void testRemoveAddress() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.domain.models.Student#addCourse(com.bytmasoft.domain.models.Course)}.
	 */
	@Test
	void testAddCourse() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.domain.models.Student#romveCourse(com.bytmasoft.domain.models.Course)}.
	 */
	@Test
	void testRomveCourse() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.domain.models.Student#addContactPerson(com.bytmasoft.domain.models.ContactPerson)}.
	 */
	@Test
	void testAddContactPerson() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.domain.models.Student#romveContactPerson(com.bytmasoft.domain.models.ContactPerson)}.
	 */
	@Test
	void testRomveContactPerson() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.bytmasoft.domain.models.Student#getRoles()}.
	 */
	@Test
	void testGetRoles() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.bytmasoft.domain.models.Student#getAddresses()}.
	 */
	@Test
	void testGetAddresses() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.bytmasoft.domain.models.Student#getCourses()}.
	 */
	@Test
	void testGetCourses() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.domain.models.Student#getContactPersons()}.
	 */
	@Test
	void testGetContactPersons() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.bytmasoft.domain.models.Student#getFiles()}.
	 */
	@Test
	void testGetFiles() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.bytmasoft.domain.models.Student#setRoles(Set)}.
	 */
	@Test
	void testSetRoles() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.domain.models.Student#setAddresses(Set)}.
	 */
	@Test
	void testSetAddresses() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.bytmasoft.domain.models.Student#setCourses(Set)}.
	 */
	@Test
	void testSetCourses() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.domain.models.Student#setContactPersons(Set)}.
	 */
	@Test
	void testSetContactPersons() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.bytmasoft.domain.models.Student#setFiles(Set)}.
	 */
	@Test
	void testSetFiles() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.bytmasoft.domain.models.Student#Student()}.
	 */
	@Test
	void testStudent() {
		assertNotNull(s1);
	}

}
