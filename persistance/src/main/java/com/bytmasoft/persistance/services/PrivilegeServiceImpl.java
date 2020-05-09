package com.bytmasoft.persistance.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bytmasoft.common.exception.MyEntityNotFoundException;
import com.bytmasoft.domain.models.Address;
import com.bytmasoft.domain.models.Privilege;
import com.bytmasoft.persistance.repositories.PrivilegeRepository;
import com.bytmasoft.persistance.service.interfaces.PrivilegeService;
import com.bytmasoft.persistance.utils.EntityUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrivilegeServiceImpl implements PrivilegeService {

	private final PrivilegeRepository repository;
	private final EntityUtils<Privilege> entityUtils;

	@Value("${spring.application.name}")
	private String appName;

	@Override
	public Privilege findOne(long id) {
		return repository.findById(id)
				.orElseThrow(() -> new MyEntityNotFoundException("There is no privilege with this id: " + id));

	}

	@Override
	public Privilege findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public List<Privilege> findAllResources() {
		return (List<Privilege>) repository.findAll();
	}

	@Override
	public List<Privilege> findAllActive() {
		return repository.findActivePrivileges();
	}

	@Override
	public Set<Privilege> findPrivilegesByRoleId(Long role_id) {

		return repository.findPrivilegeByRoleId(role_id);
	}

	@Override
	public List<Privilege> findAllInActive() {

		return repository.findInActivePrivileges();
	}

	@Override
	public List<Privilege> findAllPaginatedAndSorted(int page, int size, String sortBy, String sortOrder) {
		Pageable pageable;

		if ("DESC".equals(sortOrder.toUpperCase())) {
			pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());

		} else {
			pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());

		}

		Page<Privilege> result = repository.findAll(pageable);

		if (result.hasContent()) {
			return result.getContent();
		} else {
			return new ArrayList<Privilege>();
		}
	}

	@Override
	public Privilege create(Privilege privilege) {

			privilege.setInsertedProg(appName);
			return repository.save(privilege);		

	}

	@Override
	public void update(Privilege privilege) {

		repository.save(entityUtils.setUpdateParams(privilege, appName));

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
	public void activateById(long id) {

		Privilege p = findOne(id);
		p.setStatus("A");
		this.entityUtils.setUpdateParams(p, appName);
		repository.save(p);

	}

	@Override
	public void deactivateById(long id) {
		Privilege p = findOne(id);
		p.setStatus("I");
		this.entityUtils.setUpdateParams(p, appName);
		repository.save(p);

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
		repository.deleteInActiveUsers();

	}
	
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

	@Override
	public void remerkForDelete(Long id) {
	
		Privilege p = this.findOne(id);
		
		if(p != null) {
			p.setDeletestatus(true);
			this.update(p);
		}
	}

	@Override
	public List<Privilege> findByStatus(String status) {
		return repository.findByStatus(status);
	}

	@Override
	public void remerkByStatusForDelete(String status) {
		findByStatus(status).forEach(p -> {
			p.setDeletestatus(true);
			this.update(p);
		});
		
		
	}

	
	@Override
	public Set<Privilege> findPrivilegesByUserId(Long id) {
		
		return repository.findPrivilegesByUserId(id);
	}

	

}
