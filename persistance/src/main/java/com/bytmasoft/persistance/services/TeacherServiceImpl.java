package com.bytmasoft.persistance.services;

import static com.bytmasoft.persistance.filters.UserPredicates.filterPages;
import static com.bytmasoft.persistance.filters.UserPredicates.filterUsers;
import static com.bytmasoft.persistance.filters.UserPredicates.isNotRemerkedForDelete;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bytmasoft.common.exception.UserAlreadyExistException;
import com.bytmasoft.domain.enums.UserType;
import com.bytmasoft.domain.models.Role;
import com.bytmasoft.domain.models.Teacher;
import com.bytmasoft.persistance.interfaces.DSSMailService;
import com.bytmasoft.persistance.interfaces.RoleService;
import com.bytmasoft.persistance.interfaces.TeacherService;
import com.bytmasoft.persistance.repositories.TeacherRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TeacherServiceImpl implements TeacherService<Teacher> {

	private final TeacherRepository repository;
	private final RoleService roleService;

	@Value("${spring.application.name}")
	private String appName;
	private DSSMailService mailServie;
	
	@Override
	public Teacher findOne(long id) {

		return repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("There is no Teacher with this id: " + id));
	}

	@Override
	public List<Teacher> findAllResources() {
		return (List<Teacher>) filterUsers((List<Teacher>) repository.findAll(), isNotRemerkedForDelete());
	}

	@Override
	public List<Teacher> findAllInActive() {

		return filterUsers(repository.findByStatus("I"), isNotRemerkedForDelete());

	}

	@Override
	public List<Teacher> findAllActive() {

		return filterUsers(repository.findByStatus("A"), isNotRemerkedForDelete());

	}

	@Override
	public List<Teacher> findAllPaginatedAndSorted(int page, int size, String sortBy, String sortOrder) {
		Pageable pageable;

		if ("DESC".equals(sortOrder.toUpperCase())) {
			pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());

		} else {
			pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());

		}

	
		Page<Teacher> result = (Page<Teacher>) filterPages(repository.findAll(pageable), isNotRemerkedForDelete());

		if (result.hasContent()) {

			return (List<Teacher>) result.getContent();
		} else {
			return new ArrayList<>();
		}

	}

	@Override
	public Teacher create(Teacher teacher) {

		Teacher t = this.CreateIfNotExists(teacher);
		if (t != null) {
			throw new UserAlreadyExistException("There is an user with the same email : " + teacher.getEmail());
		}

		teacher.setPassword(passwordEncoder().encode(teacher.getPassword()));

//		if (teacher.getRoles() != null) {
//
//		  teacher.getRoles().forEach(r -> r.setInsertedProg(appName));
//		}

		teacher.setInsertedProg(appName);
		addLoginname(teacher);
	
		return repository.save(teacher);

	}

	private boolean isEmailExists(String email) {

		if (repository.findByEmail(email) == null) {
			return true;
		} else {

			return false;
		}

	}

	@Override
	public void update(Teacher resource) {
		this.repository.save(resource);

	}

	@Override
	public void deleteById(long id) {
		this.repository.deleteById(id);

	}

	@Override
	public void deleteAll() {
		this.repository.findAll().forEach(t -> {
			if (t.getDeletestatus()) {

				deleteById(t.getId());
			}
		});

	}

	@Override
	public void deleteAllInActiveResources() {
		this.repository.findByStatus("I").forEach(t -> {
			if (t.getDeletestatus()) {
				deleteById(t.getId());
			}
		});

	}

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public int countActiveResources() {
		return repository.findByStatus("A").size();
	}

	@Override
	public int countInActiveResources() {
		return repository.findByStatus("I").size();
	}

	@Override
	public void activateById(long id) {
		Teacher teacher = findOne(id);
		teacher.setStatus("A");
		repository.save(this.setUpdateParams(teacher));

	}

	@Override
	public void deactivateById(long id) {

		Teacher t = this.findOne(id);
		if (t != null) {
			t.setStatus("I");
			update(t);
		}
	}

	@Override
	public void activateAll() {
		repository.activateAll();

	}

	@Override
	public void deactivateAll() {
		repository.deactivateAll();

	}

	@Override
	public Teacher setUpdateParams(Teacher t) {
		t.setUpdatedProg(appName);
		t.setUpdatedOn(LocalDateTime.now());
		t.setUpdatedBy(t.getUsername());
		return t;
	}

	private void addLoginname(Teacher teacher) {
		teacher.setUsername(generateLoginname(teacher));

	}

	@Override
	public String generateLoginname(Teacher teacher) {
		String toconcat = "";
		int day = LocalDateTime.now().getDayOfMonth();
		if (day < 10) {
			toconcat = "0" + day;
		} else {
			toconcat = "" + day;
		}
		return teacher.getLastName().substring(0, teacher.getLastName().length() - 1)
				.concat(teacher.getFirstName().substring(0, 1)).concat(toconcat).toUpperCase();

	}

	@Override
	public List<Teacher> findByEmail(String email) {

		return this.repository.findByEmail(email);
	}

	@Override
	public List<Teacher> findByLastName(String lastName) {

		return this.repository.findByLastName(lastName);
	}

	@Override
	public List<Teacher> findByFirstName(String fistName) {

		return this.repository.findByFirstName(fistName);
	}

	@Override
	public List<Teacher> findByMiddelName(String lastName) {

		return this.repository.findByMiddelName(lastName);
	}

	@Override
	public List<Teacher> findByFirstNameAndLastName(String firstname, String lastname) {

		return this.repository.findByFirstNameAndLastName(firstname, lastname);
	}

	@Override
	public List<Teacher> findByBirthday(LocalDate birthday) {

		return this.repository.findByBirthday(birthday);
	}

	@Override
	public List<Teacher> findByStatus(String status) {

		return repository.findByStatus(status);
	}

	@Override
	public List<Teacher> findByLastLogin(LocalDateTime lastLogin) {

		return this.repository.findByLastLogin(lastLogin);
	}

	@Override
	public List<Teacher> findByUsername(String username) {

		return this.repository.findByUsername(username);
	}

	public Teacher findByOneUsername1(String username) {

			return repository.findByUsernameOrEmail(username);
	}

	
	@Override
	public List<Teacher> findByType(UserType type) {

		return this.repository.findByType(type);
	}

	@Override
	public List<Teacher> findByFirstNameContainingIgnoreCase(String firstname) {
		return repository.findByFirstNameContainingIgnoreCase(firstname);
	}

	@Override
	public List<Teacher> findByLastNameContainingIgnoreCase(String lastname) {

		return repository.findByLastNameContainingIgnoreCase(lastname);
	}

	@Override
	public List<Teacher> findAllSortedBy(String sortOrder, String... properties) {

		return filterUsers(repository.findAllSortedBy(sortBy(sortOrder, properties)), isNotRemerkedForDelete());

	}

	@Override
	public List<Teacher> findByRequestParams(Map<String, String> requestParams) {

		String lastname = "";

		String email = "";

		String username = "";

		String type = "";
		String firstname = "";

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
			username = requestParams.get("loginname");
		}

	
		if (requestParams.containsKey("type")) {
			type = requestParams.get("type").toUpperCase();

		}

		if (!firstname.isEmpty() && !lastname.isEmpty() && !username.isEmpty()) {
			return findByFirstNameAndLastNameAndLoginname(firstname, lastname, username);
		} else if (!firstname.isEmpty() && !lastname.isEmpty()) {
			return findByFirstNameAndLastName(firstname, lastname);
		} else if (!firstname.isEmpty()) {
			return findByFirstName(firstname);
		} else if (!lastname.isEmpty()) {
			return findByLastName(lastname);
		} else if (!email.isEmpty()) {
			return findByEmail(email);
		} else if (!username.isEmpty()) {
			return findByUsername(username);
		} else if (!type.isEmpty()) {
			return findByType(UserType.valueOf(type));
		}
		return null;
	}

	@Override
	public Teacher findByFirstNameAndLastNameAndEmail(String firstname, String lastname, String email) {
		return (Teacher) repository.findByFirstNameAndLastNameAndEmail(firstname, lastname, email);
	}

	@Override
	public List<Teacher> findByFirstNameAndLastNameAndLoginname(String firstname, String lastname, String loginname) {
		return repository.findByFirstNameAndLastNameAndUsername(firstname, lastname, loginname);
	}

	@Override
	public boolean checkIfPasswordValid(Teacher user, String oldPassword) {
		return passwordEncoder().matches(oldPassword, user.getPassword());
	}

	@Override
	public void remerkByStatusForDelete(String status) {

		this.findByStatus(status).forEach(t -> {
			t.setDeletestatus(true);
			this.update(t);
		});

	}

	@Override
	public Teacher findUserById(Long teacher_id) {
		return this.getUserNotForDeletedRemerked(this.findOne(teacher_id));
		
	}

	
	@Override
	public List<Teacher> findUsers() {
		return filterUsers(this.findAllActive(), isNotRemerkedForDelete());
	}

	private BCryptPasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	/**
	 * 
	 * @param teacher_id
	 * @param role_id
	 * @return {@link Boolean} true if the user has the role
	 */
	private boolean hasUserThisRole(Long teacher_id, Long role_id) {
		Teacher teacher = findOne(teacher_id);
		boolean hasRole = false;

//		for (Role role : teacher.getRoles()) {
//
//			if (role.getId().equals(role_id)) {
//				hasRole = true;
//				return hasRole;
//			}
//		}
		return hasRole;
	}

	@Override
	public void remerkForDelete(Long id) {
		Teacher t = this.findOne(id);
		if (t != null) {
			t.setDeletestatus(true);
			this.update(t);
		}
	}

	@Override
	public void assignRoleToUser(Long teacher_id, Long role_id) {
		repository.assignRoleToUser(teacher_id, role_id);

	}

	@Override
	public void assignRoleToUsers(String name) {
		Role role = roleService.findByName(name);
		assignRoleToUsers(role.getId());

	}

	@Override
	public void assignRoleToUsers(Long roleId) {

		Role role = roleService.findOne(roleId);

		for (Teacher teacher : repository.findAll()) {
			if (!hasUserThisRole(teacher.getId(), role.getId())) {
//				teacher.addRole(role);
				repository.save(teacher);

			}
		}

	}

	@Override
	public List<Teacher> findUsersByRoleName(String rolename) {
		Role role = roleService.findByName(rolename);
		return repository.findTeachersByRoleId(role.getId());
	}

	@Override
	public List<Teacher> findUserByFirstnameSorted(String firstname, String sortBy, String sortOrder) {
		Sort sort = sortBy(sortOrder, sortBy);

		return repository.findByFirstNameContains(firstname, sort);
	}

	@Override
	public List<Teacher> findUserByFirstnamePaged(String firstname, int page, int size) {
		Pageable pageable = getPageable(page, size);
		return repository.findByFirstNameContains(firstname, pageable);
	}

	@Override
	public List<Teacher> findUsersByAgeMoreThan(Long age) {
		List<Teacher> teachers = new ArrayList<Teacher>();
		
				for (Teacher teacher : findAllResources()) {
		
					if (teacher.getAge() > age) {
		
						teachers.add(teacher);
		
					}
				}
		
				return teachers;
	}

	@Override
	public List<Teacher> findUsersByAgeLessThan(Long age) {
		List<Teacher> teachers = new ArrayList<Teacher>();

		for (Teacher teacher : findAllResources()) {

			if (teacher.getAge() < age) {

				teachers.add(teacher);

			}
		}

		return teachers;
	}

	@Override
	public void assignAddressToUser(Long user_id, Long address_id) {
		repository.assignAddressToUser(user_id, address_id);
	}

	@Override
	public List<Teacher> findUsersByRoleId(Long role_id) {
		return repository.findTeachersByRoleId(role_id);
		
	}

	@Override
	public List<Teacher> findUsersByPrivilegeId(Long privilege_id) {
		List<Teacher> teachers = new ArrayList<>();
//		this.findAllResources().forEach( t -> {
//			t.getRoles().forEach(r -> {
//				r.getPrivileges().forEach(p -> {
//					if(privilege_id.equals(p.getId())) {
//						teachers.add(t);
//					}
//				});
//			});
//			
//		});
		
		return teachers;
		
	}

	@Override
	public Teacher findUserByUserIdAndRoleId(Long teacher_id, Long role_id) {
		
		Teacher	teacher = findOne(teacher_id);
		Role role = roleService.findOne(role_id);
		
//		if(teacher.getRoles().contains(role)) {
//			return teacher;
//		}else {
//			
//			return null;
//		}
		return null;
		
	}

	@Override
	public Boolean updatePasswordByUserId(Long id, String confirmPassword) {
		Teacher t = this.findOne(id);
		t.setPassword(passwordEncoder().encode(confirmPassword));
		this.update(t);
		return true;
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
	 * @param page
	 * @param size
	 * @return
	 */
	private Pageable getPageable(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return pageable;
	}
	
	private Teacher getUserNotForDeletedRemerked(Teacher teacher) {
		if (!teacher.getDeletestatus()) {
			return teacher;
		} else {
			
			return null;
		}
	}

	@Override
	public void remerkUsersWithSchoolIdForDelete(Long school_Id) {

			this.findAllResources().forEach(t -> {
			if(t.getSchool().getId().equals(school_Id)) {
				t.setDeletestatus(true);
				this.update(t);
			}
		});
	}

	
	public List<Role> findRolesByUserId(Long id) {
		
		return this.findOne(id).getRoles();
	}
	
	
	public Role findRoleByUserIdAndRoleId(Long user_id, Long role_id){
		return findOne(user_id).getRoles().stream().filter(r -> role_id.equals(r.getId())).findAny().orElse(null);
	}
	
	@Override
	public Boolean sendEmailForChangingPassword(String usernameOrEmail) {
		Teacher teacher = repository.findByUsernameOrEmail(usernameOrEmail);

		return this.mailServie.sendEmail(teacher.getEmail());

	}

	@Override
	public Teacher CreateIfNotExists(Teacher t) {
		return findByFirstNameAndLastNameAndEmail(t.getFirstName(), t.getLastName(), t.getEmail());
	}
	
}
