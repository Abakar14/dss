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
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

	@JsonIgnore
	@ApiModelProperty(hidden = true)
	@XmlElement
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "student")
	private Set<FileDB> files = new HashSet<>();

	public void addFile(FileDB file) {
		this.files.add(file);
		file.setStudent(this);

	}

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

	@Override
	public String generateUsername() {

		String toconcat = "";

		int day = LocalDateTime.now().getDayOfMonth();
		if (day < 10) {
			toconcat = "0" + day;
		} else {
			toconcat = "" + day;
		}
		return "ST" + this.getLastName().substring(0, this.getLastName().length() - 1)
				.concat(this.getFirstName().substring(0, 1)).concat(toconcat).toUpperCase();

	}

	@Override
	public String toString() {

		return new StringJoiner("; ", this.getClass().getSimpleName() + " [", "]").add("id = " + this.getId())
				.add("firstname =" + this.getFirstName()).add("lastname = " + this.getLastName())
				.add("e-mail = " + this.getEmail()).add("status" + this.getStatus()).add("type" + this.getType())
				.toString();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Student) {
			Student student = (Student) obj;
			return Objects.equal(this.getId(), student.getId()) && Objects.equal(this.getEmail(), student.getEmail());
		}
		return false;
	}

	@Override
	public int hashCode() {

		return Objects.hashCode(this.getId(), this.getEmail());

	}

}
