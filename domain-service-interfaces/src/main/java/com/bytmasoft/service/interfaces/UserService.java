package com.bytmasoft.service.interfaces;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.bytmasoft.common.interfaces.IOperations;
import com.bytmasoft.domain.enums.UserType;
import com.bytmasoft.domain.models.User;

public interface UserService<T extends User> extends IOperations<T> {

	public T addProfilePicture(MultipartFile profilePic, Long user_id) throws IOException;

	public T findByEmail(String email);

	/**
	 * @param lastName
	 */
	public List<T> findByLastName(String lastName);

	/**
	 * 
	 * @param fistName
	 */
	public List<T> findByFirstName(String fistName);

	/**
	 * 
	 * @param lastName
	 */
	public List<T> findByMiddelName(String lastName);

	/**
	 * 
	 * @param firstname
	 * @param lastname
	 * @return
	 */
	public List<T> findByFirstNameAndLastName(String firstname, String lastname);

	/**
	 * 
	 * @param birthday
	 * @return a list of {@link T}
	 */
	public List<T> findByBirthday(LocalDate birthday);

	/**
	 * 
	 * @param lastLogin
	 * @return a list of {@link T}
	 */
	public List<T> findByLastLogin(LocalDateTime lastLogin);

	/**
	 * 
	 * @param loginname
	 * @return a {@link T}
	 */
	public T findByUsername(String username);

	/**
	 * 
	 * @param lastLogin
	 * @return a list of {@link T}
	 */
	public List<T> findByType(UserType type);

	/**
	 * 
	 * @param rightsName
	 */
	public void assignRoleToUser(Long user_id, Long role_id);

	/**
	 * 
	 * @param rightsName
	 */
	public void assignRoleToUsers(String role);

	/**
	 * This method assign a role witd role_id to all students
	 * 
	 * @param roleId
	 */
	public void assignRoleToUsers(Long roleId);

	/**
	 * 
	 * @param groupName
	 * @return List of T
	 */
	public List<T> findUsersByRoleName(String rolename);

	public List<T> findUserByFirstnameSorted(String firstname, String sortBy, String sortOrder);

	public List<T> findUserByFirstnamePaged(String firstname, int page, int size);

	public List<T> findByFirstNameContainingIgnoreCase(String firstname);

	public List<T> findByLastNameContainingIgnoreCase(String lastname);

	public List<T> findAllSortedBy(String sortOrder, String... properties);

	public List<T> findAllRemarkedForDeleteSortedBy(String sortOrder, String... properties);

	public List<T> findAllInActiveSortedBy(String sortOrder, String... properties);

	/**
	 * 
	 * @param requestParams
	 * @return a list of {@link T}
	 */
	public List<T> findByRequestParams(Map<String, String> requestParams);

	public T findOneByRequestParams(Map<String, String> requestParams);

	/**
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @return
	 */
	public List<T> findByFirstNameAndLastNameAndEmailAddress(String firstname, String lastname, String email);

	/**
	 * 
	 * @param name
	 * @return
	 */
	public List<T> findByFirstNameOrLastName(String firstname, String lastname);

	/**
	 * @param firstname
	 * @param lastname
	 * @param loginname
	 * @return user
	 */
	public T findByFirstNameAndLastNameAndLoginname(String firstname, String lastname, String loginname);

	/**
	 * @param user_id
	 * @param address_id
	 */
	public void assignAddressToUser(Long user_id, Long address_id);

	/**
	 * 
	 * @param id
	 * @param password
	 * @return true if given password is equal the exist one
	 */
	public boolean isPasswordValid(Long id, String password);

	public List<T> findUsersByRoleId(Long role_id);

	public List<T> findUsersByPrivilegeId(Long privilege_id);

	/**
	 * @param user_id
	 * @param role_id
	 * @return
	 */
	public T findUserByUserIdAndRoleId(Long teacher_id, Long role_id);

//	/**
//	 * @return
//	 */
//	public List<T> findUsers();

	/**
	 * send a user email with link to add password and confirm password
	 * 
	 * @param email
	 * @return true if email was sent
	 */
	public Boolean sendEmailForChangingPassword(String email);

	/**
	 * @param id
	 * @param confirmPassword
	 * @return
	 */
	public Boolean changePassword(Long id, String confirmPassword);

	public Boolean checkIfLoginnameIsExisit(String loginname);

	public Boolean checkIfEmailIsExisit(String email);

	/**
	 * 
	 * @param id
	 * @param password
	 * @param confirmPassword
	 * @return true if password is changed else false
	 */
	public Boolean resetPassword(Long id, String password, String confirmPassword);

	public void remarkUsersWithSchoolIdForDelete(Long school_Id);

	@Override
	public void update(T t);

	public T update(T t, Long id);

	/**
	 * @param age
	 * @return a list of {@link T}
	 */
	public List<T> findUsersByAgeLessThan(int age);

	public List<T> findUsersByAgeGreaterThan(int age);

	public List<T> findUsersByAgeBetween(int startAge, int endAge);

	public List<T> findUsersByAgeIn(Collection<Integer> ages);
}
