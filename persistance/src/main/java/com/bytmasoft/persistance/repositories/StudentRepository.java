package com.bytmasoft.persistance.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bytmasoft.domain.models.Student;

/**
 * 
 * 
 * @author Mahamat Abakar Date 10.10.2019
 */
@Transactional(readOnly = true)
@Repository
public interface StudentRepository extends DSSUserBaseRepository<Student, Long> {

	
	/**
	 * @return
	 */
	@Query(value = "SELECT u.* FROM Student u JOIN Students_roles ur ON u.id = ur.Student_id WHERE ur.role_id=:role_id ", nativeQuery = true)
	List<Student> findStudentsByRoleName(@Param("role_id") long role_id);

	
	@Query(value = "FROM Student WHERE firstName = ?1 ORDER BY lastName ASC")
	public List<Student> findByFirstNameOrderByLastName(String firstname);

	@Query(value = "From Student")
	public List<Student> findAllSortedBy(Sort sort);

	@Query(value = "SELECT COUNT(s) FROM Student s WHERE s.status ='A'")
	Long countActiveResources();

	@Query(value = "SELECT COUNT(s) FROM Student s WHERE s.status ='I'")
	Long countAllInActiveStudents();

	@Modifying
	@Transactional
	@Query(value = "UPDATE Student s set s.status ='A'", nativeQuery = true)
	void activateAll();

	@Modifying
	@Transactional
	@Query(value = "UPDATE Student s set s.status ='I'", nativeQuery = true)
	void deactivateAll();

	@Modifying
	@Transactional
	@Query(value = "delete from Student s where s.status = 'I'", nativeQuery = true)
	public void deleteInActiveStudents();


	/**
	 * @param Student_id
	 * @param role_id
	 */
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO Student_role(Student_id, role_id) VALUES(:Student_id, :role_id)", nativeQuery = true)
	void assignRoleToUser(@Param("Student_id") Long Student_id, @Param("role_id") Long role_id);

	/**
	 * @param user_id
	 * @param address_id
	 */	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO student_address(student_id, address_id) VALUES(:student_id, :address_id)", nativeQuery = true)
	void assignAddressToUser(@Param("student_id") Long student_id, @Param("address_id") Long address_id);

	
	/**
	 * @return
	 */
	@Query(value = "SELECT s.* FROM Student s JOIN student_role tr ON t.id = tr.teacher_id WHERE tr.role_id=:role_id ", nativeQuery = true)
	List<Student> findStudentsByRoleId(@Param("role_id") long role_id);

	/**
	 * @param Student_id
	 * @param address_id
	 */
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO Student_address(Student_id, address_id) VALUES(:Student_id, :address_id)", nativeQuery = true)
	void assignAddressToStudent(@Param("Student_id") Long Student_id, @Param("address_id") Long address_id);

	/**
	 * @param Student_id
	 */
	@Transactional
	@Modifying
	@Query(value = "update Student s set s.deletestatus = 1 where s.id=:Student_id)", nativeQuery = true)
	void remerkByStudentIdForDelete(@Param("Student_id") Long Student_id);

	@Query(value = "SELECT s.* FROM Student s WHERE s.username=:usernameOrEmail Or s.email=:usernameOrEmail", nativeQuery = true)
	Student findByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);


//	@Query(value = "FROM Student WHERE UPPER(firstName) LIKE %?#{[0].toUpperCase()}%")
//	public List<Student> findByFirstNameContainingIgnoreCase(String firstname);
//
//	 @Query(value = "FROM Student WHERE UPPER(lastName) LIKE %?#{[0].toUpperCase()}%")
//	public List<Student> findByLastNameContainingIgnoreCase(String lastName);

}
