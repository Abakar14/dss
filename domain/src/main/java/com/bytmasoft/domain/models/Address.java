/**
 * 
 */
package com.bytmasoft.domain.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bytmasoft.domain.enums.AddressType;
import com.bytmasoft.domain.model.interfaces.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.swagger.annotations.ApiModelProperty;
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
@Entity
public class Address extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String street;
	private String city;
	private String postalCode;
	private String hauseNumber;

	@Enumerated(EnumType.STRING)
	private AddressType type;

	@JsonProperty(value = "country")
//	@Fetch(FetchMode.JOIN)
//	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@ManyToOne
	@JoinColumn(name = "country_id_fk")
	private Country country;

	@ManyToMany(mappedBy = "addresses")
	private Set<Student> students = new HashSet<>();
	
	@ManyToMany(mappedBy = "addresses")
	private Set<Manager> managers = new HashSet<>();
	
	@ManyToMany(mappedBy = "addresses")
	private Set<Teacher> teachers = new HashSet<>();
	
	@ManyToMany(mappedBy = "addresses")
	private Set<ContactPerson> contactPersons = new HashSet<>();;
	
	@ManyToMany(mappedBy = "addresses")
	private Set<Employee> employees = new HashSet<>();

	@JsonIgnore
	@ApiModelProperty(hidden = true)
	@XmlElement
	@OneToOne(mappedBy = "address")
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
