package com.bytmasoft.domain.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class Teacher extends BaseUser {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2557847867716330648L;

	
	@Fetch(FetchMode.JOIN)
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "teacher_role", joinColumns = {
			@JoinColumn(name = "teacher_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id") })
	List<Role> roles = new ArrayList<>();
	
	@ManyToMany(mappedBy = "teachers")
	private List<ContactPerson> contactPersons;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "teacher_address", joinColumns = {
			@JoinColumn(name = "teacher_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "address_id", referencedColumnName = "id") })
	private List<Address> addresses = new ArrayList<>();

	@JsonProperty(value = "school")
	@Fetch(FetchMode.JOIN)
	@ManyToOne
	@JoinColumn(name = "school_id")
	private School school;
	
	
	public void addRole(Role role) {
		this.roles.add(role);
		
	}
	
	public void addAddress(Address address) {
		this.addresses.add(address);
		address.getTeachers().add(this);
	}
	
	public void RemoveRole(Role role) {
		roles.remove(role);
		role.getTeachers().remove(this);

	}

	
}
