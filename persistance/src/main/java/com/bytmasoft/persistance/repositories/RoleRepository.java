package com.bytmasoft.persistance.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bytmasoft.domain.enums.RoleType;
import com.bytmasoft.domain.models.Role;
import com.bytmasoft.persistance.interfaces.UmBaseRepository;

/**
 * 
 * 
 * @author Mahamat Abakar Date 10.11.2019
 */
@Repository
@Transactional(readOnly = true)
public interface RoleRepository extends UmBaseRepository<Role, Long> {

	/**
	 * 
	 * @param name
	 * @return a {@link Role}
	 */
	Role findByName(String name);

	/**
	 * 
	 * @param status
	 * @return a list of {@link Role}
	 */
	List<Role> findByStatus(String status);

	/**
	 * 
	 * @param type
	 * @return a list of {@link Role}
	 */
	List<Role> findByType(RoleType type);

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO ROLE_PRIVILEGE(role_id, privilege_id) values(:role_id, :privilege_id)", nativeQuery = true)
	public void addPrivilegeToRole(@Param("role_id") Long role_id, @Param("privilege_id") Long privilege_id);

	/**
	 * @return a number of Active countries
	 */
	@Query(value = "select count(r) from Role r where r.status = 'A'")
	long countActiveResources();

	/**
	 * @return a number of inactive countries
	 */
	@Query(value = "select count(r) from Role r where r.status = 'I'")
	long countAllInActiveRights();

	@Modifying
	@Transactional
	@Query(value = "UPDATE Role r set r.status ='A'", nativeQuery = true)
	void activateAll();

	@Modifying
	@Transactional
	@Query(value = "UPDATE Role r set r.status ='I'", nativeQuery = true)
	void deactivateAll();

	@Modifying
	@Transactional
	@Query(value = "delete from Role r where r.status = 'I'", nativeQuery = true)
	void deleteAllInActiveResources();

	/**
	 * @param user_id
	 * @return
	 */
	@Query(value = "select r.* from role r join user_role ur on r.id = ur.role_id where ur.user_id =:user_id", nativeQuery = true)
	List<Role> findRoleByUserId(Long user_id);

	/**
	 * @param user_id
	 * @param right_id
	 * @return
	 */
	@Query(value = "select r.* from role r join user_role ur on r.id = ur.role_id where ur.user_id =:user_id and r.id=:role_id", nativeQuery = true)
	Role findRoleByUserIdAndRoleId(Long user_id, Long role_id);

}
