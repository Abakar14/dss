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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bytmasoft.domain.enums.UserType;
import com.bytmasoft.domain.models.EmailAddress;
import com.bytmasoft.domain.models.Role;
import com.bytmasoft.domain.models.Student;
import com.bytmasoft.persistance.repositories.StudentRepository;
import com.bytmasoft.persistance.service.interfaces.DSSMailService;
import com.bytmasoft.persistance.service.interfaces.RoleService;
import com.bytmasoft.persistance.service.interfaces.StudentService;
import com.bytmasoft.persistance.utils.UserUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

	private final StudentRepository repository;
	private final RoleService roleService;
	private final UserUtils<Student> userUtils;
	
	@Value("${spring.application.name}")
	private String appName;
	
	private DSSMailService mailServie;
	
	@Override
	public Student findOne(long id) {

		return repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("There is no Student with this id: " + id));
	}

	@Override
	public Student findByEmailAddress(String email) {
		return repository.findByEmailAddress(new EmailAddress(email));
	}
	
	
	@Override
	public List<Student> findAllResources() {
		return (List<Student>) filterUsers((List<Student>) repository.findAll(), isNotRemerkedForDelete());
	}

	@Override
	public List<Student> findAllInActive() {

		return filterUsers(repository.findByStatus("I"), isNotRemerkedForDelete());

	}

	@Override
	public List<Student> findAllActive() {

		return filterUsers(repository.findByStatus("A"), isNotRemerkedForDelete());

	}

	@Override
	public List<Student> findAllPaginatedAndSorted(int page, int size, String sortBy, String sortOrder) {
		Pageable pageable;

		if ("DESC".equals(sortOrder.toUpperCase())) {
			pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());

		} else {
			pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());

		}

	
		List<Student> result = filterPages(repository.findAll(pageable), isNotRemerkedForDelete());

		return result;

	}

	@Override
	public Student create(Student s) {

		
		boolean isExists = findByEmailAddress(s.getEmailAddress().toString()) !=null ? true : false;

		if(isExists) {
			return s;
		}else {
			
			addLoginname(s);
			s.setPassword(passwordEncoder().encode(s.getPassword()));
			s.setInsertedProg(appName);
			return repository.save(s);
		}
		
	}

	
	@Override
	public void update(Student resource) {
		this.repository.save(resource);

	}

	@Override
	public void deleteById(long id) {
		this.remerkUserForDelete(id);

	}

	@Override
	public void deleteAll() {
		this.repository.findAll().forEach(t -> {
				deleteById(t.getId());		
		});

	}

	@Override
	public void deleteAllInActiveResources() {
		this.repository.findByStatus("I").forEach(s -> {
			this.deleteById(s.getId());
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
		Student teacher = findOne(id);
		teacher.setStatus("A");
		repository.save(userUtils.setUpdateParams(teacher, appName));

	}

	@Override
	public void deactivateById(long id) {

		Student t = this.findOne(id);
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

	

	private void addLoginname(Student teacher) {
		teacher.setUsername(userUtils.generateLoginname(teacher));

	}

	@Override
	public List<Student> findByLastName(String lastName) {

		return this.repository.findByLastName(lastName);
	}

	@Override
	public List<Student> findByFirstName(String fistName) {

		return repository.findByFirstName(fistName);
	}

	@Override
	public List<Student> findByMiddelName(String lastName) {

		return this.repository.findByMiddelName(lastName);
	}

	@Override
	public List<Student> findByFirstNameAndLastName(String firstname, String lastname) {

		return this.repository.findByFirstNameAndLastName(firstname, lastname);
	}

	@Override
	public List<Student> findByBirthday(LocalDate birthday) {

		return this.repository.findByBirthday(birthday);
	}

	@Override
	public List<Student> findByStatus(String status) {

		return repository.findByStatus(status);
	}

	@Override
	public List<Student> findByLastLogin(LocalDateTime lastLogin) {

		return this.repository.findByLastLogin(lastLogin);
	}

	@Override
	public Student findByUsername(String username) {

		return (Student) repository.findByUsername(username);
	}

	public Student findByOneUsername1(String username) {

			return repository.findByUsernameOrEmail(username);
	}

	
	@Override
	public List<Student> findByType(UserType type) {

		return this.repository.findByType(type);
	}

	@Override
	public List<Student> findByFirstNameContainingIgnoreCase(String firstname) {
		return repository.findByFirstNameContainingIgnoreCase(firstname);
	}

	@Override
	public List<Student> findByLastNameContainingIgnoreCase(String lastname) {

		return repository.findByLastNameContainingIgnoreCase(lastname);
	}

	@Override
	public List<Student> findAllSortedBy(String sortOrder, String... properties) {

		return filterUsers(repository.findAllSortedBy(sortBy(sortOrder, properties)), isNotRemerkedForDelete());

	}

	@Override
	public List<Student> findByRequestParams(Map<String, String> requestParams) {

		String lastname = "";		

		String username = "";

		String type = "";
		String firstname = "";

		if (requestParams.containsKey("firstname")) {
			firstname = requestParams.get("firstname");
		}

		if (requestParams.containsKey("lastname")) {
			lastname = requestParams.get("lastname");
		}

			if (requestParams.containsKey("loginname")) {
			username = requestParams.get("loginname");
		}

	
		if (requestParams.containsKey("type")) {
			type = requestParams.get("type").toUpperCase();

		}

		if (!firstname.isEmpty() && !lastname.isEmpty()) {
			return findByFirstNameAndLastName(firstname, lastname);
		} else if (!firstname.isEmpty()) {
			return findByFirstName(firstname);
		} else if (!lastname.isEmpty()) {
			return findByLastName(lastname);
		}  else if (!type.isEmpty()) {
			return findByType(UserType.valueOf(type));
		}
		return null;
	}

	
	@Override
	public Student findByFirstNameAndLastNameAndLoginname(String firstname, String lastname, String loginname) {
		return repository.findByFirstNameAndLastNameAndUsername(firstname, lastname, loginname);
	}

	@Override
	public boolean checkIfPasswordValid(Student user, String oldPassword) {
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
	public Student findUserById(Long teacher_id) {
		return this.getUserNotForDeletedRemerked(this.findOne(teacher_id));
		
	}

	
	@Override
	public List<Student> findUsers() {
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
		Student teacher = findOne(teacher_id);
		boolean hasRole = false;

		for (Role role : teacher.getRoles()) {

			if (role.getId().equals(role_id)) {
				hasRole = true;
				return hasRole;
			}
		}
		return hasRole;
	}

	@Override
	public void remerkForDelete(Long id) {
		Student t = this.findOne(id);
		if (t != null) {
			t.setDeletestatus(true);
			this.update(t);
		}
	}

	@Override
	public void assignRoleToUser(Long student_id, Long role_id) {
		repository.assignRoleToUser(student_id, role_id);

	}

	@Override
	public void assignRoleToUsers(String name) {
		Role role = roleService.findByName(name);
		assignRoleToUsers(role.getId());

	}

	@Override
	public void assignRoleToUsers(Long roleId) {

		Role role = roleService.findOne(roleId);

		for (Student student : repository.findAll()) {
			if (!hasUserThisRole(student.getId(), role.getId())) {
				student.addRole(role);
				
				repository.save(student);

			}
		}

	}

	@Override
	public List<Student> findUsersByRoleName(String rolename) {
		Role role = roleService.findByName(rolename);
		return this.findUsersByRoleId(role.getId());
	}

	@Override
	public List<Student> findUserByFirstnameSorted(String firstname, String sortBy, String sortOrder) {
		Sort sort = sortBy(sortOrder, sortBy);

		return repository.findByFirstNameContains(firstname, sort);
	}

	@Override
	public List<Student> findUserByFirstnamePaged(String firstname, int page, int size) {
		Pageable pageable = getPageable(page, size);
		return repository.findByFirstNameContains(firstname, pageable);
	}

	@Override
	public List<Student> findUsersByAgeMoreThan(int age) {
		List<Student> students = new ArrayList<Student>();
		
				for (Student student : findAllResources()) {
		
					if (student.getAge() > age) {
		
						students.add(student);
		
					}
				}
		
				return students;
	}

	@Override
	public List<Student> findUsersByAgeLessThan(int age) {
		List<Student> students = new ArrayList<Student>();

		for (Student student : findAllResources()) {

			if (student.getAge() < age) {

				students.add(student);

			}
		}

		return students;
	}

	@Override
	public void assignAddressToUser(Long user_id, Long address_id) {
		repository.assignAddressToUser(user_id, address_id);
	}

	@Override
	public List<Student> findUsersByRoleId(Long role_id) {
		return repository.findStudentsByRoleId(role_id);
		
	}

	@Override
	public List<Student> findUsersByPrivilegeId(Long privilege_id) {
		List<Student> students = new ArrayList<>();
		
		this.findAllResources().forEach( s -> {
			s.getRoles().forEach(r -> {
				r.getPrivileges().forEach(p -> {
					if(privilege_id.equals(p.getId())) {
						students.add(s);
					}
				});
			});
			
		});
		
		return students;
		
	}

	@Override
	public Student findUserByUserIdAndRoleId(Long teacher_id, Long role_id) {
		
		Student	teacher = findOne(teacher_id);
		Role role = roleService.findOne(role_id);
		
		if(teacher.getRoles().contains(role)) {
			return teacher;
		}else {
			
			return null;
		}

		
	}

	@Override
	public Boolean updatePasswordByUserId(Long id, String confirmPassword) {
		Student t = this.findOne(id);
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
	
	private Student getUserNotForDeletedRemerked(Student teacher) {
		if (!teacher.getDeletestatus()) {
			return teacher;
		} else {
			
			return null;
		}
	}

	@Override
	public void remerkUsersWithSchoolIdForDelete(Long school_Id) {

//			this.findAllResources().forEach(t -> {
//			if(t.getSchool().getId().equals(school_Id)) {
//				t.setDeletestatus(true);
//				this.update(t);
//			}
//		});
	}
	

	@Override
	public Boolean sendEmailForChangingPassword(String usernameOrEmail) {

		Student student = repository.findByUsernameOrEmail(usernameOrEmail);

		return this.mailServie.sendEmail(student.getEmailAddress().toString());

	}

	
	@Override
	public Student findByFirstNameAndLastNameAndEmailAddress(String firstname, String lastname, EmailAddress email) {
		return repository.findByFirstNameAndLastNameAndEmailAddress(firstname, lastname, email);
	}
	


	@Override
	public void remerkUserForDelete(Long user_id) {
		Student s = findOne(user_id);
		s.setDeletestatus(true);
		s.setStatus("I");
		this.update(s);

	}

	@Override
	public Student findByUsernameOrEmail(String usernameOrEmail) {
		
		return repository.findByUsernameOrEmail(usernameOrEmail);
	}

}
