package com.bytmasoft.um.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bytmasoft.domain.models.Role;
import com.bytmasoft.persistance.interfaces.RoleService;
import com.bytmasoft.um.utils.UmMapping;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author Mahamat Abakar Date 12.10.2019
 */
@Api(value = "Permission management")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = UmMapping.ROLES, produces = { "application/json", "application/xml" }, consumes = {
		"application/json", "application/xml" })
public class RoleController {

	// TODO Mahamat 04.04.2020 21:11:32
	// integration Test
	private final RoleService roleService;

	@GetMapping("/{id}")
	Role findRoleById(@PathVariable Long id) {
		return roleService.findOne(id);
	}

	@GetMapping
	List<Role> getRoles() {

		return roleService.findAllResources();
	}

	@GetMapping("/active")
	List<Role> getAllActiveRole() {

		return roleService.findAllActive();
	}

	@GetMapping("/passive")
	List<Role> getAllInActiveRoles() {

		return roleService.findAllInActive();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Role createRole(@Valid @RequestBody Role role) {
		return roleService.create(role);
	}

	// TODO Mahamat 04.04.2020 21:10:47
	// implement http://localhost:8080/um/api/v1/roles/21/privileges/10

}
