package com.bytmasoft.persistance.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bytmasoft.domain.enums.UserType;
import com.bytmasoft.domain.models.School;
import com.bytmasoft.domain.models.User;
import com.bytmasoft.persistance.interfaces.UmBaseRepository;

/**
 * 
 * 
 * @author Mahamat Abakar Date 10.10.2019
 */
@Transactional(readOnly = true)
@Repository
public interface UserRepository extends UmBaseRepository<User, Long> {

	/**
	 * 
	 * @param email
	 * @return
	 */
	@Nullable
	List<User> findByEmail(String email);

	/**
	 * 
	 * @param lastName
	 * @return a list of {@link User}
	 */
	List<User> findByLastName(String lastName);

	/**
	 * 
	 * @param fistName
	 * @return @return a list of {@link User}
	 */
	List<User> findByFirstName(String fistName);

	List<User> findBySchool(School school);

	/**
	 * 
	 * @param birthday
	 * @return a list of {@link User}
	 */
	List<User> findByBirthday(LocalDate birthday);

	/**
	 * 
	 * @param status
	 * @return return a list of {@link User}
	 */
	List<User> findByStatus(String status);

	/**
	 * 
	 * @param lastLogin
	 * @return a list of {@link User}
	 */
	List<User> findByLastLogin(LocalDateTime lastLogin);

	/**
	 * 
	 * @param username
	 * @return a {@link User}
	 */
	List<User> findByUsername(String username);

	@Query(value = "SELECT u.* FROM user u WHERE u.username=:usernameOrEmail Or u.email=:usernameOrEmail", nativeQuery = true)
	User findByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);

	/**
	 * 
	 * @param lastLogin
	 * @return a list of {@link User}
	 */
	List<User> findByType(UserType type);

//	/**
//	 * 
//	 * @param lastLogin
//	 * @return a list of {@link User}
//	 */
//	List<User> findByCountryName(String countryName);

	/**
	 * 
	 * @param lastName
	 * @return a list of {@link User}
	 */
	List<User> findByMiddelName(String lastName);

	/**
	 * 
	 * @param firstname
	 * @param lastname
	 * @return a list of {@link User}
	 */
	public List<User> findByFirstNameAndLastName(String firstname, String lastname);

	/**
	 * 
	 * @param firstname
	 * @param lastname
	 * @param status
	 * @return a list of {@link User}
	 */
	public List<User> findByFirstNameAndLastNameAndStatus(String firstname, String lastname, String status);

	/**
	 * 
	 * @param lastName
	 * @return a list of {@link User}
	 */
	public List<User> findByLastNameContainingIgnoreCase(String lastName);

	/**
	 * 
	 * @param firstname
	 * @return a list of {@link User}
	 */
	public List<User> findByFirstNameContainingIgnoreCase(String firstname);

	/**
	 * @return
	 */
	@Query(value = "SELECT u.* FROM user u JOIN users_roles ur ON u.id = ur.user_id WHERE ur.role_id=:role_id ", nativeQuery = true)
	List<User> findUsersByRoleName(@Param("role_id") long role_id);

	/**
	 * 
	 * @param string
	 * @return @return a list of {@link User}
	 */
	List<User> findByFirstNameContainsOrderByFirstNameAsc(String string);

	/**
	 * 
	 * @param fistName
	 * @return @return a list of {@link User}
	 */
	List<User> findByFirstNameContainsOrderByFirstNameDesc(String fistName);

	/**
	 * 
	 * @param string
	 * @return @return a list of {@link User}
	 */
	List<User> findByLastNameContainsOrderByLastNameAsc(String string);

	/**
	 * 
	 * @param string
	 * @return @return a list of {@link User}
	 */
	List<User> findByLastNameContainsOrderByLastNameDesc(String string);

	public List<User> findByFirstNameContaining(String firstname, Sort sort);

	public List<User> findByFirstNameContains(String firstname, Sort sort);

	public List<User> findByFirstNameContains(String firstname, Pageable pageable);

	public List<User> findFirst5ByFirstNameOrderByFirstNameAsc(String firstname);

	@Query(value = "FROM User WHERE firstName = ?1 ORDER BY lastName ASC")
	public List<User> findByFirstNameOrderByLastName(String firstname);

	@Query(value = "From User")
	public List<User> findAllSortedBy(Sort sort);

	@Query(value = "SELECT COUNT(u) FROM User u WHERE u.status ='A'")
	long countActiveResources();

	@Query(value = "SELECT COUNT(u) FROM User u WHERE u.status ='I'")
	long countAllInActiveUsers();

	@Modifying
	@Transactional
	@Query(value = "UPDATE User u set u.status ='A'", nativeQuery = true)
	void activateAll();

	@Modifying
	@Transactional
	@Query(value = "UPDATE User u set u.status ='I'", nativeQuery = true)
	void deactivateAll();

	@Modifying
	@Transactional
	@Query(value = "delete from User u where u.status = 'I'", nativeQuery = true)
	public void deleteInActiveUsers();

	/**
	 * @param firstname
	 * @param lastname
	 * @param loginname
	 * @return a list of {@link User}
	 */
	List<User> findByFirstNameAndLastNameAndUsername(String firstname, String lastname, String loginname);

	/**
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @return a list of {@link User}
	 */
	List<User> findByFirstNameAndLastNameAndEmail(String firstname, String lastname, String email);

	/**
	 * @param user_id
	 * @param role_id
	 */
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO user_role(user_id, role_id) VALUES(:user_id, :role_id)", nativeQuery = true)
	void assignRoleToUser(@Param("user_id") Long user_id, @Param("role_id") Long role_id);

	/**
	 * @param user_id
	 * @param address_id
	 */
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO user_address(user_id, address_id) VALUES(:user_id, :address_id)", nativeQuery = true)
	void assignAddressToUser(@Param("user_id") Long user_id, @Param("address_id") Long address_id);

	/**
	 * @param user_id
	 */
	@Transactional
	@Modifying
	@Query(value = "update user u set u.deletestatus = 1 where u.id=:user_id)", nativeQuery = true)
	void remerkByUserIdForDelete(@Param("user_id") Long user_id);

//	@Query(value = "FROM User WHERE UPPER(firstName) LIKE %?#{[0].toUpperCase()}%")
//	public List<User> findByFirstNameContainingIgnoreCase(String firstname);

	// @Query(value = "FROM User WHERE UPPER(lastName) LIKE
	// %?#{[0].toUpperCase()}%")
//	public List<User> findByLastNameContainingIgnoreCase(String lastName);

}
