package com.bytmasoft.persistance.interfaces;

import java.util.List;

import com.bytmasoft.common.interfaces.IOperations;
import com.bytmasoft.domain.enums.SchoolType;
import com.bytmasoft.domain.models.Address;
import com.bytmasoft.domain.models.School;
import com.bytmasoft.domain.models.BaseUser;

public interface SchoolService extends IOperations<School> {

	/**
	 * 
	 * @param name
	 * @return List of {@link School}
	 */
	public List<School> findByName(String name);

	/**
	 * 
	 * @param type
	 * @return List of {@link School}
	 */
	public List<School> findByType(SchoolType type);

	/**
	 * 
	 * @param school_id
	 * @return a list of {@link BaseUser}
	 */
	public List<BaseUser> findUsersBySchoolId(Long school_id);

	/**
	 * 
	 * @param school_id
	 * @return a {@link Address}
	 */
	public Address findAddressBySchoolId(Long school_id);

	/**
	 * @param id
	 * @return
	 */
	public List<School> findSchoolsByUserId(Long id);

}
