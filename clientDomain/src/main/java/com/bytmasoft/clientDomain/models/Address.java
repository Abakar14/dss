/**
 * 
 */
package com.bytmasoft.clientDomain.models;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bytmasoft.clientDomain.enums.AddressType;
import com.bytmasoft.clientDomain.model.interfaces.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Mahamat Abakar Date 13.12.2019
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
@XmlRootElement
public class Address extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String street;
	private String city;
	private String postalCode;
	private String hauseNumber;


	private AddressType type;

	@JsonProperty(value = "country")
	private Country country;

	
	private Set<Student> students = new HashSet<>();
	
	private Set<Manager> managers = new HashSet<>();
	
	
	private Set<Teacher> teachers = new HashSet<>();
	
	
	private Set<ContactPerson> contactPersons = new HashSet<>();;
	
	
	private Set<Employee> employees = new HashSet<>();

	@JsonIgnore	
	@XmlElement	
	private School school;

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Address [id=").append(this.getId()).append(", country=").append(this.getCountry())
				.append(", city=").append(this.getCity()).append(", street=").append(this.getStreet())
				.append(", hausnumber=").append(this.getHauseNumber()).append(", postalcode=")
				.append(this.getPostalCode()).append(", status=").append(this.getStatus()).append(", type=")
				.append(this.getType()).append("]");

		return builder.toString();
	}

	@Override
	public int hashCode() {

		final int primeNumber = 31;
		int resutl = 1;
		resutl = (primeNumber * resutl) + ((street == null) ? 0 : street.hashCode());
		return resutl;

	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {

			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		final Address address = (Address) obj;
		if (!street.equals(address.getStreet())) {
			return false;
		}

		return true;

	}

}
