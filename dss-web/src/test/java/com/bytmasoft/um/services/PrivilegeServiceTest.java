/**
 * @author Mahamat
 * Date 19.03.2020 : 19:28:00
 */
package com.bytmasoft.um.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bytmasoft.domain.models.Privilege;
import com.bytmasoft.persistance.service.interfaces.PrivilegeService;

/**
 * @author Mahamat Date 19.03.2020 : 19:28:00
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PrivilegeServiceTest {

	@Autowired
	private PrivilegeService service;
	private long p_Id = 1L;
	private String p_name = "Write";
	private Long role_Id = 7L;

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.PrivilegeServiceImpl#findOne(long)}.
	 */
	@Test
//	@Order(1)
	@Tag("privilege")
	void testFindOne() {
		assertThat(service.findOne(p_Id).getName()).isEqualTo("Read");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.PrivilegeServiceImpl#findByName(java.lang.String)}.
	 */
	@Test
	@Order(2)
	@Tag("privilege")
	void testFindByName() {
		assertThat(service.findByName(p_name).getName()).isEqualTo(p_name);
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.PrivilegeServiceImpl#findAllResources()}.
	 */
	@Test
	@Order(3)
	@Tag("privilege")
	void testFindAllResources() {
		assertThat(service.findAllResources()).isNotNull();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.PrivilegeServiceImpl#findAllActive()}.
	 */
	@Test
	@Order(4)
	@Tag("privilege")
	void testFindAllActive() {
		service.findAllActive().forEach(p -> {
			assertThat(p.getStatus()).isEqualTo("A");
		});
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.PrivilegeServiceImpl#findPrivilegeByRoleId(java.lang.Long)}.
	 */
	@Test
	@Order(5)
	@Tag("privilege")
	void testFindPrivilegeByRoleId() {
		assertThat(service.findPrivilegesByRoleId(role_Id)).isNotEmpty();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.PrivilegeServiceImpl#findAllInActive()}.
	 */
	@Test
	@Tag("privilege")
	void testFindAllInActive() {
		service.findAllInActive().forEach(p -> {
			assertThat(p.getStatus()).isEqualTo("I");

		});
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.PrivilegeServiceImpl#findAllPaginatedAndSorted(int, int, java.lang.String, java.lang.String)}.
	 */
	@Test
	@Order(7)
	@Tag("privilege")
	void testFindAllPaginatedAnSorted() {

		System.out.println("Before name 2");
		List<Privilege> privileges = service.findAllPaginatedAndSorted(0, 2, "Name", "asc");
		assertThat(privileges).isNotEmpty();

	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.PrivilegeServiceImpl#create(com.bytmasoft.api.models.Privilege)}.
	 */
	@Test
	@Order(1)
//	@Tag("privilege")
	void testCreate() {

		this.getPrivileges().forEach(p -> {

			assertThat(service.create(p)).isNotNull();
		});
	}

	/**
	 * // * Test method for
	 * {@link com.bytmasoft.persistance.services.PrivilegeServiceImpl#update(com.bytmasoft.api.models.Privilege)}.
	 * //
	 */
	@Test
	@Order(8)
	@Tag("privilege")
	void testUpdate() {

		Privilege p = service.findOne(p_Id);

		p.setUpdatedBy("Abakar");

		service.update(p);
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.PrivilegeServiceImpl#deleteById(long)}.
	 */
//	@Test
//	@Tag("privilege")
	void testDeleteById() {
		service.deleteById(p_Id);

		assertThat(service.findOne(p_Id)).isNull();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.PrivilegeServiceImpl#deleteAll()}.
	 */
//	@Test
//	@Tag("privilege")
	void testDeleteAll() {
		service.deactivateAll();

		assertThat(service.findAllResources()).isEmpty();
		;
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.PrivilegeServiceImpl#count()}.
	 */
	@Test
	@Order(9)
	@Tag("privilege")
	void testCount() {
		assertThat(service.count()).isEqualTo(4);
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.PrivilegeServiceImpl#countActiveResources()}.
	 */
	@Test
	@Order(10)
	@Tag("privilege")
	void testCountActiveResources() {
		assertThat(service.countActiveResources()).isGreaterThan(1);
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.PrivilegeServiceImpl#countInActiveResources()}.
	 */
	@Test
	@Order(11)
	@Tag("privilege")
	void testCountInActiveResources() {
		assertThat(service.countInActiveResources()).isLessThan(1);
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.PrivilegeServiceImpl#activateById(long)}.
	 */
	@Test
	@Order(12)
	@Tag("privilege")
	void testActivateById() {
		service.activateById(p_Id);

		assertThat(service.findOne(p_Id).getStatus()).isEqualTo("A");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.PrivilegeServiceImpl#deactivateById(long)}.
	 */
	@Test
	@Order(13)
	@Tag("privilege")
	void testDeactivateById() {
		service.deactivateById(p_Id);

		assertThat(service.findOne(p_Id).getStatus()).isEqualTo("I");

	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.PrivilegeServiceImpl#deactivateAll()}.
	 */
	@Test
	@Order(14)
	@Tag("privilege")
	void testDeactivateAll() {
		service.deactivateAll();
		service.findAllResources().forEach(p -> {
			assertThat(p.getStatus()).isEqualTo("I");
		});
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.PrivilegeServiceImpl#activateAll()}.
	 */
	@Test
	@Order(15)
	@Tag("privilege")
	void testActivateAll() {

		service.activateAll();
		service.findAllResources().forEach(p -> {
			assertThat(p.getStatus()).isEqualTo("A");
		});
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.PrivilegeServiceImpl#deleteAllInActiveResources()}.
	 */
//	@Test
//	@Tag("privilege")
	void testDeleteAllInActiveResources() {
		service.deleteAllInActiveResources();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.PrivilegeServiceImpl#findByRequestParams(java.util.Map)}.
	 */
	@Test
	@Tag("privilege")
	void testFindByRequestParams() {
		Map<String, String> params = new HashMap<>();
		params.putIfAbsent("name", "Delete");
		assertThat(service.findByRequestParams(params)).isNotNull();
	}

	private List<Privilege> getPrivileges() {

		List<Privilege> privileges = new ArrayList<>();

		Privilege p1 = new Privilege();
		p1.setName("Read");
		p1.setStatus("A");

		Privilege p2 = new Privilege();
		p2.setName("Write");
		p2.setStatus("A");

		Privilege p3 = new Privilege();
		p3.setName("Update");
		p3.setStatus("A");

		Privilege p4 = new Privilege();
		p4.setName("Delete");
		p4.setStatus("A");

		privileges.add(p1);
		privileges.add(p2);
		privileges.add(p3);
		privileges.add(p4);
		return privileges;
	}

}
