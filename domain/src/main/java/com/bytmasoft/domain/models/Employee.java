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

import org.apache.commons.lang3.StringUtils;
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
public class Employee extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7118013499884529614L;

	@Fetch(FetchMode.JOIN)
//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@ManyToMany
	@JoinTable(name = "employee_role", joinColumns = {
			@JoinColumn(name = "employee_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id") })
	private Set<Role> roles = new HashSet<>();

	@JsonIgnore
//	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@ManyToMany
	@JoinTable(name = "employee_address", joinColumns = {
			@JoinColumn(name = "employee_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "address_id", referencedColumnName = "id") })
	private Set<Address> addresses = new HashSet<>();

//	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@ManyToMany
	@JoinTable(name = "employee_contact_person", joinColumns = {
			@JoinColumn(name = "employee_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "contact_person_id", referencedColumnName = "id") })
	Set<ContactPerson> contactPersons = new HashSet<>();

	@JsonIgnore
	@ApiModelProperty(hidden = true)
	@XmlElement
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "employee")
	private Set<FileDB> files = new HashSet<>();

	public void addFile(FileDB file) {
		this.files.add(file);
		file.setEmployee(this);

	}

	/**
	 * 
	 * @param role
	 */
	public void addRole(Role role) {
		this.roles.add(role);
		role.getEmployees().add(this);
	}

	/**
	 * 
	 * @param role
	 */
	public void romveRole(Role role) {
		this.roles.remove(role);
		role.getEmployees().remove(this);
	}

	/**
	 * 
	 * @param address
	 */
	public void addAddress(Address address) {
		this.addresses.add(address);
		address.getEmployees().add(this);
	}

	/**
	 * 
	 * @param address
	 */
	public void romveAddress(Address address) {
		this.addresses.remove(address);
		address.getEmployees().remove(this);
	}

	/**
	 * 
	 * @param contactPerson
	 */
	public void addContactPerson(ContactPerson contactPerson) {

		this.contactPersons.add(contactPerson);
		contactPerson.getEmployees().add(this);
	}

	/**
	 * 
	 * @param contactPerson
	 */
	public void romveContactPerson(ContactPerson contactPerson) {
		this.contactPersons.remove(contactPerson);
		contactPerson.getEmployees().remove(this);
	}

	@Override
	public void generateLoginname() {

		String toconcat = "";

		int day = LocalDateTime.now().getDayOfMonth();
		if (day < 10) {
			toconcat = "0" + day;
		} else {
			toconcat = "" + day;
		}
		this.setLoginname("EM" + this.getLastName().substring(0, this.getLastName().length() - 1)
				.concat(this.getFirstName().substring(0, 1)).concat(toconcat).toUpperCase());
	}

	@Override
	public String toString() {

		return new StringJoiner("; ", this.getClass().getSimpleName() + " [", "]").add("id = " + this.getId())
				.add("firstname =" + this.getFirstName()).add("lastname = " + this.getLastName())
				.add("e-mail = " + this.getEmail()).add("status" + this.getActive()).add("type" + getUserType())
				.toString();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Employee) {
			Employee employee = (Employee) obj;

			if (employee.getId() != null && StringUtils.isNotBlank(employee.getEmail())) {
				return Objects.equal(this.getId(), employee.getId())
						&& Objects.equal(this.getEmail(), employee.getEmail());

			}

		}
		return false;
	}

	@Override
	public int hashCode() {

		return Objects.hashCode(this.getId(), this.getEmail());

	}

}
