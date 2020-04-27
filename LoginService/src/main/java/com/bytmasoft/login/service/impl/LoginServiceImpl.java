/**
 * 
 */
package com.bytmasoft.login.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bytmasoft.domain.models.BaseUser;
import com.bytmasoft.domain.models.Privilege;
import com.bytmasoft.domain.models.Role;
import com.bytmasoft.login.models.DSSUserDetails;
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
	public DSSUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		BaseUser user = null;
//		User user = loginRepository.findByUsernameOrEmail(username, username);

		user = repository.findByUsernameOrEmail(username, username);

		if (user != null) {

			return new DSSUserDetails(user);
		} else {

			throw new UsernameNotFoundException("user not found with loginname " + username);
		}
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles){
		List<GrantedAuthority> authorities = new ArrayList<>();
		roles.forEach(r -> {
			r.getPrivileges().forEach(p -> {
				
				authorities.add(new SimpleGrantedAuthority(p.getName()));
			});
		});
		return authorities;
	}

}