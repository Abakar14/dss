package com.bytmasoft.persistance.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bytmasoft.domain.models.Country;
import com.bytmasoft.persistance.interfaces.UmBaseRepository;;

/**
 * 
 * 
 * @author Mahamat Abakar Date 01.12.2019
 */
@Repository
public interface CountryRepository extends UmBaseRepository<Country, Long> {

	/**
	 * @param name
	 * @return list of {@link Country}
	 */
	Optional<Country> findByName(String name);
	
	/**
	 * @return list of {@link Country}
	 */
	List<Country> findByStatus(String status);

	/**
	 * 
	 */
	@Modifying
	@Transactional
	@Query(value = "UPDATE Country c set c.status ='I'", nativeQuery = true)
	void deactivateAll();

	@Modifying
	@Transactional
	@Query(value = "UPDATE Country c set c.status ='A'", nativeQuery = true)
	void activateAll();

	@Modifying
	@Transactional
	@Query(value = "delete from Country c where c.status = 'I'")
	void deleteInActiveCountry();

	/**
	 * @param country_id
	 * @param address_id
	 */
	@Modifying
	@Transactional
	@Query(value = "update address set country_id =:country_id where id =:address_id", nativeQuery = true)
	void assignAddressToCountry(@Param("country_id") Long country_id, @Param("address_id") Long address_id);

}
