package com.bytmasoft.persistance.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bytmasoft.domain.models.Privilege;
import com.bytmasoft.persistance.interfaces.UmBaseRepository;;

/**
 * 
 * 
 * @author Mahamat Abakar Date 11.11.2019
 */
@Transactional
@Repository
public interface PrivilegeRepository extends UmBaseRepository<Privilege, Long> {

	/**
	 * 
	 * @param name
	 * @return a list of {@link Privilege}
	 */
	Privilege findByName(String name);

	/**
	 * 
	 * @param status
	 * @return a list of {@link Privilege}
	 */
	List<Privilege> findByStatus(String status);

	/**
	 * 
	 * @return a list of {@link Privilege}
	 */
	@Query(value = "FROM Privilege p WHERE p.status = 'A'")
	public List<Privilege> findActivePrivileges();

	/**
	 * @return a list of {@link Privilege}
	 */
	@Query(value = "FROM Privilege p WHERE p.status = 'I'")
	List<Privilege> findInActivePrivileges();

	@Query(value = "select p.* from privilege p join role_privilege rp on p.id = rp.privilege_id where rp.role_id=:role_id", nativeQuery = true)
	List<Privilege> findPrivilegeByRoleId(@Param("role_id") long role_id);

//	@Query(value = "select p.* from privilege p join role_privilege rp on p.id = rp.privilege_id where rp.role_id=:role_id",	nativeQuery = true)
//	List<Privilege> findPrivilegeByPrivilegeIdAndRoleId(@Param("privilege_id") long privilege_id, @Param("role_id") long role_id);

	/**
	 * @return number of active Groups
	 */
	@Query(value = "SELECT COUNT(p) FROM Privilege p WHERE p.status ='A'")
	long countActivePrivileges();

	/**
	 * @return number of inactive Groups
	 */
	@Query(value = "SELECT COUNT(p) FROM Privilege p WHERE p.status ='I'")
	long countInActivePrivilege();

	@Modifying
	@Transactional
	@Query(value = "UPDATE privilege p SET p.status ='A'", nativeQuery = true)
	void activateAll();

	@Modifying
	@Transactional
	@Query(value = "UPDATE privilege p SET p.status ='I'", nativeQuery = true)
	void deactivateAll();

	@Modifying
	@Transactional
	@Query(value = "delete from privilege p where p.status = 'I'", nativeQuery = true)
	void deleteInActiveUsers();

}
