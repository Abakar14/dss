/**
 * 
 */
package com.bytmasoft.login.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bytmasoft.domain.models.Teacher;
import com.bytmasoft.domain.models.BaseUser;

/**
 * 
 * @author Mahamat Abakar Date 24.01.2020
 */
@Repository
public interface TeacherLoginRepository extends CrudRepository<Teacher, Long> {

	/**
	 * @param username
	 * @return
	 */	
	BaseUser findByUsernameOrEmail(String username, String email);

}
