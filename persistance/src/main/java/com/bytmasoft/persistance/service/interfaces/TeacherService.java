package com.bytmasoft.persistance.service.interfaces;

import com.bytmasoft.domain.models.Teacher;
/**
 * 
 * @author Mahamat Abakar Date 08.10.2019
 */
public interface TeacherService extends BaseUserService<Teacher> {

	Teacher findByUsernameOrEmail(String usernameOrEmail);
	
}
