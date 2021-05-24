package com.bytmasoft.clientDomain.models;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import com.bytmasoft.clientDomain.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class ContactPerson extends BaseUser {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -787584079024966039L;
	
	
	
//	@JsonIgnore
//	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	
	private Set<Address> addresses = new HashSet<>();
	
	
	
	
	private Set<Manager> managers = new HashSet<>();
	
	
	private Set<Teacher> teachers = new HashSet<>();
	
	
	private Set<Employee> employees;
	
	
//	private Set<Student> students = new HashSet<>();

	
	@Override
	public void setType(UserType type) {
		super.setType(type);
	}
		
	public void addAddress(Address address) {
		this.addresses.add(address);
		address.getContactPersons().add(this);
	}
	
	public void removeAddress(Address address) {
		this.addresses.remove(address);
		address.getContactPersons().remove(this);
	}
	
		

}
