package com.bytmasoft.um.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bytmasoft.domain.models.Address;
import com.bytmasoft.domain.models.Country;
import com.bytmasoft.persistance.service.interfaces.AddressService;
import com.bytmasoft.persistance.service.interfaces.CountryService;
import com.bytmasoft.um.utils.UmMapping;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author Mahamat Abakar Date 23.10.2019
 */
//@Slf4j
@Api(value = "Address management")
@RestController
@RequiredArgsConstructor
@RequestMapping(produces = { "application/json", "application/xml" }, consumes = { "application/json",
		"application/xml" })
public class AddressController {

	// TODO Mahamat 04.04.2020 21:12:02
	// integration Test
	private final AddressService addressService;

	private final CountryService countryService;

	@GetMapping(UmMapping.ADDRESSES + "/{id}")
	Address findAddressById(@PathVariable("id") long id) throws EntityNotFoundException {

		return addressService.findOne(id);

	}

	@GetMapping(UmMapping.ADDRESSES)
	List<Address> getAddresses() {
		return addressService.findAllResources();
	}

	@PostMapping(UmMapping.ADDRESSES)
	@ResponseStatus(HttpStatus.CREATED)
	public Address createAddress(@RequestBody Address address) {
		return addressService.create(address);
	}

	@PutMapping(UmMapping.ADDRESSES + "/{address_id}")
	public void upateAddress(@RequestBody Address address, @PathVariable("address_id") long address_id) {
		address.setId(address_id);
		addressService.update(address);
	}

	@GetMapping(UmMapping.countries + "/{id}")
	Country findGroupById(@PathVariable("id") long id) throws EntityNotFoundException {
		return countryService.findOne(id);

	}

	@GetMapping(UmMapping.countries)
	List<Country> getCountries() {
		return countryService.findAllResources();
	}

	@PutMapping(UmMapping.countries + "{country_id}/addresses/{address_id}")
	void assignAddressToCountry(@PathVariable Long country_id, @PathVariable Long address_id) {

		countryService.assignAddressToCountry(country_id, address_id);

	}

	@PostMapping(UmMapping.countries)
	@ResponseStatus(HttpStatus.CREATED)
	public Country createCountry(@RequestBody Country country) {
		return countryService.create(country);
	}
}
