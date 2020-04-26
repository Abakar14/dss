/**
 * 
 */
package com.bytmasoft.login.service.impl;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bytmasoft.domain.models.BaseUser;
import com.bytmasoft.login.models.UmUserDetails;
import com.bytmasoft.login.repository.TeacherLoginRepository;

import lombok.RequiredArgsConstructor;

/**
 * 
 * @author Mahamat Abakar Date 19.12.2019
 */
@RequiredArgsConstructor
@Service
public class LoginServiceImpl implements UserDetailsService {

	private final TeacherLoginRepository repository;

	@Override
	public UmUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		BaseUser user = null;
//		User user = loginRepository.findByUsernameOrEmail(username, username);

		 user = repository.findByUsernameOrEmail(username, username);
				
		if (user != null) {

			return new UmUserDetails(user);
		} else {

			throw new UsernameNotFoundException("user not found with loginname " + username);
		}
	}

}
