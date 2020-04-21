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
import com.bytmasoft.domain.models.Country;
import com.bytmasoft.persistance.interfaces.CountryService;
import com.bytmasoft.persistance.repositories.CountryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CountryServiceImpl implements CountryService {

	private final CountryRepository countryRepository;

	@Value("${spring.application.name}")
	private String appName;

	@Override
	public Country findOne(long id) {
		return countryRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("There is no country with this id : " + id));
	}

	@Override
	public Country findByName(String name) {
		return countryRepository.findByName(name)
				.orElseThrow(() -> new EntityNotFoundException("There is no country with this name : " + name));
	}

	@Override
	public List<Country> findAllResources() {
		return (List<Country>) countryRepository.findAll();
	}

	@Override
	public List<Country> findAllInActive() {
		return countryRepository.findAllInActiveCountries();
	}

	@Override
	public List<Country> findAllActive() {
		return countryRepository.findAllActiveCountries();

	}

	@Override
	public List<Country> findAllPaginatedAndSorted(int page, int size, String sortBy, String sortOrder) {
		Pageable pageable;

		if ("DESC".equals(sortOrder.toUpperCase())) {
			pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());

		} else {
			pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());

		}
		Page<Country> result = countryRepository.findAll(pageable);

		if (result.hasContent()) {
			return result.getContent();
		} else {
			return new ArrayList<Country>();
		}
	}

	@Override
	public Country create(Country resource) {

//		if(resource.getUsers() != null) {
//			resource.getUsers().forEach(u -> u.setInsertedBy(appName));
//		}

		resource.setInsertedProg(appName);
		return countryRepository.save(resource);
	}

	@Override
	public void update(Country resource) {

		countryRepository.save(setUpdateParams(resource));

	}

	@Override
	public void deleteById(long id) {
		countryRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		countryRepository.deleteAll();
	}

	@Override
	public long count() {
		return countryRepository.count();
	}

	@Override
	public long countActiveResources() {

		return countryRepository.countActiveResources();
	}

	@Override
	public long countInActiveResources() {

		return countryRepository.countAllInActiveUsers();
	}

	@Override
	public void activateById(long id) {

		Country country = findOne(id);
		country.setStatus("A");
		this.setUpdateParams(country);
		countryRepository.save(country);

	}

	@Override
	public void deactivateById(long id) {
		Country country = findOne(id);
		country.setStatus("I");
		this.setUpdateParams(country);
		countryRepository.save(country);

	}

	@Override
	public void activateAll() {
		countryRepository.activateAll();

	}

	@Override
	public void deactivateAll() {
		countryRepository.deactivateAll();

	}

	@Override
	public void deleteAllInActiveResources() {
		countryRepository.deleteInActiveCountry();

	}

//	@Override
//	public Country findCountryByUserId(long user_id){
//		return countryRepository.findCountryByUserId(user_id);
//	}

//	@Override
//	public Country findCountryByUser(User user){
//		return findCountryByUserId(user.getId());
//	}

	/**
	 * @param resource
	 */
	@Override
	public Country setUpdateParams(Country resource) {
		resource.setUpdatedProg(appName);
		resource.setUpdatedOn(LocalDateTime.now());
		return resource;

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
	public void assignAddressToCountry(Long country_id, Long address_id) {
		countryRepository.assignAddressToCountry(country_id, address_id);

	}

//	@Override
//	public Country findCountryByUser(User user) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
