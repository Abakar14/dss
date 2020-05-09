package com.bytmasoft.persistance.service.interfaces;

import com.bytmasoft.domain.models.Student;

/**
 * 
 * @author Mahamat Abakar Date 08.10.2019
 */
public interface StudentService extends BaseUserService<Student> {

	Student findByUsernameOrEmail(String usernameOrEmail);
}
