package com.bytmasoft.um.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bytmasoft.domain.models.Privilege;
import com.bytmasoft.persistance.service.interfaces.PrivilegeService;
import com.bytmasoft.um.utils.UmMapping;

import io.swagger.annotations.Api;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author Mahamat Abakar Date 12.10.2019
 */
@Api(value = "Permission management")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = UmMapping.PRIVILEGES, produces = { "application/json", "application/xml" }, consumes = {
		"application/json", "application/xml" })
public class PrivilegeController {

	// TODO Mahamat 04.04.2020 21:12:42
	// integration Test
	private final PrivilegeService privilegeService;

	@GetMapping("/{id}")
	Privilege findPrivilegeById(@PathVariable("id") long id) throws NotFoundException {

		return privilegeService.findOne(id);

	}

	@GetMapping
	List<Privilege> getPrivileges() {
		return privilegeService.findAllResources();
	}

	@GetMapping("/privilege")
	Privilege findByRequestParams(@RequestParam Map<String, String> requestParams) {

		return privilegeService.findByRequestParams(requestParams);
	}

	@PutMapping("{group_id}/users/{user_id}")
	void addPrivilegeToUser(@PathVariable Long group_id, @PathVariable Long user_id) {

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Privilege createPrivilege(@RequestBody Privilege privilege) {
		return privilegeService.create(privilege);
	}

}
