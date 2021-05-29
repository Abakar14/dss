/**
 * 
 */
package com.bytmasoft.dss.token.helper.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bytmasoft.domain.models.Student;

/**
 * 
 * @author Mahamat Abakar Date 24.01.2020
 */
@Repository
public interface StudentLoginRepository extends CrudRepository<Student, Long> {

	/**
	 * @param username
	 * @return
	 */
//	BaseUser findByUsernameOrEmail(String username, String email);
	@Query(value = "SELECT s.* FROM Student s WHERE s.username=:usernameOrEmail Or s.email=:usernameOrEmail", nativeQuery = true)
	Student findByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);

	@Query(value = "SELECT s.* FROM Student s WHERE (s.username=:usernameOrEmail Or s.email=:usernameOrEmail) and s.password=:password", nativeQuery = true)
	Student findByUsernameOrEmailAndPassword(@Param("usernameOrEmail") String usernameOrEmail,
			@Param("password") String password);

}
