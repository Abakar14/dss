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
import com.bytmasoft.domain.models.Address;
import com.bytmasoft.domain.models.Country;
import com.bytmasoft.persistance.interfaces.CountryService;
import com.bytmasoft.persistance.repositories.CountryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CountryServiceImpl implements CountryService {

	private final CountryRepository repository;

	@Value("${spring.application.name}")
	private String appName;

	@Override
	public Country findOne(long id) {
		return repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("There is no country with this id : " + id));
	}

	@Override
	public Country findByName(String name) {
		return repository.findByName(name)
				.orElseThrow(() -> new EntityNotFoundException("There is no country with this name : " + name));
	}

	@Override
	public List<Country> findAllResources() {
		return (List<Country>) repository.findAll();
	}

	@Override
	public List<Country> findAllInActive() {
		return repository.findByStatus("I");
	}

	@Override
	public List<Country> findAllActive() {
		return repository.findByStatus("A");

	}

	@Override
	public List<Country> findAllPaginatedAndSorted(int page, int size, String sortBy, String sortOrder) {
		Pageable pageable;

		if ("DESC".equals(sortOrder.toUpperCase())) {
			pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());

		} else {
			pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());

		}
		Page<Country> result = repository.findAll(pageable);

		if (result.hasContent()) {
			return result.getContent();
		} else {
			return new ArrayList<Country>();
		}
	}

	@Override
	public Country create(Country resource) {

	  Country c = this.CreateIfNotExists(resource);
	  if(c == null) {
		  resource.setInsertedProg(appName);
		  return repository.save(resource);
		  
	  }else {
		  return c;
	  }
	}

	@Override
	public void update(Country resource) {

		repository.save(setUpdateParams(resource));

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

		Country country = findOne(id);
		country.setStatus("A");
		this.setUpdateParams(country);
		repository.save(country);

	}

	@Override
	public void deactivateById(long id) {
		Country country = findOne(id);
		country.setStatus("I");
		this.setUpdateParams(country);
		repository.save(country);

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
		repository.deleteInActiveCountry();

	}

	/**
	 * @param resource
	 */
	@Override
	public Country setUpdateParams(Country resource) {
		resource.setUpdatedProg(appName);
		resource.setUpdatedOn(LocalDateTime.now());
		return resource;

	}



	@Override
	public void assignAddressToCountry(Long country_id, Long address_id) {
		repository.assignAddressToCountry(country_id, address_id);

	}

	@Override
	public void remerkForDelete(Long id) {
		
		Country c = this.findOne(id);
		if(c != null) {
			c.setDeletestatus(true);
			this.update(c);
		}
		
	}

	@Override
	public List<Country> findByStatus(String status) {
	
		return repository.findByStatus(status);
	}

	@Override
	public void remerkByStatusForDelete(String status) {
		findByStatus(status).forEach(c -> {
			c.setDeletestatus(true);
			this.update(c);
		});
		
	}

	@Override
	public Country CreateIfNotExists(Country country) {
		return findByName(country.getName());		
	}


}
