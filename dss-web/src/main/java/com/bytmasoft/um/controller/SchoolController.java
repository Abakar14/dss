/**
 * @author Mahamat
 * Date 13.04.2020 : 18:38:37
 */
package com.bytmasoft.um.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bytmasoft.domain.models.School;
import com.bytmasoft.persistance.interfaces.SchoolService;
import com.bytmasoft.um.utils.UmMapping;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

/**
 * @author Mahamat Date 13.04.2020 : 18:38:37
 */
@Api(value = "School Controller")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = UmMapping.SCHOOLS, produces = { "application/json", "application/xml" }, consumes = {
		"application/json", "application/xml" })
public class SchoolController {

	@Autowired
	private final SchoolService service;

	@GetMapping("/{id}")
	School findSchoolById(@PathVariable Long id) {
		return service.findOne(id);
	}

	@GetMapping
	List<School> getSchools() {

		return service.findAllResources();
	}

	@GetMapping("/active")
	List<School> getAllActiveSchool() {

		return service.findAllActive();
	}

	@GetMapping("/passive")
	List<School> getAllInActiveSchools() {

		return service.findAllInActive();
	}

	@GetMapping("{id}/Schools")
	public List<School> getAllSchoolsForThisUserId(@PathVariable Long id) {
		return service.findSchoolsByUserId(id);

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public School createSchool(@Valid @RequestBody School school) {
		return service.create(school);
	}

}
