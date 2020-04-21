/**
 * @author Mahamat
 * Date 09.04.2020 : 21:24:30
 */
package com.bytmasoft.um.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bytmasoft.domain.enums.SchoolType;
import com.bytmasoft.domain.models.Address;
import com.bytmasoft.domain.models.School;
import com.bytmasoft.persistance.repositories.SchoolRepository;
import com.bytmasoft.persistance.services.AddressServiceImpl;
import com.bytmasoft.persistance.services.SchoolServiceImpl;
import com.bytmasoft.persistance.services.UserManagementServiceImpl;

/**
 * @author Mahamat Date 09.04.2020 : 21:24:30
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SchoolServiceTest {

	@Autowired
	SchoolServiceImpl service;

	@Autowired
	AddressServiceImpl addressServie;

	@Autowired
	UserManagementServiceImpl userService;

	private long address_id = 3L;

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.SchoolServiceImpl#findOne(long)}.
	 */
	@Test
	void testFindOne() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.SchoolServiceImpl#findAllResources()}.
	 */
	@Test
	void testFindAllResources() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.SchoolServiceImpl#findAllInActive()}.
	 */
	@Test
	void testFindAllInActive() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.SchoolServiceImpl#findAllActive()}.
	 */
	@Test
	void testFindAllActive() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.SchoolServiceImpl#findAllPaginatedAndSorted(int, int, java.lang.String, java.lang.String)}.
	 */
	@Test
	void testFindAllPaginatedAndSorted() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.SchoolServiceImpl#create(com.bytmasoft.domain.models.School)}.
	 */
	@Test
	void testCreate() {
		getSchools().forEach(s -> {
			assertThat(service.create(s)).isNotNull();

		});
		;
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.SchoolServiceImpl#update(com.bytmasoft.domain.models.School)}.
	 */
	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.SchoolServiceImpl#deleteById(long)}.
	 */
	@Test
	void testDeleteById() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.SchoolServiceImpl#deleteAll()}.
	 */
	@Test
	void testDeleteAll() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.SchoolServiceImpl#deleteAllInActiveResources()}.
	 */
	@Test
	void testDeleteAllInActiveResources() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.SchoolServiceImpl#count()}.
	 */
	@Test
	void testCount() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.SchoolServiceImpl#countActiveResources()}.
	 */
	@Test
	void testCountActiveResources() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.SchoolServiceImpl#countInActiveResources()}.
	 */
	@Test
	void testCountInActiveResources() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.SchoolServiceImpl#activateById(long)}.
	 */
	@Test
	void testActivateById() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.SchoolServiceImpl#deactivateById(long)}.
	 */
	@Test
	void testDeactivateById() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.SchoolServiceImpl#activateAll()}.
	 */
	@Test
	void testActivateAll() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.SchoolServiceImpl#deactivateAll()}.
	 */
	@Test
	void testDeactivateAll() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.SchoolServiceImpl#findByName(java.lang.String)}.
	 */
	@Test
	void testFindByName() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.SchoolServiceImpl#findByType(com.bytmasoft.domain.enums.SchoolType)}.
	 */
	@Test
	void testFindByType() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.SchoolServiceImpl#findUsersBySchoolId(java.lang.Long)}.
	 */
	@Test
	void testFindUsersBySchoolId() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.SchoolServiceImpl#findAddressBySchoolId(java.lang.Long)}.
	 */
	@Test
	void testFindAddressBySchoolId() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.SchoolServiceImpl#setUpdateParams(com.bytmasoft.domain.models.School)}.
	 */
	@Test
	void testSetUpdateParams() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.SchoolServiceImpl#SchoolServiceImpl(SchoolRepository)}.
	 */
	@Test
	void testSchoolServiceImpl() {
		assertThat(service).isNotNull();
	}

	private List<School> getSchools() {
		List<School> schools = new ArrayList<>();

		School s1 = new School();
		s1.setName("Wadi Sedina School");
		s1.setStatus("A");
		s1.setType(SchoolType.HIGHERSECONDARY);
		Address a = addressServie.findOne(address_id);
		s1.setAddress(a);

		userService.findAllResources().forEach(u -> {
			s1.getUsers().add(u);
		});

		schools.add(s1);
		return schools;
	}

}
