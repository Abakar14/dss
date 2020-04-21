package com.bytmasoft.persistance.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bytmasoft.domain.models.Address;
import com.bytmasoft.persistance.interfaces.UmBaseRepository;;

/**
 * 
 * 
 * @author Mahamat Abakar Date 01.12.2019
 */
@Repository
public interface AddressRepository extends UmBaseRepository<Address, Long> {

	/**
	 * 
	 * @param street
	 * @return List of {@link Address}
	 */
	public List<Address> findByStreet(String street);

	/**
	 * 
	 * @param city
	 * @return List of {@link Address}
	 */
	public List<Address> findByCity(String city);

	/**
	 * 
	 * @param postalCode
	 * @return List of {@link Address}
	 */
	public List<Address> findByPostalCode(String postalCode);

	/**
	 * @return list of {@link Address}
	 */
	@Query(value = "FROM Address a WHERE a.status ='I'")
	List<Address> findInActiveAddresses();

	/**
	 * @return list of {@link Address}
	 */
	@Query(value = "FROM Address a WHERE a.status ='A'")
	List<Address> findActiveAddresses();

	/**
	 * @return
	 */
	@Query(value = "SELECT COUNT(a) FROM Address a WHERE a.status ='A'")
	long countActiveResources();

	/**
	 * @return
	 */
	@Query(value = "SELECT COUNT(a) FROM Address a WHERE a.status ='I'")
	long countInActiveAddresses();

	/**
	 * 
	 */
	@Modifying
	@Transactional
	@Query(value = "UPDATE Address a set a.status ='I'", nativeQuery = true)
	void deactivateAll();

	@Modifying
	@Transactional
	@Query(value = "UPDATE Address a set a.status ='A'", nativeQuery = true)
	void activateAll();

	@Modifying
	@Transactional
	@Query(value = "delete from Address a where a.status = 'I'")
	void deleteInActiveAddress();

	/**
	 * @param user_id
	 * @return
	 */
	@Query(value = "SELECT a.* FROM Address a JOIN user_address ua ON a.id = ua.address_id WHERE ua.user_id=:user_id ", nativeQuery = true)
	Address findAddressByUserId(@Param("user_id") long user_id);

}
