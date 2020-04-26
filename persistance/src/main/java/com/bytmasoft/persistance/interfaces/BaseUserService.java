package com.bytmasoft.persistance.interfaces;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.bytmasoft.common.interfaces.IOperations;
import com.bytmasoft.domain.enums.UserType;
import com.bytmasoft.domain.model.interfaces.BaseEntity;
import com.bytmasoft.domain.models.BaseUser;


public interface BaseUserService  <T extends BaseEntity> extends IOperations<T> {

	/**
	 * 
	 * @param email
	 * @return
	 */
	List<T> findByEmail(String email);

	/**
	 * @param lastName
	 */
	List<T> findByLastName(String lastName);

	/**
	 * 
	 * @param fistName
	 */
	List<T> findByFirstName(String fistName);

	/**
	 * 
	 * @param lastName
	 */
	List<T> findByMiddelName(String lastName);

	/**
	 * 
	 * @param firstname
	 * @param lastname
	 * @return
	 */
	List<T> findByFirstNameAndLastName(String firstname, String lastname);

	/**
	 * 
	 * @param birthday
	 * @return a list of {@link T}
	 */
	List<T> findByBirthday(LocalDate birthday);

	/**
	 * 
	 * @param lastLogin
	 * @return a list of {@link T}
	 */
	List<T> findByLastLogin(LocalDateTime lastLogin);

	/**
	 * 
	 * @param loginname
	 * @return a {@link T}
	 */
	List<T> findByUsername(String username);

	/**
	 * 
	 * @param lastLogin
	 * @return a list of {@link T}
	 */
	List<T> findByType(UserType type);

	/**
	 * 
	 * @param rightsName
	 */
	void assignRoleToUser(Long user_id, Long role_id);

	/**
	 * 
	 * @param rightsName
	 */
	void assignRoleToUsers(String role);

	/**
	 * @param roleId
	 */
	void assignRoleToUsers(Long roleId);

	/**
	 * 
	 * @param user
	 * @return
	 */
	String generateLoginname(T user);

	/**
	 * 
	 * @param groupName
	 * @return List of T
	 */
	List<T> findUsersByRoleName(String rolename);

	List<T> findUserByFirstnameSorted(String firstname, String sortBy, String sortOrder);

	List<T> findUserByFirstnamePaged(String firstname, int page, int size);

	 List<T> findByFirstNameContainingIgnoreCase(String firstname);

	List<T> findByLastNameContainingIgnoreCase(String lastname);

	 List<T> findAllSortedBy(String sortOrder, String... properties);

	/**
	 * @param age
	 * @return a list of {@link T}
	 */
	List<T> findUsersByAgeMoreThan(Long age);

	/**
	 * @param age
	 * @return a list of {@link T}
	 */
	List<T> findUsersByAgeLessThan(Long age);

	/**
	 * 
	 * @param requestParams
	 * @return a list of {@link T}
	 */
	List<T> findByRequestParams(Map<String, String> requestParams);

	/**
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @return
	 */
	List<T> findByFirstNameAndLastNameAndEmail(String firstname, String lastname, String email);

	/**
	 * @param firstname
	 * @param lastname
	 * @param loginname
	 * @return
	 */
	List<T> findByFirstNameAndLastNameAndLoginname(String firstname, String lastname, String loginname);

	/**
	 * @param user_id
	 * @param address_id
	 */
	void assignAddressToUser(Long user_id, Long address_id);

	/**
	 * @param user
	 * @param oldPassword
	 * @return
	 */
	boolean checkIfPasswordValid(T user, String oldPassword);


	List<T> findUsersByRoleId(Long role_id);

	List<T> findUsersByPrivilegeId(Long privilege_id);

	/**
	 * @param user_id
	 * @param role_id
	 * @return
	 */
	T findUserByUserIdAndRoleId(Long teacher_id, Long role_id);

		
	/**
	 * @param teacher_id
	 * @return
	 */
	T findUserById(Long teacher_id);

	/**
	 * @return
	 */
	List<T> findUsers();



	/**
	 * @param email
	 */
	Boolean sendEmailForChangingPassword(String email);

	/**
	 * @param id
	 * @param confirmPassword
	 * @return
	 */
	Boolean updatePasswordByUserId(Long id, String confirmPassword);

	void remerkUsersWithSchoolIdForDelete(Long school_Id);

	T setUpdateParams(T t);

	void update(T t);

	
}
