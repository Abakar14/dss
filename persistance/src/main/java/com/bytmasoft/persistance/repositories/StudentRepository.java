package com.bytmasoft.persistance.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bytmasoft.domain.models.Student;
import com.bytmasoft.persistance.interfaces.UmBaseRepository;

/**
 * 
 * 
 * @author Mahamat Abakar Date 10.10.2019
 */
@Transactional(readOnly = true)
@Repository
public interface StudentRepository extends UmBaseRepository<Student, Long> {

	/**
	 * 
	 * @param email
	 * @return
	 */	
	List<Student> findByEmail(String email);

	/**
	 * 
	 * @param lastName
	 * @return a list of {@link Student}
	 */
	List<Student> findByLastName(String lastName);

	/**
	 * 
	 * @param fistName
	 * @return @return a list of {@link Student}
	 */
	List<Student> findByfirstName(String firstName);

	/**
	 * 
	 * @param birthday
	 * @return a list of {@link Student}
	 */
	List<Student> findByBirthday(LocalDate birthday);

	/**
	 * 
	 * @param status
	 * @return return a list of {@link Student}
	 */
	List<Student> findByStatus(String status);

	/**
	 * 
	 * @param lastLogin
	 * @return a list of {@link Student}
	 */
	List<Student> findByLastLogin(LocalDateTime lastLogin);

	/**
	 * 
	 * @param username
	 * @return a {@link Student}
	 */
	List<Student> findByUsername(String username);

	@Query(value = "SELECT u.* FROM Student u WHERE u.username=:usernameOrEmail Or u.email=:usernameOrEmail", nativeQuery = true)
	Student findByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);

	/**
	 * 
	 * @param lastLogin
	 * @return a list of {@link Student}
	 */
	//List<Student> findByType(StudentType type);

	
	/**
	 * 
	 * @param lastName
	 * @return a list of {@link Student}
	 */
	List<Student> findByMiddelName(String lastName);

	/**
	 * 
	 * @param firstname
	 * @param lastname
	 * @return a list of {@link Student}
	 */
	public List<Student> findByFirstNameAndLastName(String firstname, String lastname);

	/**
	 * 
	 * @param firstname
	 * @param lastname
	 * @param status
	 * @return a list of {@link Student}
	 */
	public List<Student> findByFirstNameAndLastNameAndStatus(String firstname, String lastname, String status);

	/**
	 * 
	 * @param lastName
	 * @return a list of {@link Student}
	 */
	public List<Student> findByLastNameContainingIgnoreCase(String lastName);

	/**
	 * 
	 * @param firstname
	 * @return a list of {@link Student}
	 */
	public List<Student> findByFirstNameContainingIgnoreCase(String firstname);

	/**
	 * @return
	 */
	@Query(value = "SELECT u.* FROM Student u JOIN Students_roles ur ON u.id = ur.Student_id WHERE ur.role_id=:role_id ", nativeQuery = true)
	List<Student> findStudentsByRoleName(@Param("role_id") long role_id);

	/**
	 * 
	 * @param string
	 * @return @return a list of {@link Student}
	 */
	List<Student> findByFirstNameContainsOrderByFirstNameAsc(String string);

	/**
	 * 
	 * @param fistName
	 * @return @return a list of {@link Student}
	 */
	List<Student> findByFirstNameContainsOrderByFirstNameDesc(String fistName);

	/**
	 * 
	 * @param string
	 * @return @return a list of {@link Student}
	 */
	List<Student> findByLastNameContainsOrderByLastNameAsc(String string);

	/**
	 * 
	 * @param string
	 * @return @return a list of {@link Student}
	 */
	List<Student> findByLastNameContainsOrderByLastNameDesc(String string);

	public List<Student> findByFirstNameContaining(String firstname, Sort sort);

	public List<Student> findByFirstNameContains(String firstname, Sort sort);

	public List<Student> findByFirstNameContains(String firstname, Pageable pageable);

	public List<Student> findByFirstNameOrderByFirstNameAsc(String firstname);

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
	 * @param firstname
	 * @param lastname
	 * @param loginname
	 * @return a list of {@link Student}
	 */
	List<Student> findByFirstNameAndLastNameAndUsername(String firstname, String lastname, String loginname);

	/**
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @return a list of {@link Student}
	 */
	List<Student> findByFirstNameAndLastNameAndEmail(String firstname, String lastname, String email);

	/**
	 * @param Student_id
	 * @param role_id
	 */
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO Student_role(Student_id, role_id) VALUES(:Student_id, :role_id)", nativeQuery = true)
	void assignRoleToStudent(@Param("Student_id") Long Student_id, @Param("role_id") Long role_id);

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

//	@Query(value = "FROM Student WHERE UPPER(firstName) LIKE %?#{[0].toUpperCase()}%")
//	public List<Student> findByFirstNameContainingIgnoreCase(String firstname);
//
//	 @Query(value = "FROM Student WHERE UPPER(lastName) LIKE %?#{[0].toUpperCase()}%")
//	public List<Student> findByLastNameContainingIgnoreCase(String lastName);

}
