/**
 * @author Mahamat
 * Date 19.03.2020 : 19:29:38
 */
package com.bytmasoft.um.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bytmasoft.domain.enums.GenderType;
import com.bytmasoft.domain.enums.UserType;
import com.bytmasoft.domain.models.BaseUser;
import com.bytmasoft.domain.models.Role;
import com.bytmasoft.persistance.services.TeacherServiceImpl;

/**
 * @author Mahamat Date 19.03.2020 : 19:29:38
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserManagementServiceTest {

	@Autowired
	TeacherServiceImpl service;

	private Long user_id = 19L;
	private Long address_id = 4L;
	private String rolename = "User";
	private Long role_id = 14L;
	private String sortBy = "firstName";
	private String sortOrder = "desc";
	private String firstname = "Mahamat";
	private String lastname = "Abakar";
	private String email = "abakar31@web.de";
	private String middelname = "";
	private LocalDate birthday;
	private String status = "A";
	private LocalDateTime lastLogin;
	private String loginname = "ABAKAM25";
	private UserType type = UserType.HUMAN;
	private Long age = 16L;
	private String oldPassword = "";
	private String updatedBy = "Abakar";
	private String sort = "desc";

//	@Test
//	void userServiceTest() {
//		assertThat(service).isNotNull();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#activateAll()}.
//	 */
//	@Test
//	@Order(43)
//	void testActivateAll() {
//		service.activateAll();
//		service.findAllResources().forEach(u -> {
//			assertThat(u.getStatus()).isEqualTo("A");
//		});
//		;
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#activateById(long)}.
//	 */
//	@Test
//	void testActivateById() {
//		service.activateById(user_id);
//		assertThat(service.findOne(user_id).getStatus()).isEqualTo("A");
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#assignRoleToUsers(java.lang.Long)}.
//	 */
//	@Test
//	@Order(2)
//	void testAssignRoleToUsers() {
//		service.assignRoleToUsers("User");
//
//		assertThat(service.findUsersByRoleName("User")).isNotEmpty();
//		assertEquals(3, service.findUsersByRoleName("User").size());
//
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#assignRoleToUsers(java.lang.String)}.
//	 */
////	@Test
////	@Order(3)
////	void testAssignRoleToAllUsers() {
////		service.assignRoleToUsers(rolename);
////		service.findOne(user_id).getRoles().forEach(r -> {
////			if (r.getName().equals(rolename)) {
////				assertThat(r.getName()).isEqualTo(rolename);
////			}
////		});
////	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#assignRoleToUser(java.lang.Long, java.lang.Long)}.
//	 */
////	@Test
////	@Order(4)
////	void testAssignRoleToUser() {
////		service.assignRoleToUser(user_id, role_id);
////
////		Optional<Role> role = service.findOne(user_id).getRoles().stream().filter(r -> role_id.equals(r.getId()))
////				.findFirst();
////
////		assertThat(role.get().getId()).isEqualTo(role_id);
////	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#assignAddressToUser(java.lang.Long, java.lang.Long)}.
//	 */
////	@Test
////	@Order(5)
////	void testAssignAddressToUser() {
////		service.assignAddressToUser(user_id, address_id);
////		User u = service.findOne(user_id);
////		assertThat(u.getAddresses()).isNotNull();
////
////	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#count()}.
//	 */
//	@Test
//	@Order(6)
//	void testCount() {
//		assertThat(service.count()).isGreaterThan(2);
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#countActiveResources()}.
//	 */
//	@Test
//	@Order(7)
//	void testCountActiveResources() {
//		assertThat(service.countActiveResources()).isGreaterThan(2);
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#countInActiveResources()}.
//	 */
//	@Test
//	void testCountInActiveResources() {
//		assertThat(service.countInActiveResources()).isLessThan(1);
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#create(com.bytmasoft.api.models.User)}.
//	 */
//	@Test
//	@Order(1)
//	void testCreate() {
//
//		this.getUsers().forEach(u -> {
//
//			assertThat(service.create(u)).isNotNull();
//		});
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#deactivateAll()}.
//	 */
//	@Test
//	@Order(8)
//	void testDeactivateAll() {
//		service.deactivateAll();
//		assertThat(service.findAllActive()).isEmpty();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#deactivateById(long)}.
//	 */
////	@Test
////	@Order(9)
//	void testDeactivateById() {
//		service.deactivateById(user_id);
//		assertThat(service.findOne(user_id).getStatus()).isEqualTo("I");
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#deleteAll()}.
//	 */
////	@Test
//	void testDeleteAll() {
//		service.deleteAll();
//		assertThat(service.findAllResources()).isEmpty();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#deleteAllInActiveResources()}.
//	 */
////	@Test
//	void testDeleteAllInActiveResources() {
//		service.deleteAllInActiveResources();
//
//		assertThat(service.findAllInActive()).isEmpty();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#deleteById(long)}.
//	 */
////	@Test
//	void testDeleteById() {
//
//		service.deleteById(user_id);
//		assertThat(service.findOne(user_id)).isNull();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#findAllActive()}.
//	 */
//	@Test
//	@Order(44)
//	void testFindAllActive() {
//		assertThat(service.findAllActive()).isNotEmpty();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#findAllInActive()}.
//	 */
//	@Test
//	@Order(10)
//	void testFindAllInActive() {
//		assertThat(service.findAllInActive()).isNotNull();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#findAllPaginatedAnSorted(int, int, java.lang.String, java.lang.String)}.
//	 */
//	@Test
//	@Order(12)
//	void testFindAllPaginatedAndSorted() {
//		assertThat(service.findAllPaginatedAndSorted(0, 2, sortBy, sortOrder)).isNotEmpty();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#findAllResources()}.
//	 */
//	@Test
//	@Order(13)
//	void testFindAllResources() {
//		assertThat(service.findAllResources()).isNotEmpty();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#findAllSortedBy(org.springframework.data.domain.Sort.Direction, java.lang.String)}.
//	 */
//	@Test
//	@Order(14)
//	void testFindAllSortedBy() {
//
//		assertThat(service.findAllSortedBy("desc", "firstName", "lastName")).isNotNull();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#findUsersByRoleName(java.lang.String)}.
//	 */
//	@Test
//	@Order(16)
//	void testFindAllUsersByRoleName() {
//		assertThat(service.findUsersByRoleName(rolename)).isNotNull();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#findByFirstNameContainingIgnoreCase(java.lang.String)}.
//	 */
//	@Test
//	@Order(17)
//	void testFindByFirstNameContainingIgnoreCase() {
//		assertThat(service.findByFirstNameContainingIgnoreCase(firstname)).isNotNull();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#findByLastNameContainingIgnoreCase(java.lang.String)}.
//	 */
//	@Test
//	@Order(18)
//	void testFindByLastNameContainingIgnoreCase() {
//		assertThat(service.findByLastNameContainingIgnoreCase(lastname)).isNotEmpty();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#findOne(long)}.
//	 */
//	@Test
//	@Order(19)
//	void testFindOne() {
//		assertThat(service.findOne(user_id)).isNotNull();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#findByEmail(java.lang.String)}.
//	 */
//	@Test
//	@Order(20)
//	void testFindByEmail() {
//		assertThat(service.findByEmail(email)).isNotNull();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#findByFirstNameAndLastName(java.lang.String, java.lang.String)}.
//	 */
//	@Test
//	@Order(21)
//	void testFindByFirstNameAndLastName() {
//		assertThat(service.findByFirstNameAndLastName(firstname, lastname)).isNotEmpty();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#findUserByFirstnamePaged(java.lang.String, int, int)}.
//	 */
//	@Test
//	@Order(22)
//	void testFindUserByFirstnamePaged() {
//		assertThat(service.findUserByFirstnamePaged(firstname, 0, 2).size()).isEqualTo(1);
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#findUserByFirstnameSorted(java.lang.String, org.springframework.data.domain.Sort)}.
//	 */
//	@Test
//	@Order(23)
//	void testFindUserByFirstnameSorted() {
//
//		List<User> users = service.findUserByFirstnameSorted(firstname, sortBy, sort);
//		System.out.println("users : ");
//		users.forEach(u -> System.out.println("Firstname : " + u.getFirstName()));
//
//		assertThat(service.findUserByFirstnameSorted(firstname, sortBy, sort)).isNotEmpty();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#generateLoginname(com.bytmasoft.api.models.User)}.
//	 */
////	@Test
////	@Order(24)
//	void testGenerateLoginname() {
//		User user = service.findOne(user_id);
//		assertThat(service.generateLoginname(user)).isNotNull();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#update(com.bytmasoft.api.models.User)}.
//	 */
//	@Test
//	@Order(25)
//	void testUpdate() {
//
//		User user = service.findOne(user_id);
//
//		user.setUpdatedBy(updatedBy);
//		user.setType(UserType.STUDENT);
//		service.update(user);
//
//		User user1 = service.findOne(user_id);
//
//		assertThat(user1.getUpdatedBy()).isEqualTo(updatedBy);
//
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#findByLastName(java.lang.String)}.
//	 */
//	@Test
//	@Order(26)
//	void testFindByLastName() {
//		assertThat(service.findByLastName(lastname)).isNotEmpty();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#findByFirstName(java.lang.String)}.
//	 */
//	@Test
//	@Order(27)
//	void testFindByFirstName() {
//		service.findByFirstName(firstname);
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#findByMiddelName(java.lang.String)}.
//	 */
//	@Test
//	@Order(28)
//	void testFindByMiddelName() {
//		assertThat(service.findByMiddelName(middelname)).isNotNull();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#findByBirthday(java.time.LocalDate)}.
//	 */
//	@Test
//	@Order(29)
//	void testFindByBirthday() {
//		assertThat(service.findByBirthday(birthday)).isNotNull();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#findByStatus(java.lang.String)}.
//	 */
//	@Test
//	@Order(30)
//	void testFindByStatus() {
//		assertThat(service.findByStatus(status)).isNotNull();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#findByLastLogin(java.time.LocalDateTime)}.
//	 */
//	@Test
//	@Order(31)
//	void testFindByLastLogin() {
//		assertThat(service.findByLastLogin(lastLogin)).isNotNull();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#findByLoginname(java.lang.String)}.
//	 */
//	@Test
//	@Order(32)
//	void testFindByLoginname() {
//		assertThat(service.findByLoginname(loginname)).isNotNull();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#findByType(com.bytmasoft.api.enums.UserType)}.
//	 */
//	@Test
//	@Order(33)
//	void testFindByType() {
//		assertThat(service.findByType(type)).isNotEmpty();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#findUsersByAgeLessThan(java.lang.Long)}.
//	 */
//	@Test
//	@Order(34)
//	void testFindUsersByAgeLessThan() {
//		assertThat(service.findUsersByAgeLessThan(age)).isNotEmpty();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#checkIfPasswordValid(com.bytmasoft.api.models.User, java.lang.String)}.
//	 */
////	@Test
//	@Order(36)
//	void testCheckIfPasswordValid() {
//		User user = service.findOne(user_id);
//		assertThat(service.checkIfPasswordValid(user, oldPassword)).isTrue();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#findUsersByAgeMoreThan(java.lang.Long)}.
//	 */
//	@Test
//	@Order(37)
//	void testFindUsersByAgeMoreThan() {
//		assertThat(service.findUsersByAgeMoreThan(age)).isNotNull();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#findByRequestParams(java.util.Map)}.
//	 */
//	@Test
//	@Order(38)
//	void testFindByRequestParams() {
//		Map<String, String> params = new HashMap<>();
//		params.put("email", email);
//		params.put("firstname", firstname);
//
//		assertThat(service.findByRequestParams(params)).isNotEmpty();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#findByFirstNameAndLastNameAndEmail(java.lang.String, java.lang.String, java.lang.String)}.
//	 */
//	@Test
//	@Order(39)
//	void testFindByFirstNameAndLastNameAndEmail() {
//		assertThat(service.findByFirstNameAndLastNameAndEmail(firstname, lastname, email)).isNotEmpty();
//	}
//
//	/**
//	 * Test method for
//	 * {@link com.bytmasoft.persistance.services.UserManagementServiceImpl#findByFirstNameAndLastNameAndLoginname(java.lang.String, java.lang.String, java.lang.String)}.
//	 */
//	@Test
//	@Order(40)
//	void testFindByFirstNameAndLastNameAndLoginname() {
//		assertThat(service.findByFirstNameAndLastNameAndLoginname(firstname, lastname, loginname)).isNotEmpty();
//	}
//
//	/**
//	 * @return
//	 */
//	private List<User> getUsers() {
//		List<User> users = new ArrayList<>();
//
//		User user = new User();
//		user.setFirstName("Mahamat");
//		user.setLastName("Abakar");
//		user.setEmail("abakar321@web.de");
//		user.setPassword("Aba14mah?");
//		user.setType(UserType.MANAGER);
//		user.setGender(GenderType.Mr);
//		user.setStatus("A");
//
//		User user1 = new User();
//		user1.setFirstName("Tasnim");
//		user1.setLastName("Abakar");
//		user1.setEmail("tasnim@web.de");
//		user1.setPassword("Aba14mah?");
//		user1.setType(UserType.TEACHER);
//		user1.setGender(GenderType.Mrs);
//		user1.setStatus("A");
//
//		User user2 = new User();
//		user2.setFirstName("Amal");
//		user2.setLastName("Frikh");
//		user2.setEmail("amal@web.de");
//		user2.setPassword("Aba14mah");
//		user2.setType(UserType.STUDENT);
//		user2.setGender(GenderType.Mrs);
//		user2.setStatus("A");
//
//		User user3 = new User();
//		user3.setFirstName("Roushed");
//		user3.setLastName("Abakar");
//		user3.setEmail("roushed@web.de");
//		user3.setPassword("Aba14mah2");
//		user3.setType(UserType.MANAGER);
//		user3.setGender(GenderType.Mr);
//		user3.setStatus("A");
//
//		User user4 = new User();
//		user4.setFirstName("Asha");
//		user4.setLastName("Abakar");
//		user4.setEmail("asha@web.de");
//		user4.setPassword("Aba14mah");
//		user4.setType(UserType.EMPLOYEE);
//		user4.setGender(GenderType.Mrs);
//		user4.setStatus("A");
//
//		User user5 = new User();
//		user5.setFirstName("Mohi");
//		user5.setLastName("Abakar");
//		user5.setEmail("mohi@web.de");
//		user5.setPassword("Aba14mah");
//		user5.setType(UserType.TEACHER);
//		user5.setGender(GenderType.Mr);
//		user5.setStatus("A");
//
//		User user6 = new User();
//		user6.setFirstName("Mahamat");
//		user6.setMiddelName("Ahmed");
//		user6.setLastName("Allawane");
//		user6.setEmail("allwane@web.de");
//		user6.setPassword("Aba14mah");
//		user6.setType(UserType.MANAGER);
//		user6.setGender(GenderType.Mr);
//		user6.setStatus("A");
//
//		users.add(user);
//		users.add(user1);
//		users.add(user2);
//		users.add(user3);
//		users.add(user4);
//		users.add(user5);
//		users.add(user6);
//		return users;
//	}

}
