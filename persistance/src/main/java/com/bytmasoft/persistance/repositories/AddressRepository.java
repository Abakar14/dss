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
	 * @param street
	 * @return List of {@link Address}
	 */
	public List<Address> findByStatus(String status);

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
