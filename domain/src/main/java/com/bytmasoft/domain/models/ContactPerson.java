package com.bytmasoft.domain.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;

import com.bytmasoft.domain.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
public class ContactPerson extends BaseUser {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -787584079024966039L;
	
	
	
//	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
//	@JoinTable(name = "student_contact_persons", joinColumns = {
//			@JoinColumn(name = "contact_persion_id", referencedColumnName = "id") }, inverseJoinColumns = {
//					@JoinColumn(name = "student_id", referencedColumnName = "id") })
//	List<Student> students = new ArrayList<Student>();
	

		
//	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
//	@JoinTable(name = "manager_contact_persons", joinColumns = {
//			@JoinColumn(name = "contact_persion_id", referencedColumnName = "id") }, inverseJoinColumns = {
//					@JoinColumn(name = "manager_id", referencedColumnName = "id") })
//	List<Manager> managers = new ArrayList<Manager>();
	
//	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
//	@JoinTable(name = "employee_contact_persons", joinColumns = {
//			@JoinColumn(name = "contact_persion_id", referencedColumnName = "id") }, inverseJoinColumns = {
//					@JoinColumn(name = "employee_id", referencedColumnName = "id") })
//	List<Employee> employees = new ArrayList<Employee>();
	
//	@JsonIgnore
//	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@ManyToMany
	@JoinTable(name = "contact_person_address", joinColumns = {
			@JoinColumn(name = "contact_persion_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "address_id", referencedColumnName = "id") })
	private Set<Address> addresses = new HashSet<>();
	
	
	
	@ManyToMany(mappedBy = "contactPersons")
	private Set<Manager> managers = new HashSet<>();
	
	@ManyToMany(mappedBy = "contactPersons")
	private Set<Teacher> teachers = new HashSet<>();
	
	@ManyToMany(mappedBy = "contactPersons")
	private Set<Employee> employees;
	
	@ManyToMany(mappedBy = "contactPersons")
	private Set<Student> students = new HashSet<>();

	
	@Override
	public void setType(UserType type) {
		
		super.setType(null);
		this.setType(null);
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
