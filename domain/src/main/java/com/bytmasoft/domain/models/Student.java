package com.bytmasoft.domain.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
public class Student extends BaseUser {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8606469960515931889L;


	@Fetch(FetchMode.JOIN)
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "student_role", joinColumns = {
			@JoinColumn(name = "student_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id") })
	private Set<Role> roles = new HashSet<>();
		
//	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL )
	@JoinTable(name = "student_address", joinColumns = {
			@JoinColumn(name = "student_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "address_id", referencedColumnName = "id") })
	private Set<Address> addresses = new HashSet<>();
	
	
//	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "student_course", joinColumns = {
			@JoinColumn(name = "student_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "course_id", referencedColumnName = "id") })
	private Set<Course> courses = new HashSet<>();
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "student_contact_person", joinColumns = {
			@JoinColumn(name = "student_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "contact_person_id", referencedColumnName = "id") })
	private Set<ContactPerson> contactPersons = new HashSet<>();
	
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
		address.getStudents().add(this);
	}
	
	/**
	 * 
	 * @param address
	 */
	public void removeAddress(Address address) {
		this.addresses.remove(address);
		address.getStudents().remove(this);
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
		contactPerson.getStudents().add(this);
	}
	
	 /**
	  * 
	  * @param contactPerson
	  */
	public void romveContactPerson(ContactPerson contactPerson) {
		this.contactPersons.remove(contactPerson);
		contactPerson.getStudents().remove(this);
	}
	
	
	

}
