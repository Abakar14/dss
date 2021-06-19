package com.bytmasoft.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bytmasoft.domain.enums.UserType;

/**
 * 
 * 
 * @author Mahamat Abakar Date 10.12.2019
 */
@NoRepositoryBean
public interface UserRepository<T, ID> extends PagingAndSortingRepository<T, ID> {

	/**
	 * 
	 * @param email
	 * @return
	 */
	T findByEmail(String email);

	/**
	 * 
	 * @param lastName
	 * @return
	 */
	List<T> findByLastName(String lastName);

	/**
	 * 
	 * @param fistName
	 * @return
	 */
	List<T> findByFirstName(String fistName);

	/**
	 * 
	 * @param birthday
	 * @return
	 */
	List<T> findByBirthday(LocalDate birthday);

	/**
	 * 
	 * @param lastLogin
	 * @return
	 */
	List<T> findByLastLogin(LocalDateTime lastLogin);

	/**
	 * 
	 * @param loginname
	 * @return
	 */
	List<T> findByLoginname(String loginname);

	/**
	 * 
	 * @param lastLogin
	 * @return
	 */
	List<T> findByUserType(UserType userType);

	/**
	 * 
	 * @param lastName
	 * @return
	 */
	List<T> findByMiddelName(String lastName);

	/**
	 * 
	 * @param firstname
	 * @param lastname
	 * @return
	 */
	public List<T> findByFirstNameAndLastName(String firstname, String lastname);

	/**
	 * 
	 * @param lastName
	 * @return
	 */
	public List<T> findByLastNameContainingIgnoreCase(String lastName);

	/**
	 * 
	 * @param firstname
	 * @return
	 */
	public List<T> findByFirstNameContainingIgnoreCase(String firstname);

	/**
	 * @param firstname
	 * @param lastname
	 * @param loginname
	 * @return
	 */
	T findByFirstNameAndLastNameAndLoginname(String firstname, String lastname, String loginname);

	/**
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @return
	 */
	List<T> findByFirstNameAndLastNameAndEmail(String firstname, String lastname, String email);

	/**
	 * 
	 * @param string
	 * @return
	 */
	List<T> findByFirstNameContainsOrderByFirstNameAsc(String string);

	/**
	 * 
	 * @param fistName
	 * @return
	 */
	List<T> findByFirstNameContainsOrderByFirstNameDesc(String fistName);

	/**
	 * 
	 * @param string
	 * @return
	 */
	List<T> findByLastNameContainsOrderByLastNameAsc(String string);

	/**
	 * 
	 * @param string
	 * @return
	 */
	List<T> findByLastNameContainsOrderByLastNameDesc(String string);

	public List<T> findByFirstNameContains(String firstname, Sort sort);

	public List<T> findByFirstNameContains(String firstname, Pageable pageable);

	public List<T> findFirst5ByFirstNameOrderByFirstNameAsc(String firstname);

	public List<T> findByFirstNameOrderByFirstNameAsc(String firstname);

	public List<T> findByBirthdayAfter(LocalDate date);

	public List<T> findByBirthdayBefore(LocalDate date);

	public List<T> findByFirstNameOrBirthdayBefore(String firstname, LocalDate birthday);

	public List<T> findByFirstNameOrBirthdayAndActive(String firstname, LocalDate birthday, Boolean active);

	public List<T> findByActiveTrue();

	public List<T> findByActiveFalse();

}
