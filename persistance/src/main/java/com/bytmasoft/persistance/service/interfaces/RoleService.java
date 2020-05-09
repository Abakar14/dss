package com.bytmasoft.persistance.service.interfaces;

import java.util.List;
import java.util.Set;

import com.bytmasoft.common.interfaces.IOperations;
import com.bytmasoft.domain.enums.RoleType;
import com.bytmasoft.domain.models.Role;

/**
 * 
 * @author Mahamat Abakar Date 08.10.2019
 */
public interface RoleService extends IOperations<Role> {

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
	List<Role> findByType(String type);

	/**
	 * @param id
	 * @return
	 */
	Set<Role> findRolesByUserId(Long id);

	/**
	 * 
	 * @param user_id
	 * @param role_id
	 * @return
	 */
	Role findRoleByUserIdAndRoleId(Long user_id, Long role_id);

	/**
	 * @param role_id
	 * @param privilege_id
	 */
	void addPrivilegeToRole(Long role_id, Long privilege_id);

	/**
	 * 
	 * @param roleId
	 * @param type
	 */
	void addTypeToRole(Long roleId, RoleType type);

}
