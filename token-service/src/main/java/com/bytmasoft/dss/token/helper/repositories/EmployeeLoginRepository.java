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
public interface EmployeeLoginRepository extends CrudRepository<Student, Long> {

	/**
	 * @param username
	 * @return
	 */
//	BaseUser findByUsernameOrEmail(String username, String email);
	@Query(value = "SELECT e.* FROM Employee e WHERE e.username=:usernameOrEmail Or e.email=:usernameOrEmail", nativeQuery = true)
	Student findByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);

}
