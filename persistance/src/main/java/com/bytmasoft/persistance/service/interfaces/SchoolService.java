package com.bytmasoft.persistance.service.interfaces;

import java.util.List;

import com.bytmasoft.common.interfaces.IOperations;
import com.bytmasoft.domain.enums.SchoolType;
import com.bytmasoft.domain.models.Address;
import com.bytmasoft.domain.models.User;
import com.bytmasoft.domain.models.School;

public interface SchoolService extends IOperations<School> {

	/**
	 * 
	 * @param name
	 * @return List of {@link School}
	 */
	public School findByName(String name);

	/**
	 * 
	 * @param type
	 * @return List of {@link School}
	 */
	public List<School> findByType(SchoolType type);

	/**
	 * 
	 * @param school_id
	 * @return a list of {@link User}
	 */
	public List<User> findUsersBySchoolId(Long school_id);

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
