/**
 * 
 */
package com.bytmasoft.dss.token.helper.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bytmasoft.domain.models.Teacher;

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
//	BaseUser findByUsernameOrEmailAddress(String username, String email);
	@Query(value = "SELECT t.* FROM Teacher t WHERE t.username=:usernameOrEmail Or t.email=:usernameOrEmail", nativeQuery = true)
	Teacher findByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);


}
