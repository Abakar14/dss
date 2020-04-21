/**
 * @author Mahamat
 * Date 19.03.2020 : 19:23:01
 */
package com.bytmasoft.um.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bytmasoft.domain.enums.AddressType;
import com.bytmasoft.domain.models.Address;
import com.bytmasoft.domain.models.Country;
import com.bytmasoft.persistance.interfaces.AddressService;

/**
 * @author Mahamat Date 19.03.2020 : 19:23:01
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressServiceTest {

	@Autowired
	AddressService service;
	private String street = "Konrad-Adenauerstr";

	private Long addressId = 12L;
	private String city = "Weinheim";
	private String postalCode = "69469";
	private String hauseNumber;
	private Long userId = 9L;

	@Test
	void userServiceTest() {
		assertThat(service).isNotNull();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.AddressServiceImpl#findOne(long)}.
	 */
	@Test
	void testFindOne() {
		Address address = service.findOne(addressId);

		assertNotNull(address);
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.AddressServiceImpl#findAllActive()}.
	 */
	@Test
	void testFindAllActive() {
		List<Address> addresses = service.findAllActive();

		assertNotNull(addresses);
		assertTrue(addresses.size() > 0);
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.AddressServiceImpl#findAllInActive()}.
	 */
	@Test
	void testFindAllInActive() {
		List<Address> addresses = service.findAllInActive();

		assertNotNull(addresses);

	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.AddressServiceImpl#findByCity(java.lang.String)}.
	 */
	@Test
	void testFindByCity() {
		List<Address> addresses = service.findByCity(city);

		assertNotNull(addresses);
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.AddressServiceImpl#findByStreet(java.lang.String)}.
	 */
	@Test
	void testFindByStreet() {

		List<Address> addresses = service.findByStreet(street);

		assertNotNull(addresses);
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.AddressServiceImpl#findByPostalCode(java.lang.String)}.
	 */
	@Test
	void testFindByPostalCode() {

		List<Address> addresses = service.findByPostalCode(postalCode);
		assertNotNull(addresses);

	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.AddressServiceImpl#findAllResources()}.
	 */
	@Test
	void testFindAllResources() {
		List<Address> addresses = service.findAllResources();

		assertNotNull(addresses);
		assertFalse(addresses.isEmpty());
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.AddressServiceImpl#findAllPaginatedAndSorted(int, int, java.lang.String, java.lang.String)}.
	 */
	@Test
	void testFindAllPaginatedAnSorted() {
		List<Address> addresses = service.findAllPaginatedAndSorted(0, 2, "city", "desc");
		assertThat(addresses).isNotNull();

	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.AddressServiceImpl#create(com.bytmasoft.api.models.Address)}.
	 */
	@Test
	@Order(1)
	void testCreate() {

		getAddresses().forEach(a -> {
			assertThat(service.create(a)).isNotNull();
		});

	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.AddressServiceImpl#update(com.bytmasoft.api.models.Address)}.
	 */
	@Test
	void testUpdate() {
		List<Address> addresses = service.findByCity(city);

		assertNotNull(addresses);

		Address address = addresses.get(0);
		assertNotNull(address);

		address.setPostalCode(postalCode);
		address.setStreet(street);
		address.setHauseNumber(hauseNumber);

//		address.setCity(updatedCity);

		service.update(address);

//		Address addressUpdated = addressService.findOne(address.getId());

	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.AddressServiceImpl#deleteById(long)}.
	 */
//	@Test
	void testDeleteById() {

		service.deleteById(addressId);
		assertThat(service.findOne(addressId)).isNull();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.AddressServiceImpl#deleteAll()}.
	 */
//	@Test
	void testDeleteAll() {
		service.deleteAll();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.AddressServiceImpl#count()}.
	 */
	@Test
	void testCount() {
		assertThat(service.count()).isGreaterThan(2);
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.AddressServiceImpl#countActiveResources()}.
	 */
	@Test
	void testCountActiveResources() {
		assertThat(service.countActiveResources()).isGreaterThan(2);
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.AddressServiceImpl#countInActiveResources()}.
	 */
//	@Test
	void testCountInActiveResources() {
		assertThat(service.countInActiveResources()).isEqualTo(0);
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.AddressServiceImpl#activateById(long)}.
	 */
//	@Test
	void testActivateById() {
		service.activateById(addressId);
		assertThat(service.findOne(addressId).getStatus()).isEqualTo("A");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.AddressServiceImpl#deactivateById(long)}.
	 */
//	@Test
	void testDeactivateById() {
		service.deactivateById(addressId);
		assertThat(service.findOne(addressId)).isEqualTo("I");
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.AddressServiceImpl#activateAll()}.
	 */
//	@Test
	void testActivateAll() {

		List<Address> addresses = service.findAllResources();

		for (Address address : addresses) {

			service.activateById(address.getId());
		}

		List<Address> addresses2 = service.findAllActive();

		assertNotNull(addresses2);

		assertTrue(!addresses2.isEmpty());
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.AddressServiceImpl#deactivateAll()}.
	 */
//	@Test
	void testDeactivateAll() {
		service.deactivateAll();
		service.findAllResources().forEach(a -> {
			assertThat(a.getStatus()).isEqualTo("I");
		});
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.AddressServiceImpl#deleteAllInActiveResources()}.
	 */
//	@Test
	void testDeleteAllInActiveResources() {
		service.deleteAllInActiveResources();
		assertThat(service.findAllInActive()).isEmpty();
	}

	/**
	 * Test method for
	 * {@link com.bytmasoft.persistance.services.AddressServiceImpl#findCountryByUserId(long)}.
	 */
	@Test
	void testFindAddressByUserId() {

		assertThat(service.findAddressByUserId(userId)).isNotNull();
	}

	private List<Address> getAddresses() {

		List<Address> addresses = new ArrayList<>();

		Country c1 = new Country();
		c1.setName("Deutschland");
		c1.setStatus("A");

		Country c2 = new Country();
		c2.setName("France");
		c2.setStatus("A");

		Country c3 = new Country();
		c3.setName("Sudan");
		c3.setStatus("A");

		Address ad1 = new Address();
		ad1.setCity("Weinheim");
		ad1.setCountry(c1);
		ad1.setHauseNumber("9");
		ad1.setPostalCode("69469");
		ad1.setStatus("A");
		ad1.setStreet("Konrad-Adenauerstr");
		ad1.setType(AddressType.HOME);

		Address ad2 = new Address();
		ad2.setCity("Paris");
		ad2.setCountry(c2);
		ad2.setHauseNumber("9");
		ad2.setPostalCode("69469");
		ad2.setStatus("A");
		ad2.setStreet("Chare de Gole");
		ad2.setType(AddressType.BUSINESS);

		Address ad3 = new Address();
		ad3.setCity("Oumdurman");
		ad3.setCountry(c3);
		ad3.setHauseNumber("9");
		ad3.setPostalCode("69469");
		ad3.setStatus("A");
		ad3.setStreet("Ali Dinnar");
		ad3.setType(AddressType.CONTRACT);

		addresses.add(ad1);
		addresses.add(ad2);
		addresses.add(ad3);

		return addresses;

	}

}
