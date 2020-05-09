package com.bytmasoft.domain.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@ManyToMany
	@JoinTable(name = "teacher_role", joinColumns = {
			@JoinColumn(name = "teacher_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id") })
	Set<Role> roles =new HashSet<>();

	@ManyToMany(mappedBy = "teachers")
	private Set<School> schools = new HashSet<>();

	@JsonIgnore
//	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@ManyToMany
	@JoinTable(name = "teacher_address", joinColumns = {
			@JoinColumn(name = "teacher_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "address_id", referencedColumnName = "id") })
	private Set<Address> addresses = new HashSet<>();

	@JsonIgnore
//	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@ManyToMany
	@JoinTable(name = "teacher_course", joinColumns = {
			@JoinColumn(name = "teacher_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "course_id", referencedColumnName = "id") })
	private Set<Course> courses = new HashSet<>();

//	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@ManyToMany
	@JoinTable(name = "teacher_contact_person", joinColumns = {
			@JoinColumn(name = "contact_person_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "teacher_id", referencedColumnName = "id") })
	private Set<ContactPerson> contactPersons = new HashSet<>();

	/**
	 * 
	 * @param role
	 */
	public void addRole(Role role) {
		this.roles.add(role);
		role.getTeachers().add(this);
	}

	/**
	 * 
	 * @param role
	 */
	public void romveRole(Role role) {
		this.roles.remove(role);
		role.getTeachers().remove(this);
	}

	
	public void addCourse(Course course) {

		this.courses.add(course);
		course.getTeachers().add(this);
	}

	public void removeCourse(Course course) {

		this.courses.remove(course);
		course.getTeachers().remove(this);
	}

	/**
	 * 
	 * @param address
	 */
	public void addAddress(Address address) {
		this.addresses.add(address);
		address.getTeachers().add(this);
	}

	/**
	 * 
	 * @param address
	 */
	public void romveAddress(Address address) {
		this.addresses.remove(address);
		address.getTeachers().remove(this);
	}

	/**
	 * 
	 * @param contactPerson
	 */
	public void addContactPerson(ContactPerson contactPerson) {

		this.contactPersons.add(contactPerson);
		contactPerson.getTeachers().add(this);
	}

	/**
	 * 
	 * @param contactPerson
	 */
	public void romveContactPerson(ContactPerson contactPerson) {
		this.contactPersons.remove(contactPerson);
		contactPerson.getTeachers().remove(this);
	}

}
