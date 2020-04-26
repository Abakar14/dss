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

import com.bytmasoft.domain.enums.UserType;
import com.bytmasoft.domain.models.School;
import com.bytmasoft.domain.models.Teacher;
import com.bytmasoft.persistance.interfaces.UmBaseRepository;

/**
 * 
 * 
 * @author Mahamat Abakar Date 10.10.2019
 */
@Transactional(readOnly = true)
@Repository
public interface TeacherRepository extends UmBaseRepository<Teacher, Long> {

	/**
	 * 
	 * @param email
	 * @return
	 */
//	@Nullable
	List<Teacher> findByEmail(String email);

	/**
	 * 
	 * @param lastName
	 * @return a list of {@link Teacher}
	 */
	List<Teacher> findByLastName(String lastName);

	/**
	 * 
	 * @param fistName
	 * @return @return a list of {@link Teacher}
	 */
	List<Teacher> findByFirstName(String fistName);

	List<Teacher> findBySchool(School school);

	/**
	 * 
	 * @param birthday
	 * @return a list of {@link Teacher}
	 */
	List<Teacher> findByBirthday(LocalDate birthday);

	/**
	 * 
	 * @param status
	 * @return return a list of {@link Teacher}
	 */
	List<Teacher> findByStatus(String status);

	/**
	 * 
	 * @param lastLogin
	 * @return a list of {@link Teacher}
	 */
	List<Teacher> findByLastLogin(LocalDateTime lastLogin);

	/**
	 * 
	 * @param username
	 * @return a {@link Teacher}
	 */
	List<Teacher> findByUsername(String username);

	@Query(value = "SELECT u.* FROM Teacher u WHERE u.username=:usernameOrEmail Or u.email=:usernameOrEmail", nativeQuery = true)
	Teacher findByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);

	/**
	 * 
	 * @param lastLogin
	 * @return a list of {@link Teacher}
	 */
	List<Teacher> findByType(UserType type);

	
	/**
	 * 
	 * @param lastName
	 * @return a list of {@link Teacher}
	 */
	List<Teacher> findByMiddelName(String lastName);

	/**
	 * 
	 * @param firstname
	 * @param lastname
	 * @return a list of {@link Teacher}
	 */
	public List<Teacher> findByFirstNameAndLastName(String firstname, String lastname);

	/**
	 * 
	 * @param firstname
	 * @param lastname
	 * @param status
	 * @return a list of {@link Teacher}
	 */
	public List<Teacher> findByFirstNameAndLastNameAndStatus(String firstname, String lastname, String status);

	/**
	 * 
	 * @param lastName
	 * @return a list of {@link Teacher}
	 */
	public List<Teacher> findByLastNameContainingIgnoreCase(String lastName);

	/**
	 * 
	 * @param firstname
	 * @return a list of {@link Teacher}
	 */
	public List<Teacher> findByFirstNameContainingIgnoreCase(String firstname);

	/**
	 * @return
	 */
	@Query(value = "SELECT t.* FROM Teacher t JOIN teacher_role tr ON t.id = tr.teacher_id WHERE tr.role_id=:role_id ", nativeQuery = true)
	List<Teacher> findTeachersByRoleId(@Param("role_id") long role_id);

	

	/**
	 * 
	 * @param string
	 * @return @return a list of {@link Teacher}
	 */
	List<Teacher> findByFirstNameContainsOrderByFirstNameAsc(String string);

	/**
	 * 
	 * @param fistName
	 * @return @return a list of {@link Teacher}
	 */
	List<Teacher> findByFirstNameContainsOrderByFirstNameDesc(String fistName);

	/**
	 * 
	 * @param string
	 * @return @return a list of {@link Teacher}
	 */
	List<Teacher> findByLastNameContainsOrderByLastNameAsc(String string);

	/**
	 * 
	 * @param string
	 * @return @return a list of {@link Teacher}
	 */
	List<Teacher> findByLastNameContainsOrderByLastNameDesc(String string);

	public List<Teacher> findByFirstNameContaining(String firstname, Sort sort);

	public List<Teacher> findByFirstNameContains(String firstname, Sort sort);

	public List<Teacher> findByFirstNameContains(String firstname, Pageable pageable);

	public List<Teacher> findFirst5ByFirstNameOrderByFirstNameAsc(String firstname);

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
	 * @param firstname
	 * @param lastname
	 * @param loginname
	 * @return a list of {@link Teacher}
	 */
	List<Teacher> findByFirstNameAndLastNameAndUsername(String firstname, String lastname, String loginname);

	/**
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @return a list of {@link Teacher}
	 */
	List<Teacher> findByFirstNameAndLastNameAndEmail(String firstname, String lastname, String email);

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
	

//	// @Query(value = "FROM Teacher WHERE UPPER(lastName) LIKE
//	// %?#{[0].toUpperCase()}%")
////	public List<Teacher> findByLastNameContainingIgnoreCase(String lastName);

}
