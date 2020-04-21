package com.bytmasoft.persistance.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bytmasoft.common.exception.EntityNotFoundException;
import com.bytmasoft.domain.models.Privilege;
import com.bytmasoft.persistance.interfaces.PrivilegeService;
import com.bytmasoft.persistance.repositories.PrivilegeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrivilegeServiceImpl implements PrivilegeService {

	private final PrivilegeRepository privilegeRepository;

	@Value("${spring.application.name}")
	private String appName;

	/**
	 * @param userRepository2
	 * @param rightsRepository2
	 */
//	public PrivilegeServiceImpl(UserRepository userRepository, RoleRepository rightsRepository, PrivilegeRepository privilegeRepository) {
//		this.userRepository = userRepository;
//		this.rightsRepository = rightsRepository;
//		this.privilegeRepository = privilegeRepository;
//	}

	@Override
	public Privilege findOne(long id) {
		return privilegeRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("There is no privilege with this id: " + id));

	}

	@Override
	public Privilege findByName(String name) {
		return privilegeRepository.findByName(name);
	}

	@Override
	public List<Privilege> findAllResources() {
		return (List<Privilege>) privilegeRepository.findAll();
	}

	@Override
	public List<Privilege> findAllActive() {
		return privilegeRepository.findActivePrivileges();
	}

	@Override
	public List<Privilege> findPrivilegesByRoleId(Long role_id) {

		return privilegeRepository.findPrivilegeByRoleId(role_id);
	}

	@Override
	public List<Privilege> findAllInActive() {

		return privilegeRepository.findInActivePrivileges();
	}

	@Override
	public List<Privilege> findAllPaginatedAndSorted(int page, int size, String sortBy, String sortOrder) {
		Pageable pageable;

		if ("DESC".equals(sortOrder.toUpperCase())) {
			pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());

		} else {
			pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());

		}

		Page<Privilege> result = privilegeRepository.findAll(pageable);

		if (result.hasContent()) {
			return result.getContent();
		} else {
			return new ArrayList<Privilege>();
		}
	}

	@Override
	public Privilege create(Privilege group) {

//		if(group.getUsers() != null) {
//			
//			group.getUsers().forEach(u -> u.setInsertedBy(appName));
//		}
//		
//		if(group.getRights() !=null) {
//			group.getRights().forEach(r -> r.setInsertedProg(appName));
//		}

		group.setInsertedProg(appName);
		return privilegeRepository.save(group);
	}

	@Override
	public void update(Privilege privilege) {

		privilegeRepository.save(setUpdateParams(privilege));

	}

	@Override
	public void deleteById(long id) {

		privilegeRepository.deleteById(id);

	}

	@Override
	public void deleteAll() {
		privilegeRepository.deleteAll();

	}

	@Override
	public long count() {
		return privilegeRepository.count();
	}

	@Override
	public long countActiveResources() {

		return privilegeRepository.countActivePrivileges();
	}

	@Override
	public long countInActiveResources() {
		return privilegeRepository.countInActivePrivilege();
	}

	@Override
	public void activateById(long id) {

		Privilege group = findOne(id);
		group.setStatus("A");
		this.setUpdateParams(group);
		privilegeRepository.save(group);

	}

	@Override
	public void deactivateById(long id) {
		Privilege group = findOne(id);
		group.setStatus("I");
		this.setUpdateParams(group);
		privilegeRepository.save(group);

	}

	@Override
	public void activateAll() {
		privilegeRepository.activateAll();

	}

	@Override
	public void deactivateAll() {
		privilegeRepository.deactivateAll();

	}

	@Override
	public void deleteAllInActiveResources() {
		privilegeRepository.deleteInActiveUsers();

	}

//	@Override
//	public void assignUserToGroup(Long user_id, String groupName) {
//
//		Privilege group = groupRepository.findByName(groupName);
//
//		User user = userRepository.findById(user_id)
//				.orElseThrow(() -> new EntityNotFoundException("User by Id : " + user_id + " not found"));
//			
////			group.addUser(user);
//			groupRepository.save(group);
//			
//	}
//
//	@Override
//	public void assignRightToGroup(Long group_id, Long rights_id) {
//		groupRepository.assignRightsToGroup(group_id, rights_id);
//
//	}
//
//	@Override
//	public void assignRightToGroup(String groupName, String rightName) {
//		
//		Privilege	group = groupRepository.findByName(groupName);
//		
//		
//		
//			Long rights_id = rightsRepository.findByName(rightName).getId();
//			
//			groupRepository.assignRightsToGroup(group.getId(), rights_id);
//			
//		
//	}
//
//
//
//	@Override
//	public Privilege findGroupByUserIdAndGroupId(Long user_id, Long group_id) {
//		return groupRepository.findGroupByUserIdAndGroupId(user_id, group_id);
//	}
//
//	@Override
//	public List<Privilege> findAllGroupsByUserId(Long user_id) {
//		return groupRepository.findAllGroupsByUserId(user_id);
//	}

	@Override
	public Privilege setUpdateParams(Privilege source) {
		source.setUpdatedProg(appName);
		source.setUpdatedOn(LocalDateTime.now());
		return source;
	}

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
	public Privilege findByRequestParams(Map<String, String> requestParams) {

		String groupname = "";

		if (requestParams.containsKey("name")) {
			groupname = requestParams.get("name");
		}

		if (!groupname.isEmpty()) {

			return findByName(groupname);

		}

		return null;

	}

}
