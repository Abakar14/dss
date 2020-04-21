/**
 * 
 */
package com.bytmasoft.um.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytmasoft.domain.models.JwtRequest;
import com.bytmasoft.domain.models.JwtResponse;
import com.bytmasoft.login.models.UmUserDetails;
import com.bytmasoft.login.service.impl.LoginServiceImpl;
import com.bytmasoft.login.util.TokenUtil;

/**
 * 
 * @author Mahamat Abakar Date 19.12.2019
 */
@RestController
@CrossOrigin
@RequestMapping(produces = { "application/json", "application/xml" }, consumes = { "application/json",
		"application/xml" })
public class LoginController {
	// TODO Mahamat 04.04.2020 21:12:16
	// integration Test
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	TokenUtil tokenUtil;

	@Autowired
	private LoginServiceImpl umUserdetailsService;

	@PostMapping(path = "/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UmUserDetails userDetails = umUserdetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = tokenUtil.generateToken(userDetails);

		JwtResponse response = new JwtResponse(token);

		userDetails.getAuthorities().forEach(g -> {
			response.getRoles().add(g.getAuthority());
		});

		return ResponseEntity.ok(response);

	}

	// @PostMapping("/logout")
//	public String logout(HttpServletRequest request, HttpServletResponse response) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		if(auth != null) {
//			new SecurityContextLogoutHandler().logout(request, response, auth);
//			
//		}
//		
//		return "redirect:/authenticate";
//	}

	/**
	 * @param loginname
	 * @param password
	 */
	private void authenticate(String loginname, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginname, password));

		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}

	}

}
