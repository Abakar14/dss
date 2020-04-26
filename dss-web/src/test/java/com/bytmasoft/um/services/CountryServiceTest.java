/**
 * @author Mahamat
 * Date 19.03.2020 : 19:26:34
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

import com.bytmasoft.domain.models.Country;
import com.bytmasoft.persistance.interfaces.CountryService;

/**
 * @author Mahamat Date 19.03.2020 : 19:26:34
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class CountryServiceTest {

	@Autowired
	CountryService service;
	private long country_id = 17L;
	private String name = "Egypt";
	private int pageSize = 2;
	private int pageNo = 0;
	private String sortBy = "Name";
	private String sortOrder = "desc";
	private String updatedBy = "Abakar";
	private Long address_id = 14L;

	@Test
	@Order(2)
	public void testLoadContext() {
		assertThat(service).isNotNull();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.CountryServiceImpl#findOne(long)}.
	 */
	@Test
	@Order(3)
	void testFindOne() {
		assertThat(service.findOne(country_id)).isNotNull();

	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.CountryServiceImpl#findByName(java.lang.String)}.
	 */
	@Test
	@Order(4)
	void testFindByName() {

		assertThat(service.findByName(name)).isNotNull();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.CountryServiceImpl#findAllResources()}.
	 */
	@Test
	@Order(5)
	void testFindAllResources() {

		assertThat(service.findAllResources()).isNotEmpty();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.CountryServiceImpl#findAllInActive()}.
	 */
	@Test
	void testFindAllInActive() {
		assertThat(service.findAllInActive()).isNotEmpty();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.CountryServiceImpl#findAllActive()}.
	 */
	@Test
	@Order(6)
	void testFindAllActive() {
		assertThat(service.findAllActive()).isNotEmpty();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.CountryServiceImpl#findAllPaginated(int, int)}.
	 */
	@Test
	@Order(7)
	void testFindAllPaginatedAndSorted() {
		assertThat(service.findAllPaginatedAndSorted(pageSize, pageNo, sortBy, sortOrder)).isNotEmpty();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.CountryServiceImpl#create(com.bytmasoft.Country.models.Country)}.
	 */
	@Test
	@Order(1)
	void testCreate() {

		getCountries().forEach(c -> {
			assertThat(service.create(c)).isNotNull();
		});
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.CountryServiceImpl#update(com.bytmasoft.Country.models.Country)}.
	 */
	@Test
	@Order(8)
	void testUpdate() {

		Country c = service.findOne(country_id);
//		c.setUpdatedBy(updatedBy);
		service.update(c);
		Country c1 = service.findOne(country_id);

//		assertThat(c1.getUpdatedBy()).isEqualTo(updatedBy);

	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.CountryServiceImpl#deleteById(long)}.
	 */
//	@Test
	void testDeleteById() {
		service.deleteById(country_id);
		assertThat(service.findOne(country_id)).isNull();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.CountryServiceImpl#deleteAll()}.
	 */
//	@Test
	void testDeleteAll() {
		service.deleteAll();
		assertThat(service.findAllResources()).isEmpty();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.CountryServiceImpl#count()}.
	 */
	@Test
	@Order(9)
	void testCount() {

		assertThat(service.count()).isGreaterThan(2);
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.CountryServiceImpl#countActiveResources()}.
	 */
	@Test
	@Order(10)
	void testCountActiveResources() {
		assertThat(service.countActiveResources()).isGreaterThan(3);
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.CountryServiceImpl#countInActiveResources()}.
	 */
	@Test
	@Order(16)
	void testCountInActiveResources() {
		assertThat(service.countInActiveResources()).isLessThan(2);
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.CountryServiceImpl#activateById(long)}.
	 */
	@Test
	@Order(15)
	void testActivateById() {
		service.activateById(country_id);

		assertThat(service.findOne(country_id).getStatus()).isEqualTo("A");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.CountryServiceImpl#deactivateById(long)}.
	 */
	@Test
	@Order(11)
	void testDeactivateById() {
		service.deactivateById(country_id);

		assertThat(service.findOne(country_id).getStatus()).isEqualTo("I");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.CountryServiceImpl#activateAll()}.
	 */
	@Test
	@Order(14)
	void testActivateAll() {
		service.activateAll();

		service.findAllResources().forEach(c -> {

			assertThat(c.getStatus()).isEqualTo("A");
		});
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.CountryServiceImpl#deactivateAll()}.
	 */
	@Test
	@Order(12)
	void testDeactivateAll() {
		service.deactivateAll();
		service.findAllResources().forEach(c -> {

			assertThat(c.getStatus()).isEqualTo("I");
		});
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.CountryServiceImpl#deleteAllInActiveResources()}.
	 */
//	@Test
	void testDeleteAllInActiveResources() {

		service.deleteAllInActiveResources();
		assertThat(service.findAllInActive()).isEmpty();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.CountryServiceImpl#assignAddressToCountry(java.lang.Long, java.lang.Long)}.
	 */
//	@Test
//	@Order(13)
	void testAssignAddressToCountry() {
		service.assignAddressToCountry(country_id, address_id);

		service.findOne(country_id).getAddresses().forEach(a -> {
			if (a.getId().equals(address_id)) {

				assertThat(a.getId()).isEqualTo(address_id);
			}
		});
	}

	/**
	 * @return
	 */
	private List<Country> getCountries() {

		List<Country> countries = new ArrayList<>();

		Country c1 = new Country();
		c1.setName("Egypt");

		Country c2 = new Country();
		c2.setName("Tschad");

		Country c3 = new Country();
		c3.setName("Turkei");

		countries.add(c1);
		countries.add(c2);
		countries.add(c3);
		return countries;
	}

}
