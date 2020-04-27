package com.bytmasoft.persistance.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bytmasoft.domain.enums.SchoolType;
import com.bytmasoft.domain.models.School;
import com.bytmasoft.persistance.interfaces.UmBaseRepository;;

/**
 * 
 * 
 * @author Mahamat Abakar Date 01.12.2019
 */
@Repository
@Transactional(readOnly = true)
public interface SchoolRepository extends UmBaseRepository<School, Long> {

	/**
	 * @param name {@link String}
	 * @return list of {@link School}
	 */
	School findByName(String name);

	/**
	 * 
	 * @param status {@link String}
	 * @return list of {@link School}
	 */
	List<School> findByStatus(String status);

	/**
	 * 
	 * @param type {@link SchoolType}
	 * @return list of {@link School}
	 */
	List<School> findByType(SchoolType type);

	@Modifying
	@Transactional
	@Query(value = "UPDATE school s set s.status ='I'", nativeQuery = true)
	void deactivateAll();

	@Modifying
	@Transactional
	@Query(value = "UPDATE school s set s.status ='A'", nativeQuery = true)
	void activateAll();

//	@Modifying
//	@Transactional
//	@Query(value = "delete from school s where s.status = 'I'")
//	void deleteInActiveSchool();

	/**
	 * @param user_id
	 * @return
	 */
	@Query(value = "SELECT s.* FROM school s JOIN user u ON s.id = u.school_id WHERE u.id=:user_id ", nativeQuery = true)
	List<School> findSchoolsByUserId(@Param("user_id") long user_id);

}
