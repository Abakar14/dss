package com.bytmasoft.domain.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

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
import com.google.common.base.Objects;

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
	Set<Role> roles = new HashSet<>();

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
			@JoinColumn(name = "teacher_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "contact_person_id", referencedColumnName = "id") })
	private Set<ContactPerson> contactPersons = new HashSet<>();

	@JsonIgnore
	@ApiModelProperty(hidden = true)
	@XmlElement
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "student")
	private Set<FileDB> files = new HashSet<>();

	public void addFile(FileDB file) {
		this.files.add(file);
		file.setTeacher(this);

	}

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

	@Override
	public void generateUsername() {
		String toconcat = "";

		int day = LocalDateTime.now().getDayOfMonth();
		if (day < 10) {
			toconcat = "0" + day;
		} else {
			toconcat = "" + day;
		}
		this.setUsername("TE" + this.getLastName().substring(0, this.getLastName().length() - 1)
				.concat(this.getFirstName().substring(0, 1)).concat(toconcat).toUpperCase());
	}

	@Override
	public String toString() {

		return new StringJoiner(" | ", this.getClass().getSimpleName() + " [ ", "]").add("ID = " + this.getId())
				.add("firstname =" + this.getFirstName()).add("lastname = " + this.getLastName())
				.add("e-mail = " + this.getEmail()).toString();
	}

	@Override
	public boolean equals(Object obj) {
		return Objects.equal(this, obj);

	}

	@Override
	public int hashCode() {
		int hash = 31;
		return Objects.hashCode(hash, this.getEmail(), this.getId());
	}

}
