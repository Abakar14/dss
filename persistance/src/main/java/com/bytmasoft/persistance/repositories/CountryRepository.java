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
	@Query(value = "FROM Country WHERE name = ?1")
	Optional<Country> findByName(String name);

	/**
	 * @return list of {@link Country}
	 */
	@Query(value = "FROM Country c WHERE c.status ='I'")
	List<Country> findAllInActiveCountries();

	/**
	 * @return list of {@link Country}
	 */
	@Query(value = "FROM Country c WHERE c.status ='A'")
	List<Country> findAllActiveCountries();

	/**
	 * @return
	 */
	@Query(value = "SELECT COUNT(c) FROM Country c WHERE c.status ='A'")
	long countActiveResources();

	/**
	 * @return
	 */
	@Query(value = "SELECT COUNT(c) FROM Country c WHERE c.status ='I'")
	long countAllInActiveUsers();

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
	 * @param user_id
	 * @return
	 */
//	@Query(value = "SELECT c.* FROM Country c JOIN User u ON c.id = u.country_id WHERE u.id=:user_id ", nativeQuery = true)
//	Country findCountryByUserId(@Param("user_id") long user_id);

	/**
	 * @param country_id
	 * @param address_id
	 */
	@Modifying
	@Transactional
	@Query(value = "update address set country_id =:country_id where id =:address_id", nativeQuery = true)
	void assignAddressToCountry(@Param("country_id") Long country_id, @Param("address_id") Long address_id);

}
