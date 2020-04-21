package com.bytmasoft.persistance.interfaces;

import java.util.List;
import java.util.Map;

import com.bytmasoft.common.interfaces.IOperations;
import com.bytmasoft.domain.models.Privilege;

public interface PrivilegeService extends IOperations<Privilege> {

	/**
	 * 
	 * @param privilege_id
	 * @param role_id
	 * @return
	 */
	List<Privilege> findPrivilegesByRoleId(Long role_id);

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
