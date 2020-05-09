package com.bytmasoft.persistance.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bytmasoft.domain.models.Manager;

/**
 * 
 * 
 * @author Mahamat Abakar Date 10.10.2019
 */
@Transactional(readOnly = true)
@Repository
public interface ManagerRepository extends DSSUserBaseRepository<Manager, Long> {

	
//	List<Manager> findBySchool(School school);

	

	@Query(value = "SELECT m.* FROM manager m WHERE m.username=:usernameOrEmail Or m.email=:usernameOrEmail", nativeQuery = true)
	Manager findByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);

	

	
	/**
	 * @return
	 */
	@Query(value = "SELECT m.* FROM Manager m JOIN manager_role mr ON m.id = mr.manager_id WHERE mr.role_id=:role_id ", nativeQuery = true)
	List<Manager> findUsersByRoleName(@Param("role_id") long role_id);
	
	
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

}
