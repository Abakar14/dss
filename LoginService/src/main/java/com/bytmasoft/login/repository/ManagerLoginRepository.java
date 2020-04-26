/**
 * 
 */
package com.bytmasoft.login.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bytmasoft.domain.models.BaseUser;
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
	BaseUser findByUsernameOrEmail(String username, String email);

}
