/**
 * 
 */
package com.bytmasoft.login.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bytmasoft.domain.models.User;

/**
 * 
 * @author Mahamat Abakar Date 24.01.2020
 */

public interface LoginRepository extends CrudRepository<User, Long> {

	/**
	 * @param username
	 * @return
	 */
	List<User> findByUsername(String username);

	User findByUsernameOrEmail(String username, String email);

}
