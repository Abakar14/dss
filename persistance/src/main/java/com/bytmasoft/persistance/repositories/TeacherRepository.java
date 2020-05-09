package com.bytmasoft.persistance.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bytmasoft.domain.models.Teacher;

/**
 * 
 * 
 * @author Mahamat Abakar Date 10.10.2019
 */
@Transactional(readOnly = true)
@Repository
public interface TeacherRepository extends DSSUserBaseRepository<Teacher, Long> {



	@Query(value = "SELECT t.* FROM Teacher t WHERE t.username=:usernameOrEmail Or t.email=:usernameOrEmail", nativeQuery = true)
	Teacher findByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);



	/**
	 * @return
	 */
	@Query(value = "SELECT t.* FROM Teacher t JOIN teacher_role tr ON t.id = tr.teacher_id WHERE tr.role_id=:role_id ", nativeQuery = true)
	List<Teacher> findTeachersByRoleId(@Param("role_id") long role_id);

	

	
	@Query(value = "FROM Teacher WHERE firstName = ?1 ORDER BY lastName ASC")
	public List<Teacher> findByFirstNameOrderByLastName(String firstname);

	@Query(value = "From Teacher")
	public List<Teacher> findAllSortedBy(Sort sort);

	@Query(value = "SELECT COUNT(t) FROM Teacher t WHERE t.status ='A'")
	long countActiveResources();

	@Query(value = "SELECT COUNT(t) FROM Teacher t WHERE t.status ='I'")
	long countAllInActiveUsers();

	@Modifying
	@Transactional
	@Query(value = "UPDATE Teacher t set t.status ='A'", nativeQuery = true)
	void activateAll();

	@Modifying
	@Transactional
	@Query(value = "UPDATE Teacher t set t.status ='I'", nativeQuery = true)
	void deactivateAll();

	@Modifying
	@Transactional
	@Query(value = "delete from Teacher t where t.status = 'I'", nativeQuery = true)
	public void deleteInActiveUsers();

	
	/**
	 * @param user_id
	 * @param role_id
	 */
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO teacher_role(teacher_id, role_id) VALUES(:teacher_id, :role_id)", nativeQuery = true)
	void assignRoleToUser(@Param("teacher_id") Long teacher_id, @Param("role_id") Long role_id);

	/**
	 * @param user_id
	 * @param address_id
	 */	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO teacher_address(teacher_id, address_id) VALUES(:teacher_id, :address_id)", nativeQuery = true)
	void assignAddressToUser(@Param("teacher_id") Long teacher_id, @Param("address_id") Long address_id);

	/**
	 * @param user_id
	 */
	@Transactional
	@Modifying
	@Query(value = "update teacher t set t.deletestatus = 1 where t.id=:teacher_id)", nativeQuery = true)
	void remerkByUserIdForDelete(@Param("teacher_id") Long teacher_id);
	
}
