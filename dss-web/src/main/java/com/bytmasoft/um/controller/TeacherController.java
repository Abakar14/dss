package com.bytmasoft.um.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bytmasoft.domain.models.Privilege;
import com.bytmasoft.domain.models.Role;
import com.bytmasoft.domain.models.Teacher;
import com.bytmasoft.persistance.services.TeacherServiceImpl;
import com.bytmasoft.um.utils.UmMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author Mahamat Abakar Date 08.10.2019
 */
@Api(value = "Usermanagement System Management all User operations")
@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping(path = UmMapping.TEACHERS, produces = { "application/json", "application/xml" }, consumes = {
		"application/json", "application/xml" })
public class TeacherController {
	// TODO Mahamat 04.04.2020 21:12:59
	// integration Test
	
	private final TeacherServiceImpl service;

	/***************************** Get Sections ****************************/

	@ApiOperation(value = "view a list of teacher", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/[id]")
	Teacher findTeacherById(@PathVariable Long id) {

		return service.findOne(id);
	}
	

	@ApiOperation(value = "view a list of users by age > {integer}", response = List.class)
	@GetMapping("/agemorethan/{age}")
	List<Teacher> findAllTeachersByAgeMoreThan(@PathVariable Long age) {
		return service.findUsersByAgeMoreThan(age);
	}

	@ApiOperation(value = "view a list of users by age < {integer}", response = List.class)
	@GetMapping("/agelessthan/{age}")
	List<Teacher> findAllTeachersByAgeLessThan(@PathVariable Long age) {
		return service.findUsersByAgeLessThan(age);
	}

	@ApiOperation(value = "view a list of user by request params", response = List.class)
	@GetMapping("/teacher")
	List<Teacher> findByRequestParams(@RequestParam Map<String, String> requestParams) {

		return service.findByRequestParams(requestParams);
	}

	@GetMapping("/count")
	Long countAllTeachers() {

		return service.count();
	}

	@GetMapping("/count/active")
	Integer countActiveTeachers() {

		return service.countActiveResources();
	}

	@GetMapping("/count/inactive")
	Integer countInActiveTeachers() {

		return service.countInActiveResources();
	}

	@ApiOperation(value = "view a list of Teachers", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping
	List<Teacher> getTeachers() {

		return service.findAllResources();
	}


	@ApiOperation(value = "view a list of teachers pagingated and sorted", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/page")
	List<Teacher> getUsersPagenatedAndSorted(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "5") int pageSize, @RequestParam(defaultValue = "lastName") String sortBy,
			@RequestParam(defaultValue = "ASC") String sortOrder) {

		return service.findAllPaginatedAndSorted(pageNo, pageSize, sortBy, sortOrder);
	}

//	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/active")
	List<Teacher> getAllActiveTeachers() {

		return service.findAllActive();
	}

	@GetMapping("/inactive")
	List<Teacher> getAllInActiveUsers() {

		return service.findAllInActive();
	}

	@GetMapping("{id}/roles")
	public List<Role> getAllRolesByUserId(@PathVariable Long id) {
		return service.findRolesByUserId(id);

	}

	@GetMapping("{user_id}/roles/{role_id}")
	public Role getRoleByTeacherIdAndRoleId(@PathVariable Long user_id, @PathVariable Long role_id) {
		return service.findRoleByUserIdAndRoleId(user_id, role_id);

	}

//	@GetMapping("{user_id}/privileges")
//	public List<Privilege> getRoleByUserIdAndRoleId(@PathVariable Long user_id) {
//		return this.service.findPrivilegesByUserId(user_id);
//
//	}

	/***************************** Post Sections ****************************/

	
//	@PostMapping("/sign-up")
//	public void signUp(@RequestBody Teacher teacher) {
//
//		teacher.setPassword(this.bCryptPasswordEncoder.encode(teacher.getPassword()));
//		
//		this.service.create(teacher);
//	}
	
	
//	@ApiOperation(value = "Add a user")
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public User register(@ApiParam(value = "User object to store", required = true) @Valid @RequestBody User user) {
//
//		return service.create(user);
//	}
//
//	/**
//	 * First ask user for changing his Password We need here a username or Email
//	 * then we generate a mail with link und send it to User
//	 * 
//	 * @param emailOrUsername
//	 * @return
//	 */
//	@PostMapping("/resetpassword")
//	Boolean changePassword(@RequestBody JwtRequest resetRequest) {
//		String usernameOrEmail = "";
//		if (!Strings.isNullOrEmpty(resetRequest.getEmail())) {
//			usernameOrEmail = resetRequest.getEmail();
//		} else if (!Strings.isNullOrEmpty(resetRequest.getUsername())) {
//			usernameOrEmail = resetRequest.getUsername();
//		}
//
//		return service.sendEmailForChangingPassword(usernameOrEmail);
//	}
//
//	/***************************** Put Sections ****************************/
//
//	/**
//	 * after user recieved a mail with link and filled all fields
//	 * 
//	 * @param emailOrUsername
//	 * @return
//	 */
//	@PutMapping("{id}/resetpassword")
//	Boolean confirmChangePassword(@PathVariable(value = "id") Long id, @RequestBody JwtRequest resetRequest) {
//		if (resetRequest.getPassword().equals(resetRequest.getConfirmPassword())) {
//			if (Strings.isNullOrEmpty(resetRequest.getConfirmPassword())) {
//				return service.updatePasswordByUserId(id, resetRequest.getConfirmPassword());
//
//			} else {
//				return false;
//			}
//
//		} else {
//			return false;
//		}
//
//	}
//
//	@PutMapping("/{id}")
//	public ResponseEntity<User> updateUser(@Valid @PathVariable(value = "id") Long id, @Valid @RequestBody User user)
//			throws EntityNotFoundException {
//		service.findOne(id);
//		user.setId(id);
//		service.update(user);
//		return ResponseEntity.ok(user);
//	}
//
//	@PutMapping("/activate")
//	public void activateAll() {
//		service.activateAll();
//	}
//
//	@PutMapping("/deactivate")
//	public void deactivateAll() {
//		service.deactivateAll();
//	}
//
//	@PutMapping("/{id}/activate")
//	public void activateUserById(@PathVariable Long id) {
//		service.activateById(id);
//	}
//
//	@PutMapping("/{id}/deactivate")
//	public void deactivateUserById(@PathVariable Long id) {
//		service.deactivateById(id);
//	}
//
//	@PutMapping("/assignto/group/{id}")
//	public void assignAllUsersToGroup(@PathVariable Long id) {
//		service.assignRoleToUsers(id);
//	}
//
//	@PutMapping("/roles/{id}")
//	public void assignRoleToAllUsers(@PathVariable Long id) {
//		service.assignRoleToUsers(id);
//	}
//
//	@PutMapping("{user_id}/roles/{role_id}")
//	void assignRoleToUser(@PathVariable Long user_id, @PathVariable Long role_id) {
//		service.assignRoleToUser(user_id, role_id);
//
//	}
//
//	@PutMapping("{user_id}/addresses/{address_id}")
//	void assignAddressToUser(@PathVariable Long user_id, @PathVariable Long address_id) {
//		service.assignAddressToUser(user_id, address_id);
//
//	}
//
//	/***************************** Delete Sections ****************************/
//
//	@ApiOperation(value = "delete all users")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
////		    @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
////		    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
////		    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
//	})
//	@DeleteMapping("schools/[id]")
//	void deleteUsersBySchoolId(@PathVariable Long id) {
//
//		service.remerkUsersWithSchoolIdForDelete(id);
//
//	}
//
//	@DeleteMapping("/{id}")
//	void deleteUserById(@PathVariable Long id) {
//		service.remerkByUserIdForDelete(id);
//
//	}
//
//	@DeleteMapping("/inactive")
//	void deleteInActiveUsers() {
//		service.deleteAllInActiveResources();
//
//	}

}
