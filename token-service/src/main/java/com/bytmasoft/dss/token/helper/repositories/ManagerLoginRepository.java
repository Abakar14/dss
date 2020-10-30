/**
 * 
 */
package com.bytmasoft.dss.token.helper.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bytmasoft.domain.models.Manager;

/**
 * 
 * @author Mahamat Abakar Date 24.01.2020
 */
@Repository
public interface ManagerLoginRepository extends CrudRepository<Manager, Long> {

	/**
	 * @param username
	 * @return
	 */	
//	BaseUser findByUsernameOrEmail(String username, String email);
	
	@Query(value = "SELECT m.* FROM Manager m WHERE m.username=:usernameOrEmail Or m.email=:usernameOrEmail", nativeQuery = true)
	Manager findByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);


}
