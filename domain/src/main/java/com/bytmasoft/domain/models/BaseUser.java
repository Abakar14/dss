package com.bytmasoft.domain.models;

import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bytmasoft.domain.enums.GenderType;
import com.bytmasoft.domain.enums.UserType;
import com.bytmasoft.domain.model.interfaces.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.utility.RandomString;

/**
 * 
 * @author Mahamat Abakar Date 08.10.2019
 */

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseUser extends BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7764311919115841229L;

	
	@ApiModelProperty(notes = "The api will generate the status")
	@JsonProperty(value = "status")
	@Size(max = 1)
	private String status;
	
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

//	@JsonProperty(value = "email")
//	@Email
//	@Column(nullable = false, unique = true)
//	private String email;
	@Embedded
	@Column(unique = true, nullable = false)
	private EmailAddress emailAddress;

//	@JsonIgnore
	@NotNull(message = "is required")
	@Size(min = 8, message = "is required")
	private String password;
	
	@NotNull(message = "is required")
	@Size(min = 9, max = 9, message = "is required")
	private String salt;

	@JsonProperty(value = "birthday")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
	private LocalDate birthday;

	@ApiModelProperty(notes = "The api will generate the last login")
	@JsonProperty(value = "lastlogin")
	@Column(name = "last_login")
	private LocalDateTime lastLogin;

	@ApiModelProperty(notes = "The api will generate the username")
	@JsonProperty(value = "username")
	@Column(unique = true, nullable = false)
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

	
	public void setPassword(String password) {
		
		this.password = password;
	}


	
	public String generateSalt() {
		
		return	RandomString.make(9);
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
				.append(", email=").append(this.emailAddress.toString()).append(", password=").append(this.getPassword())
				.append("]"
						);

		return builder.toString();
	}

	@Override
	public int hashCode() {

		final int primeNumber = 31;
		int resutl = 1;
		resutl = (primeNumber * resutl) + ((this.emailAddress.toString() == null) ? 0 : this.emailAddress.toString().hashCode());
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

		final BaseUser user = (BaseUser) obj;
		if (!this.emailAddress.toString().equals(user.emailAddress.toString())) {
			return false;
		}

		return true;

	}

}
