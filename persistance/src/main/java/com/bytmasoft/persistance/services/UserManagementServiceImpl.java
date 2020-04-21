package com.bytmasoft.persistance.services;

import static com.bytmasoft.persistance.filters.UserPredicates.filterUsers;
import static com.bytmasoft.persistance.filters.UserPredicates.isNotRemerkedForDelete;
import static com.bytmasoft.persistance.filters.UserPredicates.isParent;
import static com.bytmasoft.persistance.filters.UserPredicates.isTeacher;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bytmasoft.common.exception.EntityNotFoundException;
import com.bytmasoft.domain.enums.UserType;
import com.bytmasoft.domain.models.Privilege;
import com.bytmasoft.domain.models.Role;
import com.bytmasoft.domain.models.School;
import com.bytmasoft.domain.models.User;
import com.bytmasoft.persistance.interfaces.RoleService;
import com.bytmasoft.persistance.interfaces.SchoolService;
import com.bytmasoft.persistance.interfaces.UserManagementService;
import com.bytmasoft.persistance.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserManagementServiceImpl implements UserManagementService {

	private final UserRepository userRepository;
	private final RoleService roleService;
	// private final PrivilegeService privilegeServie;
	private final SchoolService schoolServie;
	private final DSSMailServiceImpl mailServie;

	@Value("${spring.application.name}")
	private String appName;

	@Override
	public void activateAll() {
		userRepository.activateAll();
	}

	@Override
	public void activateById(long id) {
		User user = findOne(id);
		user.setStatus("A");

		userRepository.save(this.setUpdateParams(user));

	}

	private void addLoginname(User user) {
		user.setUsername(generateLoginname(user));

	}

	@Override
	public void assignRoleToUsers(Long roleId) {

		Role role = roleService.findOne(roleId);

		for (User user : userRepository.findAll()) {
			if (!hasUserThisRole(user.getId(), role.getId())) {
				user.addRole(role);
				userRepository.save(user);

			}
		}

	}

	@Override
	public void assignRoleToUsers(String rolename) {
		Role role = roleService.findByName(rolename);
		assignRoleToUsers(role.getId());
	}

	@Override
	public void assignRoleToUser(Long user_id, Long role_id) {
		userRepository.assignRoleToUser(user_id, role_id);
	}

	@Override
	public void assignAddressToUser(Long user_id, Long address_id) {
		userRepository.assignAddressToUser(user_id, address_id);

	}

	@Override
	public long count() {
		return userRepository.count();
	}

	@Override
	public long countActiveResources() {
		return userRepository.countActiveResources();
	}

	@Override
	public long countInActiveResources() {
		return userRepository.countAllInActiveUsers();
	}

	@Override
	public User create(User user) {

//		if(isEmailExists(user.getEmail())) {
//			throw new UserAlreadyExistException("There is an user with the same email : "+ user.getEmail());
//		}

		user.setPassword(passwordEncoder().encode(user.getPassword()));

		if (user.getRoles() != null) {

			user.getRoles().forEach(r -> r.setInsertedProg(appName));
		}

		user.setInsertedProg(appName);
		addLoginname(user);
		return userRepository.save(user);
	}

	@Override
	public void deactivateAll() {
		userRepository.deactivateAll();

	}

	@Override
	public void deactivateById(long id) {

		User user = findOne(id);
		user.setStatus("I");
		this.setUpdateParams(user);
		userRepository.save(user);

	}

	@Override
	public void deleteAll() {
		userRepository.deleteAll();
	}

	@Override
	public void deleteAllInActiveResources() {

		userRepository.findByStatus("I").forEach(u -> {
			this.remerkByUserIdForDelete(u.getId());
		});

	}

	@Override
	public void deleteById(long id) {
		userRepository.deleteById(id);

	}

	@Override
	public List<User> findAllActive() {

		return filterUsers(userRepository.findByStatus("A"), isNotRemerkedForDelete());
	}

	@Override
	public List<User> findAllInActive() {
		return filterUsers(userRepository.findByStatus("I"), isNotRemerkedForDelete());
	}

	@Override
	public List<User> findAllPaginatedAndSorted(int page, int size, String sortBy, String sortOrder) {
		Pageable pageable;

		if ("DESC".equals(sortOrder.toUpperCase())) {
			pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());

		} else {
			pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());

		}

		Page<User> result = filterUsers(userRepository.findAll(pageable), isNotRemerkedForDelete());

		if (result.hasContent()) {

			return result.getContent();
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public List<User> findAllResources() {

		return filterUsers((List<User>) userRepository.findAll(), isNotRemerkedForDelete());

	}

	@Override
	public List<User> findAllSortedBy(String sortOrder, String... properties) {

		return filterUsers(userRepository.findAllSortedBy(sortBy(sortOrder, properties)), isNotRemerkedForDelete());

	}

	@Override
	public List<User> findUsersByRoleName(String rolename) {

		Role role = roleService.findByName(rolename);

		return userRepository.findUsersByRoleName(role.getId());
	}

	@Override
	public List<User> findByFirstNameContainingIgnoreCase(String firstname) {
		return userRepository.findByFirstNameContainingIgnoreCase(firstname);
	}

	@Override
	public List<User> findByLastNameContainingIgnoreCase(String lastname) {
		return userRepository.findByLastNameContainingIgnoreCase(lastname);
	}

	@Override
	public User findOne(long id) {

		return userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("There is no user with this id: " + id));

	}

	@Override
	public List<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> findByFirstNameAndLastName(String firstname, String lastname) {

		return userRepository.findByFirstNameAndLastName(firstname, lastname);
	}

	@Override
	public List<User> findUserByFirstnamePaged(String firstname, int page, int size) {

		Pageable pageable = getPageable(page, size);

		return userRepository.findByFirstNameContains(firstname, pageable);

	}

	@Override
	public List<User> findUserByFirstnameSorted(String firstname, String sortBy, String sortOrder) {

		Sort sort = sortBy(sortOrder, sortBy);

		return userRepository.findByFirstNameContains(firstname, sort);
	}

	@Override
	public String generateLoginname(User user) {
		String toconcat = "";
		int day = LocalDateTime.now().getDayOfMonth();
		if (day < 10) {
			toconcat = "0" + day;
		} else {
			toconcat = "" + day;
		}
		return user.getLastName().substring(0, user.getLastName().length() - 1)
				.concat(user.getFirstName().substring(0, 1)).concat(toconcat).toUpperCase();

	}

//	private Sort getSort(String sortBy, String sortOrder) {
//
//		Sort sort;
//		if ("DESC".equals(sortOrder)) {
//
//			sort = new Sort(Direction.DESC, sortBy);
//		} else {
//			sort = new Sort(Direction.ASC, sortBy);
//		}
//
//		return sort;
//	}

	@Override
	public User setUpdateParams(User source) {
		source.setUpdatedProg(appName);
		source.setUpdatedOn(LocalDateTime.now());
		source.setUpdatedBy(source.getUsername());
		return source;
	}

	@Override
	public void update(User resource) {

		userRepository.save(resource);

	}

	@Override
	public List<User> findByLastName(String lastName) {

		return userRepository.findByLastName(lastName);
	}

	@Override
	public List<User> findByFirstName(String fistName) {

		return userRepository.findByFirstName(fistName);
	}

	@Override
	public List<User> findByMiddelName(String middelName) {

		return userRepository.findByMiddelName(middelName);
	}

	@Override
	public List<User> findByBirthday(LocalDate birthday) {

		return userRepository.findByBirthday(birthday);
	}

	@Override
	public List<User> findByStatus(String status) {

		return userRepository.findByStatus(status);
	}

	@Override
	public List<User> findByLastLogin(LocalDateTime lastLogin) {

		return userRepository.findByLastLogin(lastLogin);
	}

	@Override
	public List<User> findByLoginname(String loginname) {

		return userRepository.findByUsername(loginname);
	}

	@Override
	public List<User> findByType(UserType type) {

		return userRepository.findByType(type);
	}

//	@Override
//	public List<User> findByCountryName(String countryName) {
//		
//		return userRepository.findByCountryName(countryName);
//	}

	@Override
	public List<User> findUsersByAgeLessThan(Long age) {
		List<User> users = new ArrayList<User>();

		for (User user : findAllResources()) {

			if (user.getAge() < age) {

				users.add(user);

			}
		}

		return users;
	}

	@Override
	public boolean checkIfPasswordValid(final User user, String oldPassword) {

		return passwordEncoder().matches(oldPassword, user.getPassword());

	}

	@Override
	public List<User> findUsersByAgeMoreThan(Long age) {

		List<User> users = new ArrayList<User>();

		for (User user : findAllResources()) {

			if (user.getAge() > age) {

				users.add(user);

			}
		}

		return users;
	}

	@Override
	public List<User> findByRequestParams(Map<String, String> requestParams) {

		String firstname = "";

		String lastname = "";

		String email = "";

		String loginname = "";
//		String country = "";

		String type = "";

		String groupname = "";

		if (requestParams.containsKey("firstname")) {
			firstname = requestParams.get("firstname");
		}

		if (requestParams.containsKey("lastname")) {
			lastname = requestParams.get("lastname");
		}

		if (requestParams.containsKey("email")) {
			email = requestParams.get("email");
		}

		if (requestParams.containsKey("loginname")) {
			loginname = requestParams.get("loginname");
		}

//		if(requestParams.containsKey("country")) {
//			country = requestParams.get("country");
//		}

		if (requestParams.containsKey("type")) {
			type = requestParams.get("type").toUpperCase();

		}

		if (requestParams.containsKey("groupname")) {
			groupname = requestParams.get("groupname");
		}

		if (!firstname.isEmpty() && !lastname.isEmpty() && !loginname.isEmpty()) {
			return findByFirstNameAndLastNameAndLoginname(firstname, lastname, loginname);
		} else if (!firstname.isEmpty() && !lastname.isEmpty() && !email.isEmpty()) {
			return findByFirstNameAndLastNameAndEmail(firstname, lastname, email);
		} else if (!firstname.isEmpty() && !lastname.isEmpty()) {
			return findByFirstNameAndLastName(firstname, lastname);
		} else if (!firstname.isEmpty()) {
			return findByFirstName(firstname);
		} else if (!lastname.isEmpty()) {
			return findByLastName(lastname);
		} else if (!email.isEmpty()) {
			return findByEmail(email);
		} else if (!loginname.isEmpty()) {
			return findByLoginname(loginname);
		} else if (!type.isEmpty()) {
			return findByType(UserType.valueOf(type));
		} else if (!groupname.isEmpty()) {
			return findUsersByRoleName(groupname);
		}

		return null;

	}

	/**
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @return
	 */
	@Override
	public List<User> findByFirstNameAndLastNameAndEmail(String firstname, String lastname, String email) {

		return userRepository.findByFirstNameAndLastNameAndEmail(firstname, lastname, email);
	}

	/**
	 * @param firstname
	 * @param lastname
	 * @param loginname
	 * @return
	 */
	@Override
	public List<User> findByFirstNameAndLastNameAndLoginname(String firstname, String lastname, String loginname) {
		return userRepository.findByFirstNameAndLastNameAndUsername(firstname, lastname, loginname);
	}

	/**
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	private Pageable getPageable(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return pageable;
	}

	/**
	 * 
	 * @param sortOrder
	 * @param columnsname
	 * @return {@link Sort}
	 */
	private Sort sortBy(String sortOrder, String... properties) {
		Direction d = Sort.Direction.fromString(sortOrder);
		return Sort.by(d, properties);
	}

	/**
	 * 
	 * @param user_id
	 * @param role_id
	 * @return {@link Boolean} true if the user has the role
	 */
	private boolean hasUserThisRole(Long user_id, Long role_id) {
		User user = findOne(user_id);
		boolean hasRole = false;

		for (Role role : user.getRoles()) {

			if (role.getId().equals(role_id)) {
				hasRole = true;
				return hasRole;
			}
		}
		return hasRole;
	}

	private BCryptPasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Override
	public List<School> findSchoolsByUserId(Long user_id) {

		return schoolServie.findSchoolsByUserId(user_id);
	}

	@Override
	public List<Role> findRolesByUserId(Long user_id) {

		return this.roleService.findRolesByUserId(user_id);
	}

	@Override
	public List<Privilege> findPrivilegesByUserId(Long user_id) {

		List<Role> roles = roleService.findRolesByUserId(user_id);
		List<Privilege> privileges = new ArrayList<>();

		roles.forEach(r -> {
			privileges.addAll(r.getPrivileges());
		});

		return privileges;
	}

	@Override
	public Role findRoleByUserIdAndRoleId(Long user_id, Long role_id) {

		return roleService.findRoleByUserIdAndRoleId(user_id, role_id);
	}

	@Override
	public void remerkByUserIdForDelete(Long user_id) {
		this.userRepository.remerkByUserIdForDelete(user_id);

	}

	@Override
	public void remerkUsersWithStatusForDelete(String status) {
		List<User> users = userRepository.findByStatus(status);

		users.forEach(u -> {
			this.remerkByUserIdForDelete(u.getId());
		});

	}

	@Override
	public void remerkUsersWithSchoolIdForDelete(Long school_Id) {

		School school = schoolServie.findOne(school_Id);

		List<User> users = userRepository.findBySchool(school);

		users.forEach(u -> {
			this.remerkByUserIdForDelete(u.getId());
		});

	}

	@Override
	public void remerkUsersWithSchoolNameForDelete(String name) {

		List<School> schools = schoolServie.findByName(name);

		schools.forEach(s -> {

			List<User> users = userRepository.findBySchool(s);

			users.forEach(u -> {
				this.remerkByUserIdForDelete(u.getId());
			});
		});

	}

	@Override
	public User findParentById(Long parent_id) {

		User user = this.findOne(parent_id);
		return this.getUserNotForDeletedRemerked(user);
	}

	@Override
	public List<User> findParents() {

		return filterUsers(this.findAllResources(), isParent());
	}

	@Override
	public User findTeacherById(Long teacher_id) {

		User user = this.getUserNotForDeletedRemerked(this.findOne(teacher_id));

		if (user.getType().equals(UserType.PARENT)) {
			return user;
		} else {
			return null;
		}

	}

	@Override
	public List<User> findTeachers() {
		return filterUsers(this.findAllActive(), isTeacher());
	}

	@Override
	public User findStudentById(Long student_id) {

		return this.getUserNotForDeletedRemerked(this.findOne(student_id));
	}

	private User getUserNotForDeletedRemerked(User user) {
		if (!user.getDeletestatus()) {
			return user;
		} else {
			User emptyUser = new User();
			System.out.println(emptyUser.toString());
			return emptyUser;
		}
	}

	@Override
	public Boolean sendEmailForChangingPassword(String usernameOrEmail) {
		User user = userRepository.findByUsernameOrEmail(usernameOrEmail);

		return this.mailServie.sendEmail(user.getEmail());

	}

	@Override
	public Boolean updatePasswordByUserId(Long id, String password) {

		User user = this.findOne(id);
		user.setPassword(passwordEncoder().encode(password));
		userRepository.save(user);
		return true;
	}

}
