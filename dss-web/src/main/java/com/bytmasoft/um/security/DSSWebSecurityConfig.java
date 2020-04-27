/**
 * 
 */
package com.bytmasoft.um.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 
 * @author Mahamat Abakar Date 19.12.2019
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DSSWebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DSSAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtRequestFilter filter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

	}


	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		// TODO Mahamat 25.03.2020 20:49:22 ich müss die rechte und url nochmal richtig
		// ordnen!!! unten /users/user ....
		// @// @formatter:off
		httpSecurity.csrf().disable()
				// we do not need to authenticate login request
				.authorizeRequests().antMatchers("/login").permitAll()
				// These OPTIONS call are made by Angular application to Spring Boot application
				//.permitAll().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				// all other requests need to be authenticated
				.antMatchers(HttpMethod.GET, "/teachers/**").hasAnyAuthority("TEACHER")
				.antMatchers(HttpMethod.GET, "/Managers/", "/roles/", "/privileges/", "/teachers/")
				.hasAnyAuthority("ADMIN")
				.antMatchers( HttpMethod.POST, "/Managers/", "/roles/", "/privileges/", "/teachers/")
				.hasAnyAuthority("ADMIN")				
				.anyRequest().authenticated().and().exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// @formatter:on
		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

	}

}
