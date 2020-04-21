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

	private final RoleRepository roleRepository;

	@Value("${spring.application.name}")
	private String appName;

	@Override
	public Role findOne(long id) {
		return roleRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("There is no role with this id: " + id));

	}

	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

	@Override
	public List<Role> findAllResources() {
		return (List<Role>) roleRepository.findAll();
	}

	@Override
	public List<Role> findAllPaginatedAndSorted(int page, int size, String sortBy, String sortOrder) {
		Pageable pageable;

		if ("DESC".equals(sortOrder.toUpperCase())) {
			pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());

		} else {
			pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());

		}

		Page<Role> result = roleRepository.findAll(pageable);
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
		role.setInsertedProg(appName);

		return roleRepository.save(role);
	}

	@Override
	public void addPrivilegeToRole(Long role_id, Long privilege_id) {

		roleRepository.addPrivilegeToRole(role_id, privilege_id);

	}

	@Override
	public void update(Role role) {
		roleRepository.save(setUpdateParams(role));
	}

	@Override
	public void deleteById(long id) {
		roleRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		roleRepository.deleteAll();

	}

	@Override
	public long count() {
		return roleRepository.count();
	}

	@Override
	public long countActiveResources() {

		return roleRepository.countActiveResources();
	}

	@Override
	public long countInActiveResources() {

		return roleRepository.countAllInActiveRights();
	}

	@Override
	public void deactivateById(long id) {

		Role role = findOne(id);
		role.setStatus("I");
		this.setUpdateParams(role);
		roleRepository.save(role);

	}

	@Override
	public void activateById(long id) {
		Role role = findOne(id);
		role.setStatus("I");

		this.setUpdateParams(role);

		roleRepository.save(role);

	}

	@Override
	public void activateAll() {
		roleRepository.activateAll();

	}

	@Override
	public void deactivateAll() {
		roleRepository.deactivateAll();
	}

	@Override
	public void deleteAllInActiveResources() {

		roleRepository.deleteAllInActiveResources();

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

	/**
	 * 
	 * @param source
	 * @return
	 */
//	private Role ListUpdateParams(Role source) {
//		source.setUpdatedProg(appName);
//		source.setUpdatedOn(LocalDateTime.now());
//
//		return source;
//	}

	/**
	 * 
	 * @param sortBy
	 * @param sortOrder
	 * @return
	 */
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
	public List<Role> findByStatus(String status) {

		return roleRepository.findByStatus(status);
	}

	@Override
	public List<Role> findByType(String type) {

		return roleRepository.findByType(RoleType.valueOf(type));
	}

	@Override
	public List<Role> findAllInActive() {

		return roleRepository.findByStatus("I");
	}

	@Override
	public List<Role> findAllActive() {

		return roleRepository.findByStatus("A");
	}

	@Override
	public List<Role> findRolesByUserId(Long user_id) {

		return roleRepository.findRoleByUserId(user_id);
	}

	@Override
	public void addTypeToRole(Long roleId, RoleType type) {
		Role role = roleRepository.findById(roleId)
				.orElseThrow(() -> new EntityNotFoundException(Messages.ROLE_NOT_FOUND));

		role.setType(type);
		roleRepository.save(role);

	}

}
