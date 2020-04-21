/**
 * 
 */
package com.bytmasoft.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bytmasoft.domain.models.User;
import com.bytmasoft.login.models.UmUserDetails;
import com.bytmasoft.login.repository.LoginRepository;

/**
 * 
 * @author Mahamat Abakar Date 19.12.2019
 */
//@RequiredArgsConstructor
@Service
public class LoginServiceImpl implements UserDetailsService {

	@Autowired
	LoginRepository loginRepository;

	@Override
	public UmUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = loginRepository.findByUsernameOrEmail(username, username);

		if (user != null) {

			return new UmUserDetails(user);
		} else {

			throw new UsernameNotFoundException("user not found with loginname " + username);
		}
	}

}
