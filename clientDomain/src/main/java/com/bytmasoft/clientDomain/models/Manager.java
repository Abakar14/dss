package com.bytmasoft.clientDomain.models;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class Manager extends BaseUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 206042356897421964L;

	Set<Role> roles = new HashSet<>();

	private Set<Address> addresses = new HashSet<>();

	@JsonIgnore
	private Set<School> schools = new HashSet<>();

	private Set<ContactPerson> contactPersons = new HashSet<ContactPerson>();

	/**
	 * 
	 * @param role
	 */
	public void addRole(Role role) {
		this.roles.add(role);
		role.getManagers().add(this);
	}

	/**
	 * 
	 * @param role
	 */
	public void romveRole(Role role) {
		this.roles.remove(role);
		role.getManagers().remove(this);
	}

	/**
	 * 
	 * @param address
	 */
	public void addAddress(Address address) {
		this.addresses.add(address);
		address.getManagers().add(this);
	}

	/**
	 * 
	 * @param address
	 */
	public void romveAddress(Address address) {
		this.addresses.remove(address);
		address.getManagers().remove(this);
	}

	public void addSchool(School school) {
		this.schools.add(school);
		school.setManager(this);
	}

	public void removeSchool(School school) {
		this.schools.remove(school);
		school.setManager(null);
	}

	/**
	 * 
	 * @param contactPerson
	 */
	public void addContactPerson(ContactPerson contactPerson) {

		this.contactPersons.add(contactPerson);
		contactPerson.getManagers().add(this);
	}

	/**
	 * 
	 * @param contactPerson
	 */
	public void romveContactPerson(ContactPerson contactPerson) {
		this.contactPersons.remove(contactPerson);
		contactPerson.getManagers().remove(this);
	}

}
