package com.bytmasoft.domain.models;


import java.util.HashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class Manager extends BaseUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 206042356897421964L;
	@Fetch(FetchMode.JOIN)
//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@ManyToMany
	@JoinTable(name = "manager_role", joinColumns = {
			@JoinColumn(name = "manager_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id") })
	Set<Role> roles = new HashSet<>();
	
	
//	@JsonIgnore
//	@ManyToMany
	@ManyToMany(fetch = FetchType.LAZY,  cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "manager_address", joinColumns = {
			@JoinColumn(name = "manager_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "address_id", referencedColumnName = "id") })
	private Set<Address> addresses = new HashSet<>();
	
	
	@JsonIgnore
	@ApiModelProperty(hidden = true)
	@XmlElement
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "manager")
	private Set<School> schools = new HashSet<>();
			
	
//	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@ManyToMany
	@JoinTable(name = "student_contact_person", joinColumns = {
			@JoinColumn(name = "contact_person_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "student_id", referencedColumnName = "id") })
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
