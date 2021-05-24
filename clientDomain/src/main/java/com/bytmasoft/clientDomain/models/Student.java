package com.bytmasoft.clientDomain.models;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement
public class Student extends BaseUser {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8606469960515931889L;


	
	private Set<Role> roles = new HashSet<>();
		
//	@JsonIgnore	
	private Set<Address> addresses = new HashSet<>();
	
	
//	@JsonIgnore
	private Set<Course> courses = new HashSet<>();
	
	
	
	private Set<ContactPerson> contactPersons = new HashSet<>();
	
//	@JsonProperty(value = "profile_picture")
//	private byte [] profile_picture;
	
	/**
	 * 
	 * @param role
	 */
	public void addRole(Role role) {
		this.roles.add(role);
		role.getStudents().add(this);
	}
	
	/**
	 * 
	 * @param role
	 */
	public void romveRole(Role role) {
		this.roles.remove(role);
		role.getStudents().remove(this);
	}
	
	/**
	 * 
	 * @param address
	 */
	public void addAddress(Address address) {
		this.addresses.add(address);
//		address.getStudents().add(this);
	}
	
	/**
	 * 
	 * @param address
	 */
	public void removeAddress(Address address) {
		this.addresses.remove(address);
//		address.getStudents().remove(this);
	}
	
	/**
	 * 
	 * @param course
	 */
	public void addCourse(Course course) {
		this.courses.add(course);
		course.getStudents().add(this);
	}
	
	/**
	 * 
	 * @param course
	 */
	public void romveCourse(Course course) {
		this.courses.remove(course);
		course.getStudents().remove(this);
	}
	
	/**
	 * 
	 * @param contactPerson
	 */
	 public void addContactPerson(ContactPerson contactPerson) {
	 
		this.contactPersons.add(contactPerson);
//		contactPerson.getStudents().add(this);
	}
	
	 /**
	  * 
	  * @param contactPerson
	  */
	public void romveContactPerson(ContactPerson contactPerson) {
		this.contactPersons.remove(contactPerson);
//		contactPerson.getStudents().remove(this);
	}
	
	
	

}
