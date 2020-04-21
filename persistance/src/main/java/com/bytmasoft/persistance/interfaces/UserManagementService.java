package com.bytmasoft.persistance.interfaces;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.bytmasoft.common.interfaces.IOperations;
import com.bytmasoft.domain.enums.UserType;
import com.bytmasoft.domain.models.Privilege;
import com.bytmasoft.domain.models.Role;
import com.bytmasoft.domain.models.School;
import com.bytmasoft.domain.models.User;

/**
 * 
 * @author Mahamat Abakar Date 08.10.2019
 */
public interface UserManagementService extends IOperations<User> {

	/**
	 * 
	 * @param email
	 * @return
	 */
	List<User> findByEmail(String email);

	/**
	 * @param lastName
	 */
	List<User> findByLastName(String lastName);

	/**
	 * 
	 * @param fistName
	 */
	List<User> findByFirstName(String fistName);

	/**
	 * 
	 * @param lastName
	 */
	List<User> findByMiddelName(String lastName);

	/**
	 * 
	 * @param firstname
	 * @param lastname
	 * @return
	 */
	List<User> findByFirstNameAndLastName(String firstname, String lastname);

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
	 * @param loginname
	 * @return a {@link User}
	 */
	List<User> findByLoginname(String loginname);

	/**
	 * 
	 * @param lastLogin
	 * @return a list of {@link User}
	 */
	List<User> findByType(UserType type);

	/**
	 * 
	 * @param rightsName
	 */
	void assignRoleToUser(Long user_id, Long right_id);

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
	String generateLoginname(User user);

	/**
	 * 
	 * @param groupName
	 * @return List of User
	 */
	List<User> findUsersByRoleName(String rolename);

	List<User> findUserByFirstnameSorted(String firstname, String sortBy, String sortOrder);

	List<User> findUserByFirstnamePaged(String firstname, int page, int size);

	public List<User> findByFirstNameContainingIgnoreCase(String firstname);

	public List<User> findByLastNameContainingIgnoreCase(String lastname);

	public List<User> findAllSortedBy(String sortOrder, String... properties);

	/**
	 * @param age
	 * @return a list of {@link User}
	 */
	List<User> findUsersByAgeMoreThan(Long age);

	/**
	 * @param age
	 * @return a list of {@link User}
	 */
	List<User> findUsersByAgeLessThan(Long age);

	/**
	 * 
	 * @param requestParams
	 * @return a list of {@link User}
	 */
	List<User> findByRequestParams(Map<String, String> requestParams);

	/**
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @return
	 */
	List<User> findByFirstNameAndLastNameAndEmail(String firstname, String lastname, String email);

	/**
	 * @param firstname
	 * @param lastname
	 * @param loginname
	 * @return
	 */
	List<User> findByFirstNameAndLastNameAndLoginname(String firstname, String lastname, String loginname);

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
	boolean checkIfPasswordValid(User user, String oldPassword);

	List<School> findSchoolsByUserId(Long user_id);

	List<Role> findRolesByUserId(Long user_id);

	List<Privilege> findPrivilegesByUserId(Long user_id);

	/**
	 * @param user_id
	 * @param role_id
	 * @return
	 */
	Role findRoleByUserIdAndRoleId(Long user_id, Long role_id);

	/**
	 * 
	 * @param user_id
	 */
	void remerkByUserIdForDelete(Long user_id);

	/**
	 * 
	 * @param status
	 */
	void remerkUsersWithStatusForDelete(String status);

	/**
	 * 
	 * @param school_Id
	 */
	void remerkUsersWithSchoolIdForDelete(Long school_Id);

	/**
	 * 
	 * @param name
	 */
	void remerkUsersWithSchoolNameForDelete(String name);

	/**
	 * @param parent_id
	 * @return
	 */
	User findParentById(Long parent_id);

	/**
	 * @return
	 */
	List<User> findParents();

	/**
	 * @param teacher_id
	 * @return
	 */
	User findTeacherById(Long teacher_id);

	/**
	 * @return
	 */
	List<User> findTeachers();

	/**
	 * @param student_id
	 * @return
	 */
	User findStudentById(Long student_id);

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

}
