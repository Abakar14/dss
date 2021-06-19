package com.bytmasoft.dss.token.helper.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bytmasoft.common.exception.DSSEntityNotFoundException;
import com.bytmasoft.domain.models.User;
import com.bytmasoft.dss.token.helper.models.UserPrincipal;
import com.bytmasoft.dss.token.helper.repositories.StudentLoginRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StudentPrincipalDetailsService implements UserDetailsService {

	private final StudentLoginRepository studentLoginRepository;

	@Override
	public UserPrincipal loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

		return new UserPrincipal(findByUsernameOrEmail(usernameOrEmail));

	}

	private User findByUsernameOrEmail(String username) {

		User user = studentLoginRepository.findByUsernameOrEmail(username);

		if (user == null) {

			throw new DSSEntityNotFoundException(String.format("user with : %s, loginname not found...", username));

		}

		return user;
	}

}
