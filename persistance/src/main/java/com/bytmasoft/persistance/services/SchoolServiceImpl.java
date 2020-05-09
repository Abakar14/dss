/**
 * @author Mahamat
 * Date 09.04.2020 : 18:31:46
 */
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

import com.bytmasoft.common.exception.MyEntityNotFoundException;
import com.bytmasoft.domain.enums.SchoolType;
import com.bytmasoft.domain.models.Address;
import com.bytmasoft.domain.models.BaseUser;
import com.bytmasoft.domain.models.School;
import com.bytmasoft.persistance.repositories.SchoolRepository;
import com.bytmasoft.persistance.service.interfaces.SchoolService;
import com.bytmasoft.persistance.utils.EntityUtils;

import lombok.RequiredArgsConstructor;

/**
 * @author Mahamat Date 09.04.2020 : 18:31:46
 */
@RequiredArgsConstructor
@Service
public class SchoolServiceImpl implements SchoolService {

	private final SchoolRepository repository;
	private final EntityUtils<School> entityUtils;
	
	@Value("${spring.application.name}")
	private String appName;

	@Override
	public School findOne(long id) {
		return repository.findById(id)
				.orElseThrow(() -> new MyEntityNotFoundException("There is no school with this id: " + id));

	}

	@Override
	public List<School> findAllResources() {

		return (List<School>) repository.findAll();
	}

	@Override
	public List<School> findAllInActive() {

		return repository.findByStatus("I");

	}

	@Override
	public List<School> findAllActive() {

		return repository.findByStatus("A");

	}

	@Override
	public List<School> findAllPaginatedAndSorted(int page, int size, String sortBy, String sortOrder) {
		Pageable pageable;

		if ("DESC".equals(sortOrder.toUpperCase())) {
			pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());

		} else {
			pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());

		}

		Page<School> result = repository.findAll(pageable);

		if (result.hasContent()) {

			return result.getContent();
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public School create(School resource) {

		boolean isExists = repository.findByName(resource.getName()) != null ? true : false;
		if(isExists) {
			return resource;
		}else {
			resource.setInsertedProg(appName);
			return repository.save(resource);
			
		}
	}

	@Override
	public void update(School resource) {

		repository.save(entityUtils.setUpdateParams(resource, appName));
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
	public void deleteAllInActiveResources() {

		repository.findByStatus("I").forEach(s -> {

			if (s.getDeletestatus().equals(true)) {
				this.deleteById(s.getId());
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
		School school = findOne(id);
		school.setStatus("A");
		this.entityUtils.setUpdateParams(school, appName);
		repository.save(school);

	}

	@Override
	public void deactivateById(long id) {

		School school = findOne(id);
		school.setStatus("I");

		repository.save(this.entityUtils
				.setUpdateParams(school, appName));

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
	public School findByName(String name) {

		return repository.findByName(name);

	}

	@Override
	public List<School> findByType(SchoolType type) {

		return repository.findByType(type);

	}

	@Override
	public List<BaseUser> findUsersBySchoolId(Long school_id) {
//		School school = findOne(school_id);
//		return school.getUsers();
		return null;

	}

	@Override
	public Address findAddressBySchoolId(Long school_id) {
		School school = findOne(school_id);
		return school.getAddress();

	}


	@Override
	public List<School> findSchoolsByUserId(Long id) {

		return repository.findSchoolsByUserId(id);
	}

	@Override
	public void remerkForDelete(Long id) {

		School s = this.findOne(id);
		if (s != null) {
			s.setDeletestatus(true);
			this.entityUtils.setUpdateParams(s, appName);

		}

	}

	@Override
	public List<School> findByStatus(String status) {
		return repository.findByStatus(status);
	}

	@Override
	public void remerkByStatusForDelete(String status) {
		findByStatus(status).forEach(c -> {
			c.setDeletestatus(true);
			this.update(c);
		});

	}




}
