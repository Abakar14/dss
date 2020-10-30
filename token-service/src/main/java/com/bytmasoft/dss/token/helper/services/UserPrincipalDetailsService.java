package com.bytmasoft.dss.token.helper.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bytmasoft.domain.models.BaseUser;
import com.bytmasoft.dss.token.helper.models.UserPrincipal;
import com.bytmasoft.dss.token.helper.repositories.ManagerLoginRepository;
import com.bytmasoft.dss.token.helper.repositories.StudentLoginRepository;
import com.bytmasoft.dss.token.helper.repositories.TeacherLoginRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserPrincipalDetailsService implements UserDetailsService{
	
	private final TeacherLoginRepository teacherLoginRepository;
	private final ManagerLoginRepository managerLoginRepository;
	private final StudentLoginRepository StudentLoginRepository;

	@Override
	public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
				
		 BaseUser user = findByUsername(username);

		if (user != null) {

			return new UserPrincipal(user);
		} else {

			throw new UsernameNotFoundException("user not found with loginname " + username);
		}
		
		
	}

	private BaseUser findByUsername(String username) {
		
		BaseUser user = null;
		
		user = this.StudentLoginRepository.findByUsernameOrEmail(username);
		
		if(user == null) {
			user = this.teacherLoginRepository.findByUsernameOrEmail(username);
			
		}
		if(user == null) {
			
			user = this.managerLoginRepository.findByUsernameOrEmail(username);
		}
		
		
		return user;
	}

}
