package com.bytmasoft.persistance.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bytmasoft.domain.enums.UserType;
import com.bytmasoft.domain.models.Student;
import com.bytmasoft.domain.models.Teacher;


/**
 * 
 * 
 * @author Mahamat Abakar Date 10.12.2019
 */
@NoRepositoryBean
public interface DSSUserBaseRepository<T, ID> extends PagingAndSortingRepository<T, ID> {
	
	/**
	 * 
	 * @param email
	 * @return
	 */	
	T findByEmailAddress(String email);

	/**
	 * 
	 * @param lastName
	 * @return a list of {@link Student}
	 */
	List<T> findByLastName(String lastName);
	

	/**
	 * 
	 * @param fistName
	 * @return @return a list of {@link Teacher}
	 */
	List<T> findByFirstName(String fistName);



	/**
	 * 
	 * @param birthday
	 * @return a list of {@link Teacher}
	 */
	List<T> findByBirthday(LocalDate birthday);

	/**
	 * 
	 * @param status
	 * @return return a list of {@link Teacher}
	 */
	List<T> findByStatus(String status);

	/**
	 * 
	 * @param lastLogin
	 * @return a list of {@link Teacher}
	 */
	List<T> findByLastLogin(LocalDateTime lastLogin);

	/**
	 * 
	 * @param username
	 * @return a {@link Teacher}
	 */
	T findByUsername(String username);
	
	/**
	 * 
	 * @param lastLogin
	 * @return a list of {@link Teacher}
	 */
	List<T> findByType(UserType type);
	/**
	 * 
	 * @param lastName
	 * @return a list of {@link Teacher}
	 */
	List<T> findByMiddelName(String lastName);

	/**
	 * 
	 * @param firstname
	 * @param lastname
	 * @return a list of {@link Teacher}
	 */
	public List<T> findByFirstNameAndLastName(String firstname, String lastname);

	
	/**
	 * 
	 * @param firstname
	 * @param lastname
	 * @param status
	 * @return a list of {@link Teacher}
	 */
	public List<T> findByFirstNameAndLastNameAndStatus(String firstname, String lastname, String status);

	/**
	 * 
	 * @param lastName
	 * @return a list of {@link Teacher}
	 */
	public List<T> findByLastNameContainingIgnoreCase(String lastName);

	/**
	 * 
	 * @param firstname
	 * @return a list of {@link Teacher}
	 */
	public List<T> findByFirstNameContainingIgnoreCase(String firstname);

	
	/**
	 * @param firstname
	 * @param lastname
	 * @param loginname
	 * @return a list of {@link Teacher}
	 */
	T findByFirstNameAndLastNameAndUsername(String firstname, String lastname, String loginname);


	/**
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @return a list of {@link Teacher}
	 */
	T findByFirstNameAndLastNameAndEmailAddress(String firstname, String lastname, String email);

	/**
	 * 
	 * @param string
	 * @return @return a list of {@link Teacher}
	 */
	List<T> findByFirstNameContainsOrderByFirstNameAsc(String string);

	/**
	 * 
	 * @param fistName
	 * @return @return a list of {@link Teacher}
	 */
	List<T> findByFirstNameContainsOrderByFirstNameDesc(String fistName);

	/**
	 * 
	 * @param string
	 * @return @return a list of {@link Teacher}
	 */
	List<T> findByLastNameContainsOrderByLastNameAsc(String string);

	/**
	 * 
	 * @param string
	 * @return @return a list of {@link Teacher}
	 */
	List<T> findByLastNameContainsOrderByLastNameDesc(String string);

	public List<T> findByFirstNameContaining(String firstname, Sort sort);

	public List<T> findByFirstNameContains(String firstname, Sort sort);

	public List<T> findByFirstNameContains(String firstname, Pageable pageable);

	public List<T> findFirst5ByFirstNameOrderByFirstNameAsc(String firstname);
	
	
	public List<T> findByFirstNameOrderByFirstNameAsc(String firstname);



}
