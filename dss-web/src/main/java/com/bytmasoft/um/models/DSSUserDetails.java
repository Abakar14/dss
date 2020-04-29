/**
 * 
 */
package com.bytmasoft.um.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bytmasoft.domain.models.Manager;
import com.bytmasoft.domain.models.Role;
import com.bytmasoft.domain.models.Student;
import com.bytmasoft.domain.models.Teacher;
import com.bytmasoft.domain.models.BaseUser;

/**
 * 
 * @author Mahamat Abakar Date 30.12.2019
 */

public class DSSUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7282182398219186511L;

	private BaseUser user;

	private List<GrantedAuthority> authorities;

	/**
	 * @param user
	 */
	public DSSUserDetails(BaseUser user) {

		this.user = user;

		getAuthorities();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		authorities = new ArrayList<>();
		
		if(user instanceof Teacher) {
			
			this.authorities = prepareAuthorities(((Teacher) user).getRoles());
	
		}else if(user instanceof Manager) {
		
			this.authorities = prepareAuthorities(((Manager) user).getRoles());
		}else if(user instanceof Student) {
		
			this.authorities = prepareAuthorities(((Student) user).getRoles());
		}
		
		
		return authorities;	
	}
	
	/**
	 * add a privileges and Roles to authorities
	 * @param roles
	 * @return
	 */
	private List<GrantedAuthority> prepareAuthorities(List<Role> roles){
		List<GrantedAuthority> authorities = new ArrayList<>();		
		
		roles.forEach(r -> {
			
			authorities.add(new SimpleGrantedAuthority("ROLE_"+r.getType()));
			
			r.getPrivileges().forEach(p -> {
				
				authorities.add(new SimpleGrantedAuthority(p.getName()));
			});
		});
		
		return authorities;
	}

	@Override
	public String getPassword() {

		return user.getPassword();
	}

	@Override
	public String getUsername() {

		return user.getUsername();
	}

	public String getEmail() {

		return user.getEmail();
	}

	// TODO Mahamat 21.03.2020 23:20:38 ich muss anschauen wie implementiere es am
	// besten
	@Override
	public boolean isAccountNonExpired() {

		return !user.getDeletestatus();
	}

	@Override
	public boolean isAccountNonLocked() {

		if (user.getStatus() != null && !user.getStatus().isEmpty()) {
			return user.getStatus().equals("A") ? true : false;

		} else {
			return false;
		}
	}

	// TODO Mahamat 21.03.2020 23:21:34 ich muss hier richtig implementieren
	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.getStatus().equals("A") ? true : false;
	}
	
	

}
