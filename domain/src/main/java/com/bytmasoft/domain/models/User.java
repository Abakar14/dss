package com.bytmasoft.domain.models;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.bytmasoft.domain.enums.GenderType;
import com.bytmasoft.domain.enums.UserType;
import com.bytmasoft.domain.model.interfaces.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Mahamat Abakar Date 08.10.2019
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@XmlRootElement
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Long.class)
public class User extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7706628628381104293L;

	@ApiModelProperty(allowEmptyValue = false)
	@JsonProperty(value = "gender")
	@Column(name = "gender", nullable = false)
	@Enumerated(EnumType.STRING)
	private GenderType gender;

	@ApiModelProperty(allowEmptyValue = false)
//	@JsonProperty(value = "firstname")
	@NotNull
	@Size(min = 3, max = 50, message = "firstname must be between {min} and {max} characters long")
	@Column(name = "first_name", nullable = false)
	private String firstName;

	@ApiModelProperty(allowEmptyValue = true)
	@JsonProperty(value = "middelname")
	@Size(min = 1, message = "is required")
	private String middelName;

//	@JsonProperty(value = "lastname")
	@NotNull(message = "is required")
	@Size(min = 3, message = "is required")
	@Column(name = "last_name", nullable = false)
	private String lastName;

	@JsonProperty(value = "email")
	@Email
	@Column(nullable = false, unique = true)
	private String email;

//	@JsonIgnore
	@NotNull(message = "is required")
	@Size(min = 8, message = "is required")
	private String password;

	@JsonProperty(value = "birthday")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
	private LocalDate birthday;

	@ApiModelProperty(notes = "The api will generate the last login")
	@JsonProperty(value = "lastlogin")
	@Column(name = "last_login")
	private LocalDateTime lastLogin;

	@ApiModelProperty(notes = "The api will generate the username")
	@JsonProperty(value = "username")
	private String username;

	@JsonProperty(value = "type")
	@Enumerated(EnumType.STRING)
	private UserType type;

	@ApiModelProperty(notes = "The api calcalte the age from the birthday", hidden = true)
	@JsonProperty(value = "age")
	@Transient
	private int age;

	@JsonProperty(value = "foto")
	private Blob foto;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "user_address", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "address_id", referencedColumnName = "id") })
	private List<Address> addresses = new ArrayList<>();

	@Fetch(FetchMode.JOIN)
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id") })
	List<Role> roles = new ArrayList<>();

	@JsonProperty(value = "school")
	@Fetch(FetchMode.JOIN)
	@ManyToOne
	@JoinColumn(name = "school_id")
	private School school;

	public void addAddress(Address address) {
		this.addresses.add(address);
		address.getUsers().add(this);
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}

	public void RemoveRole(Role role) {

		roles.remove(role);
		role.getUsers().remove(this);

	}

	public int getAge() {

		if (birthday != null) {
			LocalDate today = LocalDate.now();
			Period period = Period.between(birthday, today);
			return period.getYears();

		}
		return age;

	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("User [id=").append(this.getId()).append(", firstname=").append(this.getFirstName())
				.append(", lastname=").append(this.getLastName()).append(", status=").append(this.getStatus())
				.append(", email=").append(this.getEmail()).append(", password=").append(this.getPassword())
				.append(", age").append(this.getAge()).append(", addresses=").append(this.getAddresses())
				.append(", roles=").append(this.getRoles()).append("]");

		return builder.toString();
	}

	@Override
	public int hashCode() {

		final int primeNumber = 31;
		int resutl = 1;
		resutl = (primeNumber * resutl) + ((email == null) ? 0 : email.hashCode());
		return resutl;

	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {

			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		final User user = (User) obj;
		if (!email.equals(user.getEmail())) {
			return false;
		}

		return true;

	}

}
