package com.bytmasoft.persistance.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bytmasoft.domain.models.Address;
import com.bytmasoft.persistance.repositories.AddressRepository;
import com.bytmasoft.persistance.service.interfaces.AddressService;
import com.bytmasoft.persistance.utils.EntityUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {

	private final AddressRepository repository;
	private final EntityUtils<Address> entityUtils;
	
	@Value("${spring.application.name}")
	private String appName;

	@Override
	public Address findOne(long id) {
		return repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("There is no user with this id: " + id));
	}

	@Override
	public List<Address> findAllActive() {
		return repository.findByStatus("A");
	}

	@Override
	public List<Address> findAllInActive() {

		return repository.findByStatus("I");
	}

	@Override
	public List<Address> findByCity(String city) {

		return repository.findByCity(city);
	}

	@Override
	public List<Address> findByStreet(String street) {

		return repository.findByStreet(street);
	}

	@Override
	public List<Address> findByPostalCode(String postalCode) {

		return repository.findByPostalCode(postalCode);
	}

	@Override
	public List<Address> findAllResources() {
		return (List<Address>) repository.findAll();
	}

	@Override
	public List<Address> findAllPaginatedAndSorted(int page, int size, String sortBy, String sortOrder) {

		Pageable pageable;

		if ("DESC".equals(sortOrder.toUpperCase())) {
			pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());

		} else {
			pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());

		}

		Page<Address> result = repository.findAll(pageable);

		if (result.hasContent()) {

			return result.getContent();
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public Address create(Address address) {
		
	boolean isExists = repository.findByCityAndPostalCodeAndStreetAndHauseNumber(address.getCity(), address.getPostalCode(),
				address.getStreet(), address.getHauseNumber()) != null ? true : false;

		if(isExists) {
			return address;
		}else {
			address.setInsertedProg(appName);
			return repository.save(address);
			
		}
	}

	@Override
	public void update(Address resource) {
		entityUtils.setUpdateParams(resource, appName);
		repository.save(resource);

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

		Address address = findOne(id);
		address.setStatus("A");
		this.entityUtils.setUpdateParams(address, appName);
		repository.save(address);

	}

	@Override
	public void deactivateById(long id) {
		Address address = findOne(id);
		address.setStatus("I");
		this.entityUtils.setUpdateParams(address, appName);
		repository.save(address);

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
		repository.deleteInActiveAddress();

	}

	@Override
	public Address findAddressByUserId(Long user_id) {
		return repository.findAddressByUserId(user_id);
	}

	

	@Override
	public void remerkForDelete(Long id) {

		Address a = this.findOne(id);
		if (a != null) {
			a.setDeletestatus(true);
			this.update(a);
		}

	}

	@Override
	public void remerkByStatusForDelete(String status) {

		this.findByStatus(status).forEach(a -> {
			a.setDeletestatus(true);
			update(a);
		});

	}

	@Override
	public List<Address> findByStatus(String status) {

		return repository.findByStatus(status);
	}

	

}
