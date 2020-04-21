package com.bytmasoft.persistance.interfaces;

import java.util.List;

import com.bytmasoft.common.interfaces.IOperations;
import com.bytmasoft.domain.models.Address;

public interface AddressService extends IOperations<Address> {

	/**
	 * 
	 * @param street
	 * @return List of {@link Address}
	 */
	public List<Address> findByStreet(String street);

	/**
	 * 
	 * @param city
	 * @return List of {@link Address}
	 */
	public List<Address> findByCity(String city);

	/**
	 * 
	 * @param postalCode
	 * @return List of {@link Address}
	 */
	public List<Address> findByPostalCode(String postalCode);

	/**
	 * @param user_id
	 * @return
	 */
	Address findAddressByUserId(Long user_id);

}
