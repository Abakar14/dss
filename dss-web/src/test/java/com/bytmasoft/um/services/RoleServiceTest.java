/**
 * @author Mahamat
 * Date 19.03.2020 : 19:28:53
 */
package com.bytmasoft.um.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bytmasoft.domain.enums.RoleType;
import com.bytmasoft.domain.models.Role;
import com.bytmasoft.persistance.interfaces.PrivilegeService;
import com.bytmasoft.persistance.interfaces.RoleService;

/**
 * @author Mahamat Date 19.03.2020 : 19:28:53
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RoleServiceTest {

	@Autowired
	RoleService service;

	@Autowired
	PrivilegeService pService;

	private Long role_id = 6L;

	private String name = "User";

	private int pageSize = 0;

	private int pageNo = 2;

	private String sortBy = "name";

	private String sortOrder = "desc";

	private Long user_id = 9L;

	private String updatedBy = "Abakar";

	private String status = "A";

	private String type = "USER";

	@Test
	public void ContextLoads() {
		assertThat(service).isNotNull();
		assertThat(pService).isNotNull();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.RoleServiceImpl#findOne(long)}.
	 */
	@Test
//	@Order(1)
	void testFindOne() {
		assertThat(service.findOne(role_id)).isNotNull();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.RoleServiceImpl#findByName(java.lang.String)}.
	 */
	@Test
	@Order(2)
	void testFindByName() {
		service.findByName(name);
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.RoleServiceImpl#findAllResources()}.
	 */
	@Test
	@Order(3)
	void testFindAllResources() {
		assertThat(service.findAllResources()).isNotNull();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.RoleServiceImpl#findAllPaginatedAnSorted(int, int, java.lang.String, java.lang.String)}.
	 */
	@Test
	@Order(4)
	void testFindAllPaginatedAnSorted() {

		assertThat(service.findAllPaginatedAndSorted(pageSize, pageNo, sortBy, sortOrder)).isNotNull();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.RoleServiceImpl#findAllRolesForThisUserId(java.lang.Long)}.
	 */
	@Test
	@Order(5)
	void testFindAllRolesForThisUserId() {
		assertThat(service.findRolesByUserId(user_id)).isNotNull();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.RoleServiceImpl#findRoleByUserIdAndRoleId(java.lang.Long, java.lang.Long)}.
	 */
//	@Test
	@Order(6)
	void testFindOnlyOneRoleForThisUserId() {
		assertThat(service.findRoleByUserIdAndRoleId(user_id, role_id)).isNotNull();
	}

	/**
	 * Test Methode for
	 * {@link com.bytmasoft.persistance.services.RoleServiceImpl#addTypeToRole(Role, RoleType)}
	 */
//	@Test
	void testaddTypeToRole() {
		service.addTypeToRole(5L, RoleType.STUDENT);
		Role role1 = service.findOne(5L);
		assertThat(role1.getType()).isEqualTo(RoleType.STUDENT);

		service.addTypeToRole(9L, RoleType.ADMIN);
		Role role2 = service.findOne(9L);
		assertThat(role2.getType()).isEqualTo(RoleType.ADMIN);
	}

//	/**
//	 * Test method for {@link com.bytmasoft.persistance.services.RoleServiceImpl#create(com.bytmasoft.api.models.Role)}.
//	 */
	@Test
	@Order(1)
	void testCreate() {
		getRoles().forEach(r -> {
			assertThat(this.service.create(r)).isNotNull();
		});

	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.RoleServiceImpl#addPrivilegeToRole(java.lang.Long, java.lang.Long)}.
	 */
	@Test
	void testAddPrivilegeToRole() {

		service.findAllResources().forEach(r -> {
			if (r.getType().equals(RoleType.GUEST)) {

				pService.findAllResources().forEach(p -> {
					if (!p.getName().equals("Delete")) {

						service.addPrivilegeToRole(r.getId(), p.getId());
					}
				});

			} else {
				pService.findAllResources().forEach(p -> {

					service.addPrivilegeToRole(r.getId(), p.getId());

				});
			}
		});

	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.RoleServiceImpl#update(com.bytmasoft.api.models.Role)}.
	 */
	@Test
	@Order(7)
	void testUpdate() {
		Role role = service.findOne(role_id);
		role.setUpdatedBy(updatedBy);
		service.update(role);

		Role role1 = service.findOne(role_id);

		assertThat(role1.getUpdatedBy()).isEqualTo(updatedBy);

	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.RoleServiceImpl#deleteById(long)}.
	 */
//	@Test
	void testDeleteById() {
		service.deleteById(role_id);
		assertThat(service.findOne(role_id)).isNotNull();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.RoleServiceImpl#deleteAll()}.
	 */
//	@Test
	void testDeleteAll() {
		service.deleteAll();
		assertThat(service.findAllResources()).isEmpty();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.RoleServiceImpl#count()}.
	 */
//	@Test
	@Order(7)
	void testCount() {
		assertThat(service.count()).isGreaterThan(2);
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.RoleServiceImpl#countActiveResources()}.
	 */
//	@Test
	@Order(8)
	void testCountActiveResources() {
		assertThat(service.countActiveResources()).isGreaterThan(2);
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.RoleServiceImpl#countInActiveResources()}.
	 */
	@Test
	@Order(10)
	void testCountInActiveResources() {
		assertThat(service.countInActiveResources()).isLessThan(2);
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.RoleServiceImpl#deactivateById(long)}.
	 */
	@Test
	@Order(9)
	void testDeactivateById() {
		service.deactivateById(role_id);

		Role role = service.findOne(role_id);

		assertThat(role.getStatus()).isEqualTo("I");

	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.RoleServiceImpl#activateById(long)}.
	 */
//	@Test
	@Order(11)
	void testActivateById() {
		service.activateById(role_id);
		Role role = service.findOne(role_id);
		assertThat(role.getStatus()).isEqualTo("A");

	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.RoleServiceImpl#activateAll()}.
	 */
	@Test
	@Order(13)
	void testActivateAll() {
		service.activateAll();
		service.findAllResources().forEach(r -> {
			assertThat(r.getStatus()).isEqualTo("A");
		});

	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.RoleServiceImpl#deactivateAll()}.
	 */
	@Test
	@Order(12)
	void testDeactivateAll() {
		service.deactivateAll();
		service.findAllResources().forEach(r -> {
			assertThat(r.getStatus()).isEqualTo("I");
		});
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.RoleServiceImpl#deleteAllInActiveResources()}.
	 */
//	@Test
	void testDeleteAllInActiveResources() {
		service.deleteAllInActiveResources();
		assertThat(service.findAllInActive()).isNull();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.RoleServiceImpl#findByStatus(java.lang.String)}.
	 */
	@Test
	@Order(14)
	void testFindByStatus() {

		assertThat(service.findByStatus(status)).isNotEmpty();

	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.RoleServiceImpl#findByType(java.lang.String)}.
	 */
	@Test
	@Order(15)
	void testFindByType() {
		assertThat(service.findByType(type)).isNotNull();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.RoleServiceImpl#findAllInActive()}.
	 */
	@Test
	@Order(16)
	void testFindAllInActive() {
		assertThat(service.findAllInActive()).isNotNull();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.RoleServiceImpl#findAllActive()}.
	 */
	@Test
	@Order(17)
	void testFindAllActive() {
		assertThat(service.findAllActive()).isNotEmpty();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.RoleServiceImpl#findAllRolesByUserId(java.lang.Long)}.
	 */
	@Test
	@Order(18)
	void testFindRolesByUserId() {
		assertThat(service.findRolesByUserId(role_id)).isNotNull();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.RoleServiceImpl#findRoleByUserIdAndRoleId(java.lang.Long, java.lang.Long)}.
	 */
	@Test
	@Order(19)
	void testFindRoleByUserIdAndRoleId() {

		assertThat(service.findRoleByUserIdAndRoleId(user_id, role_id)).isNotNull();
	}

	private List<Role> getRoles() {

		List<Role> roles = new ArrayList<>();

		Role role1 = new Role();
		role1.setName("Student");
		role1.setStatus("A");
		role1.setType(RoleType.STUDENT);

		Role role2 = new Role();
		role2.setName("Admin");
		role2.setStatus("A");
		role2.setType(RoleType.ADMIN);

		Role role3 = new Role();
		role3.setName("Teacher");
		role3.setStatus("A");
		role3.setType(RoleType.TEACHER);

		Role role4 = new Role();
		role4.setName("Guest");
		role4.setStatus("A");
		role4.setType(RoleType.GUEST);

		roles.add(role1);
		roles.add(role2);
		roles.add(role3);
		roles.add(role4);
		return roles;
	}

}
