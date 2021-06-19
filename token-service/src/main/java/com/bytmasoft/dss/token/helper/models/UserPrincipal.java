package com.bytmasoft.dss.token.helper.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bytmasoft.domain.models.User;
import com.bytmasoft.domain.models.Employee;
import com.bytmasoft.domain.models.Manager;
import com.bytmasoft.domain.models.Role;
import com.bytmasoft.domain.models.Student;
import com.bytmasoft.domain.models.Teacher;

public class UserPrincipal implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6507182070784569574L;
	private User user;
	private Set<GrantedAuthority> authorities;
	private String salz;
	private String password;
	private String username;

	public UserPrincipal(User user) {
		this.password = user.getPassword();
		this.user = user;
		this.username = user.getLoginname();
		this.salz = user.getSalt();
		getAuthorities();

	}

	/**
	 * initiates a new UserPrincipal
	 */
	@SuppressWarnings("unused")
	private UserPrincipal() {

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		authorities = new HashSet<>();

		if (user instanceof Teacher) {

			this.authorities = prepareAuthorities(((Teacher) user).getRoles());

		} else if (user instanceof Manager) {

			this.authorities = prepareAuthorities(((Manager) user).getRoles());
		} else if (user instanceof Student) {

			this.authorities = prepareAuthorities(((Student) user).getRoles());
		} else if (user instanceof Employee) {

			this.authorities = prepareAuthorities(((Employee) user).getRoles());
		}

		return authorities;
	}

	/**
	 * add a privileges and Roles to authorities
	 * 
	 * @param roles
	 * @return
	 */
	private Set<GrantedAuthority> prepareAuthorities(Set<Role> roles) {
		Set<GrantedAuthority> authorities = new HashSet<>();

		roles.forEach(r -> {

			authorities.add(new SimpleGrantedAuthority("ROLE_" + r.getType()));

			r.getPrivileges().forEach(p -> {

				authorities.add(new SimpleGrantedAuthority(p.getName()));
			});
		});

		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !user.getDeletestatus();
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.getActive();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.getActive();
	}

	public String getEmail() {
		return user.getEmail();
	}

	public String getSalz() {
		return salz;
	}

}
