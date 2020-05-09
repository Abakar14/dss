package com.bytmasoft.persistance.service.interfaces;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bytmasoft.common.interfaces.IOperations;
import com.bytmasoft.domain.models.Privilege;
import com.bytmasoft.domain.models.Role;

public interface PrivilegeService extends IOperations<Privilege> {

	/**
	 * 
	 * @param privilege_id
	 * @param role_id
	 * @return
	 */
	Set<Privilege> findPrivilegesByRoleId(Long role_id);
	
	/**
	 * @param id
	 * @return
	 */
	Set<Privilege> findPrivilegesByUserId(Long id);


	/**
	 * @param name
	 * @return
	 */
	Privilege findByName(String name);

	/**
	 * @param requestParams
	 * @return
	 */
	Privilege findByRequestParams(Map<String, String> requestParams);

}
