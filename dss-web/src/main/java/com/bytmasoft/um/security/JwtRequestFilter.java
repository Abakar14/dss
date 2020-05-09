/**
 * 
 */
package com.bytmasoft.um.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.expression.ParseException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bytmasoft.domain.models.Teacher;
import com.bytmasoft.persistance.services.TeacherServiceImpl;
import com.bytmasoft.um.models.DSSUserDetails;
import com.bytmasoft.um.utils.TokenUtil;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.BadJOSEException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Mahamat Abakar Date 19.12.2019
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	
	private final TeacherServiceImpl teacherService;

	private final TokenUtil tokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");
		String loginname = null;
		String token = null;

		log.info("\nrequestTokenHeader : " + requestTokenHeader);

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {

			token = requestTokenHeader.substring(7);

			log.info("\nToken : " + token);

			try {

				loginname = tokenUtil.getUsernameFormToken(token);

			} catch (IllegalArgumentException | ParseException | BadJOSEException | JOSEException | java.text.ParseException e) {

				log.error(e.getMessage());
			}

		} else {
			log.warn("JWT Token does not begin with Bearer String");
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			auth.setAuthenticated(true);

		}

//		if(loginname != null && SecurityContextHolder.getContext().getAuthentication() != null) {
		if (loginname != null) {
			
			Teacher user = teacherService.findByOneUsername1(loginname);
			
			UserDetails userDetail = new DSSUserDetails(user);
			
//			UserDetails userDetail = this.loginService.loadUserByUsername(loginname);
		
			try {
				if (tokenUtil.validateToken(token, userDetail)) {

					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							userDetail, null, userDetail.getAuthorities());

					authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				}
			} catch (ParseException e) {
				log.error(e.getMessage());
			} catch (BadJOSEException e) {
				log.error(e.getMessage());
			} catch (JOSEException e) {
				log.error(e.getMessage()); 
			} catch (java.text.ParseException e) {
				log.error(e.getMessage()); 
			}
		}

		filterChain.doFilter(request, response);
	}

}
