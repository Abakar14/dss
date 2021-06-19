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
public class ContactPerson extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -787584079024966039L;

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

	@JsonIgnore
	@ApiModelProperty(hidden = true)
	@XmlElement
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "contactPerson")
	private Set<FileDB> files = new HashSet<>();

	public void addFile(FileDB file) {
		this.files.add(file);
		file.setContactPerson(this);

	}

	public void addAddress(Address address) {
		this.addresses.add(address);
		address.getContactPersons().add(this);
	}

	public void removeAddress(Address address) {
		this.addresses.remove(address);
		address.getContactPersons().remove(this);
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

		this.setLoginname("CO" + this.getLastName().substring(0, this.getLastName().length() - 1)
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

		if (obj instanceof ContactPerson) {
			ContactPerson contactPerson = (ContactPerson) obj;

			if (contactPerson.getId() != null && StringUtils.isNotBlank(contactPerson.getEmail())) {
				return Objects.equal(this.getId(), contactPerson.getId())
						&& Objects.equal(this.getEmail(), contactPerson.getEmail());

			}

		}
		return false;
	}

	@Override
	public int hashCode() {

		return Objects.hashCode(this.getId(), this.getEmail());

	}

}
