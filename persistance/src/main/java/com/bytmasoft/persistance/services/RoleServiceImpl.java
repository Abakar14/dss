package com.bytmasoft.persistance.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bytmasoft.common.exception.EntityNotFoundException;
import com.bytmasoft.domain.enums.RoleType;
import com.bytmasoft.domain.models.Role;
import com.bytmasoft.messages.Messages;
import com.bytmasoft.persistance.interfaces.RoleService;
import com.bytmasoft.persistance.repositories.RoleRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

	private final RoleRepository repository;

	@Value("${spring.application.name}")
	private String appName;

	@Override
	public Role findOne(long id) {
		return repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("There is no role with this id: " + id));

	}

	@Override
	public Role findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public List<Role> findAllResources() {
		return (List<Role>) repository.findAll();
	}

	@Override
	public List<Role> findAllPaginatedAndSorted(int page, int size, String sortBy, String sortOrder) {
		Pageable pageable;

		if ("DESC".equals(sortOrder.toUpperCase())) {
			pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());

		} else {
			pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());

		}

		Page<Role> result = repository.findAll(pageable);
		if (result.hasContent()) {
			return result.getContent();
		} else {
			return new ArrayList<Role>();
		}
	}

	@Override
	public Role findRoleByUserIdAndRoleId(Long user_id, Long role_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role create(Role role) {
		
		Role r = this.CreateIfNotExists(role);
		if(r == null) {
			role.setInsertedProg(appName);
			return repository.save(role);
		}else {
			return r;
		}

	}

	@Override
	public void addPrivilegeToRole(Long role_id, Long privilege_id) {

		repository.addPrivilegeToRole(role_id, privilege_id);

	}

	@Override
	public void update(Role role) {
		repository.save(setUpdateParams(role));
	}

	@Override
	public void deleteById(long id) {
		repository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();

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
	public void deactivateById(long id) {

		Role role = findOne(id);
		role.setStatus("I");
		this.setUpdateParams(role);
		repository.save(role);

	}

	@Override
	public void activateById(long id) {
		Role role = findOne(id);
		role.setStatus("A");

		this.setUpdateParams(role);

		repository.save(role);

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
	public void deleteAllInActiveResources() {

		repository.deleteAllInActiveResources();

	}

	/**
	 * 
	 * @param source
	 * @return
	 */
	@Override
	public Role setUpdateParams(Role source) {
		source.setUpdatedProg(appName);
		source.setUpdatedOn(LocalDateTime.now());

		return source;
	}

	
	@Override
	public List<Role> findByStatus(String status) {

		return repository.findByStatus(status);
	}

	@Override
	public List<Role> findByType(String type) {

		return repository.findByType(RoleType.valueOf(type));
	}

	@Override
	public List<Role> findAllInActive() {

		return repository.findByStatus("I");
	}

	@Override
	public List<Role> findAllActive() {

		return repository.findByStatus("A");
	}

	@Override
	public List<Role> findRolesByUserId(Long user_id) {

		return repository.findRoleByUserId(user_id);
	}

	@Override
	public void addTypeToRole(Long roleId, RoleType type) {
		Role role = repository.findById(roleId)
				.orElseThrow(() -> new EntityNotFoundException(Messages.ROLE_NOT_FOUND));

		role.setType(type);
		repository.save(role);

	}

	@Override
	public void remerkForDelete(Long id) {
		Role r  = this.findOne(id);
		if(r != null) {
			r.setDeletestatus(true);
			this.update(r);
		}
		
	}

	@Override
	public void remerkByStatusForDelete(String status) {
		findByStatus(status).forEach(c -> {
			c.setDeletestatus(true);
			this.update(c);
		});		
	}

	@Override
	public Role CreateIfNotExists(Role r) {
		Role role = findByName(r.getName());
		if(role == null) {
			return null;
		}else {			
			return role;
		}
	}

}
