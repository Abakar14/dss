package com.bytmasoft.persistance.services;

import java.time.LocalDateTime;
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
import com.bytmasoft.persistance.interfaces.AddressService;
import com.bytmasoft.persistance.repositories.AddressRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {

	private final AddressRepository addressRepository;

	@Value("${spring.application.name}")
	private String appName;

	@Override
	public Address findOne(long id) {
		return addressRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("There is no user with this id: " + id));
	}

	@Override
	public List<Address> findAllActive() {
		return addressRepository.findActiveAddresses();
	}

	@Override
	public List<Address> findAllInActive() {

		return addressRepository.findInActiveAddresses();
	}

	@Override
	public List<Address> findByCity(String city) {

		return addressRepository.findByCity(city);
	}

	@Override
	public List<Address> findByStreet(String street) {

		return addressRepository.findByStreet(street);
	}

	@Override
	public List<Address> findByPostalCode(String postalCode) {

		return addressRepository.findByPostalCode(postalCode);
	}

	@Override
	public List<Address> findAllResources() {
		return (List<Address>) addressRepository.findAll();
	}

	@Override
	public List<Address> findAllPaginatedAndSorted(int page, int size, String sortBy, String sortOrder) {

		Pageable pageable;

		if ("DESC".equals(sortOrder.toUpperCase())) {
			pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());

		} else {
			pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());

		}

		Page<Address> result = addressRepository.findAll(pageable);

		if (result.hasContent()) {

			return result.getContent();
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public Address create(Address resource) {

		resource.setInsertedProg(appName);
		return addressRepository.save(resource);
	}

	@Override
	public void update(Address resource) {
		setUpdateParams(resource);
		addressRepository.save(resource);

	}

	@Override
	public void deleteById(long id) {
		addressRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		addressRepository.deleteAll();
	}

	@Override
	public long count() {
		return addressRepository.count();
	}

	@Override
	public long countActiveResources() {

		return addressRepository.countActiveResources();
	}

	@Override
	public long countInActiveResources() {

		return addressRepository.countInActiveAddresses();
	}

	@Override
	public void activateById(long id) {

		Address country = findOne(id);
		country.setStatus("A");
		this.setUpdateParams(country);
		addressRepository.save(country);

	}

	@Override
	public void deactivateById(long id) {
		Address country = findOne(id);
		country.setStatus("I");
		this.setUpdateParams(country);
		addressRepository.save(country);

	}

	@Override
	public void activateAll() {
		addressRepository.activateAll();

	}

	@Override
	public void deactivateAll() {
		addressRepository.deactivateAll();

	}

	@Override
	public void deleteAllInActiveResources() {
		addressRepository.deleteInActiveAddress();

	}

	@Override
	public Address findAddressByUserId(Long user_id) {
		return addressRepository.findAddressByUserId(user_id);
	}

	/**
	 * @param resource
	 */
	@Override
	public Address setUpdateParams(Address resource) {
		resource.setUpdatedProg(appName);
		resource.setUpdatedOn(LocalDateTime.now());
		return resource;

	}

}
