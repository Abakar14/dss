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

//	@Query(value = "SELECT u.* FROM User u WHERE u.username=:usernameOrEmail Or u.email=:usernameOrEmail", nativeQuery = true)
//	User findUserByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);
//
//	/**
//	 * @param Student_id
//	 * @param role_id
//	 */
//	@Transactional
//	@Modifying
//	@Query(value = "INSERT INTO User_role(user_id, role_id) VALUES(:user_id, :role_id)", nativeQuery = true)
//	void assignRoleToUser(@Param("user_id") Long user_id, @Param("role_id") Long role_id);
//
//	@Transactional
//	@Modifying
//	@Query(value = "update User u set u.password =:password  where u.id=:user_id)", nativeQuery = true)
//	void changePassword(@Param("user_id") Long Student_id, @Param("password") String password);

}
