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
import com.bytmasoft.domain.models.Manager;
import com.bytmasoft.persistance.interfaces.UmBaseRepository;

/**
 * 
 * 
 * @author Mahamat Abakar Date 10.10.2019
 */
@Transactional(readOnly = true)
@Repository
public interface ManagerRepository extends UmBaseRepository<Manager, Long> {

	/**
	 * 
	 * @param email
	 * @return
	 */
	List<Manager> findByEmail(String email);

	/**
	 * 
	 * @param lastName
	 * @return a list of {@link Manager}
	 */
	List<Manager> findByLastName(String lastName);

	/**
	 * 
	 * @param fistName
	 * @return @return a list of {@link Manager}
	 */
	List<Manager> findByFirstName(String fistName);

//	List<Manager> findBySchool(School school);

	/**
	 * 
	 * @param birthday
	 * @return a list of {@link Manager}
	 */
	List<Manager> findByBirthday(LocalDate birthday);

	/**
	 * 
	 * @param status
	 * @return return a list of {@link Manager}
	 */
	List<Manager> findByStatus(String status);

	/**
	 * 
	 * @param lastLogin
	 * @return a list of {@link Manager}
	 */
	List<Manager> findByLastLogin(LocalDateTime lastLogin);

	/**
	 * 
	 * @param username
	 * @return a {@link Manager}
	 */
	List<Manager> findByUsername(String username);

	@Query(value = "SELECT u.* FROM user u WHERE u.username=:usernameOrEmail Or u.email=:usernameOrEmail", nativeQuery = true)
	Manager findByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);

	/**
	 * 
	 * @param lastLogin
	 * @return a list of {@link Manager}
	 */
	List<Manager> findByType(UserType type);

	/**
	 * 
	 * @param lastLogin
//	 * @return a list of {@link Manager}
//	 */
//	List<Manager> findByCountryName(String countryName);

	/**
	 * 
	 * @param lastName
	 * @return a list of {@link Manager}
	 */
	List<Manager> findByMiddelName(String lastName);

	/**
	 * 
	 * @param firstname
	 * @param lastname
	 * @return a list of {@link Manager}
	 */
	public List<Manager> findByFirstNameAndLastName(String firstname, String lastname);

	/**
	 * 
	 * @param firstname
	 * @param lastname
	 * @param status
	 * @return a list of {@link Manager}
	 */
	public List<Manager> findByFirstNameAndLastNameAndStatus(String firstname, String lastname, String status);

	/**
	 * 
	 * @param lastName
	 * @return a list of {@link Manager}
	 */
	public List<Manager> findByLastNameContainingIgnoreCase(String lastName);

	/**
	 * 
	 * @param firstname
	 * @return a list of {@link Manager}
	 */
	public List<Manager> findByFirstNameContainingIgnoreCase(String firstname);

	/**
	 * @return
	 */
	@Query(value = "SELECT m.* FROM Manager m JOIN manager_role mr ON m.id = mr.manager_id WHERE mr.role_id=:role_id ", nativeQuery = true)
	List<Manager> findUsersByRoleName(@Param("role_id") long role_id);

	/**
	 * 
	 * @param string
	 * @return @return a list of {@link Manager}
	 */
	List<Manager> findByFirstNameContainsOrderByFirstNameAsc(String string);

	/**
	 * 
	 * @param fistName
	 * @return @return a list of {@link Manager}
	 */
	List<Manager> findByFirstNameContainsOrderByFirstNameDesc(String fistName);

	/**
	 * 
	 * @param string
	 * @return @return a list of {@link Manager}
	 */
	List<Manager> findByLastNameContainsOrderByLastNameAsc(String string);

	/**
	 * 
	 * @param string
	 * @return @return a list of {@link Manager}
	 */
	List<Manager> findByLastNameContainsOrderByLastNameDesc(String string);

	public List<Manager> findByFirstNameContaining(String firstname, Sort sort);

	public List<Manager> findByFirstNameContains(String firstname, Sort sort);

	public List<Manager> findByFirstNameContains(String firstname, Pageable pageable);

	public List<Manager> findFirst5ByFirstNameOrderByFirstNameAsc(String firstname);

	@Query(value = "FROM Manager WHERE firstName = ?1 ORDER BY lastName ASC")
	public List<Manager> findByFirstNameOrderByLastName(String firstname);

	@Query(value = "From Manager")
	public List<Manager> findAllSortedBy(Sort sort);

	@Query(value = "SELECT COUNT(m) FROM Manager m WHERE m.status ='A'")
	long countActiveResources();

	@Query(value = "SELECT COUNT(m) FROM Manager m WHERE m.status ='I'")
	long countAllInActiveUsers();

	@Modifying
	@Transactional
	@Query(value = "UPDATE Manager m set m.status ='A'", nativeQuery = true)
	void activateAll();

	@Modifying
	@Transactional
	@Query(value = "UPDATE Manager m set m.status ='I'", nativeQuery = true)
	void deactivateAll();

	@Modifying
	@Transactional
	@Query(value = "delete from Manager m where m.status = 'I'", nativeQuery = true)
	public void deleteInActiveUsers();

	/**
	 * @param firstname
	 * @param lastname
	 * @param loginname
	 * @return a list of {@link Manager}
	 */
	List<Manager> findByFirstNameAndLastNameAndUsername(String firstname, String lastname, String loginname);

	/**
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @return a list of {@link Manager}
	 */
	List<Manager> findByFirstNameAndLastNameAndEmail(String firstname, String lastname, String email);

	/**
	 * @param user_id
	 * @param role_id
	 */
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO manager_role(manager_id, role_id) VALUES(:manager_id, :role_id)", nativeQuery = true)
	void assignRoleToUser(@Param("manager_id") Long manager_id, @Param("role_id") Long role_id);

	/**
	 * @param user_id
	 * @param address_id
	 */
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO manager_address(manager_id, address_id) VALUES(:manager_id, :address_id)", nativeQuery = true)
	void assignAddressToUser(@Param("manager_id") Long manager_id, @Param("address_id") Long address_id);

	/**
	 * @param user_id
	 */
	@Transactional
	@Modifying
	@Query(value = "update manager m set m.deletestatus = 1 where m.id=:manager_id)", nativeQuery = true)
	void remerkByUserIdForDelete(@Param("manager_id") Long manager_id);
//
////	@Query(value = "FROM Manager WHERE UPPER(firstName) LIKE %?#{[0].toUpperCase()}%")
////	public List<Manager> findByFirstNameContainingIgnoreCase(String firstname);
//
//	// @Query(value = "FROM Manager WHERE UPPER(lastName) LIKE
//	// %?#{[0].toUpperCase()}%")
////	public List<Manager> findByLastNameContainingIgnoreCase(String lastName);

}
