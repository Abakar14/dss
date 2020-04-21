package com.bytmasoft.persistance.interfaces;

import com.bytmasoft.common.interfaces.IOperations;
import com.bytmasoft.domain.models.Country;

public interface CountryService extends IOperations<Country> {

	/**
	 * @param name
	 * @return
	 */
	Country findByName(String name);

	/**
	 * @param user_id
	 * @return
	 */
//	Country findCountryByUserId(long user_id);

	/**
	 * @param user
	 * @return
	 */
//	Country findCountryByUser(User user);

	/**
	 * @param country_id
	 * @param address_id
	 */
	void assignAddressToCountry(Long country_id, Long address_id);

}
