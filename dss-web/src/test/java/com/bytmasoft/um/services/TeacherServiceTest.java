package com.bytmasoft.um.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bytmasoft.domain.enums.GenderType;
import com.bytmasoft.domain.enums.UserType;
import com.bytmasoft.domain.models.EmailAddress;
import com.bytmasoft.domain.models.Role;
import com.bytmasoft.domain.models.Teacher;
import com.bytmasoft.persistance.service.interfaces.RoleService;
import com.bytmasoft.persistance.service.interfaces.TeacherService;

@SpringBootTest
class TeacherServiceTest {

	@Autowired
	TeacherService service;
	
	@Autowired
	RoleService roleService;
	
	private String rolename = "Teacher";

	private Long user_id = 2L;

	@Test
	void testService() {
		assertThat(service).isNotNull();
	}
//	@Test
//	void testFindOne() {
//		fail("Not yet implemented");
//	}

//	@Test
//	void testFindAllResources() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindAllInActive() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindAllActive() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindAllPaginatedAndSorted() {
//		fail("Not yet implemented");
//	}

	@Test
	void testCreate() {
		this.getTeachers().forEach(t -> {
			assertThat(service.create(t)).isNotNull();
		});
	}

//	@Test
//	void testUpdate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDeleteById() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDeleteAll() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDeleteAllInActiveResources() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testCount() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testCountActiveResources() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testCountInActiveResources() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testActivateById() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDeactivateById() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testActivateAll() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDeactivateAll() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSetUpdateParams() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGenerateLoginname() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindByEmail() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindByLastName() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindByFirstName() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindByMiddelName() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindByFirstNameAndLastName() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindByBirthday() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindByStatus() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindByLastLogin() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindByUsername() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindByType() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindByFirstNameContainingIgnoreCase() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindByLastNameContainingIgnoreCase() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindAllSortedBy() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindByRequestParams() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindByFirstNameAndLastNameAndEmail() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindByFirstNameAndLastNameAndLoginname() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testCheckIfPasswordValid() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testRemerkByStatusForDelete() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindUserById() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindUsers() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testRemerkForDelete() {
//		fail("Not yet implemented");
//	}

	@Test
	void testAssignRoleToUser() {
		
		Role r = roleService.findByName(rolename); 
		
		assertThat(r).isNotNull();	
		
		service.assignRoleToUser(user_id, r.getId());
	}

//	@Test
//	void testAssignRoleToUsersString() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testAssignRoleToUsersLong() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindUsersByRoleName() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindUserByFirstnameSorted() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindUserByFirstnamePaged() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindUsersByAgeMoreThan() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindUsersByAgeLessThan() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testAssignAddressToUser() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindUsersByRoleId() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindUsersByPrivilegeId() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindUserByUserIdAndRoleId() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testUpdatePasswordByUserId() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testRemerkUsersWithSchoolIdForDelete() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSendEmailForChangingPassword() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testTeacherServiceImpl() {
//		fail("Not yet implemented");
//	}

	private List<Teacher> getTeachers() {

		List<Teacher> teachers = new ArrayList<>();

//		teachers.add(this.getTeacher("Mahamat", "Abakar", "abakar321@web.de", "Mahamoud", "Aba14mah?", UserType.HUMAN,
//				GenderType.Mr, "A"));

//		teachers.add(this.getTeacher("Tasnim", "Abakar", "tasnim@web.de", "Mahdi", "Aba14mah?", UserType.HUMAN,
//				GenderType.Mrs, "A"));

		teachers.add(
				this.getTeacher("Amal", "Frikh", "amal@web.de", null, "Aba14mah", UserType.HUMAN, GenderType.Mrs, "A"));

		teachers.add(this.getTeacher("Roushed", "Abakar", "roushed@web.de", null, "Aba14mah2", UserType.HUMAN,
				GenderType.Mr, "A"));

		teachers.add(
				this.getTeacher("Asha", "Abakar", "asha@web.de", null, "Aba14mah", UserType.HUMAN, GenderType.Mrs, "A"));

		teachers.add(
				this.getTeacher("Mohi", "Abakar", "mohi@web.de", null, "Aba14mah", UserType.HUMAN, GenderType.Mr, "A"));

		teachers.add(this.getTeacher("Mahamat", "Allawane", "allwane@web.de", "Ahmed", "Aba14mah", UserType.HUMAN,
				GenderType.Mr, "A"));

		return teachers;
	}

	private Teacher getTeacher(String firstname, String lastname, String email, String middelname, String password,
			UserType type, GenderType gender, String status) {

		Teacher t = new Teacher();
		t.setFirstName(firstname);
		t.setLastName(lastname);
		t.setEmailAddress(new EmailAddress(email));
		t.setMiddelName(middelname);
		t.setPassword(password);
		t.setStatus(status);
		t.setType(type);
		t.setGender(gender);

		return t;
	}

}
